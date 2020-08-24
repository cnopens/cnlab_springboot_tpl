package com.ds.dss.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.baomidou.mybatisplus.MybatisConfiguration;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.plugins.PerformanceInterceptor;
import com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean;
import com.ds.dss.common.enums.DataSourceEnum;
import com.ds.dss.common.context.MultipleDataSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;


@Configuration
@MapperScan({"com.ds.iaas.rsmgr.mbg.mapper", "com.ds.iaas.rsmgr.dao"})
public class MyBatisConfig {
    @Value("${mybatis-plus.mapper-locations}")
    private String mapperLocation;

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        final PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        paginationInterceptor.setLocalPage(true);
        return paginationInterceptor;
    }

    @Bean
    @Profile({"dev", "test"})
    public PerformanceInterceptor performanceInterceptor() {
        final PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
        performanceInterceptor.setMaxTime(1000L);
        performanceInterceptor.setFormat(true);
        return performanceInterceptor;
    }

    /**
     * @return
     * @ConfigurationProperties 的大致作用就是：赋值，将注解转换成对象。给对象赋值
     */
    @Bean(name = {"db1"})
    @ConfigurationProperties(prefix = "spring.datasource.multiple.db1")
    public DataSource db1() {
        System.out.printf("========================default db1 datasource==============================");
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = {"db2"})
    @ConfigurationProperties(prefix = "spring.datasource.multiple.db2")
    public DataSource db2() {
        System.out.printf("========================default db2 datasource==============================");
        return DruidDataSourceBuilder.create().build();
    }


    /**
     * 创建动态数据源
     *
     * @primary： 表示在同一个接口有多个类可以注入的时候，默认选择哪个，而不是让@Autowire报错，spring扫码注入bean时优先注入带有@Primary注解的bean
     * @Qualifier： 当有多个同一类型的Bean时，可以用@Qualifier(“name”)来指定。@Qualifier限定描述符除了能根据名字进行注入，还能进行更细粒度的控制如何选择候选者
     */
    @Bean
    @Primary
    public DataSource multipleDataSource(@Qualifier("db1") final DataSource db1, @Qualifier("db2") final DataSource db2) {
        final MultipleDataSource multipleDataSource = new MultipleDataSource();
        final Map<Object, Object> targetDataSources = new HashMap<Object, Object>();
        targetDataSources.put(DataSourceEnum.DEFAULT_DS, db1);
        targetDataSources.put(DataSourceEnum.SLAVE_ZX_PRODUCT.getValue(), db2);
        System.out.println("Target Datasource : "+targetDataSources);
        multipleDataSource.setTargetDataSources(targetDataSources);
        multipleDataSource.setDefaultTargetDataSource(db1);
        System.out.println("Target Datasource : "+targetDataSources);
        return multipleDataSource;
    }

    @Bean({"sqlSessionFactory"})
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        final MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(this.multipleDataSource(this.db1(), this.db2()));
        final MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setJdbcTypeForNull(JdbcType.NULL);
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setCacheEnabled(false);
        sqlSessionFactory.setConfiguration(configuration);
        sqlSessionFactory.setPlugins(new Interceptor[]{this.paginationInterceptor()});
        sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(this.mapperLocation));
        return sqlSessionFactory.getObject();
    }
}
