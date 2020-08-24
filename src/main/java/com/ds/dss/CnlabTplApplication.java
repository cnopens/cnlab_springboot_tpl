package com.ds.dss;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @ClassName: CnlabTplApplication
 * @Description: function desc
 * @Author cnopens
 * @Date 2020/8/24 下午1:05
 * @Copyright Dashuo
 **/
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, DruidDataSourceAutoConfigure.class})
@EnableTransactionManagement
public class CnlabTplApplication {

    public static void main(String[] args) {
        SpringApplication.run((Class) CnlabTplApplication.class, args);

    }
}
