package com.ds.dss.componet;

import com.ds.dss.common.annotation.DataSource;
import com.ds.dss.common.context.DataSourceContextHolder;
import org.aspectj.lang.annotation.*;
import org.slf4j.*;
import org.springframework.core.annotation.*;
import org.springframework.stereotype.*;

/***
 * @Order(-1)保证该AOP在@Transactional之前执行，如果没有order，
 * 而且类配置了@Transaction那么数据源会失效
 * 通过order指定顺序，值越小越先执行，不标注数字则默认int最大值2147483647，即优先级最低
 */
@Component
@Aspect
@Order(-1)
public class DataSourceAspect {
    private static final Logger log;


    /**
     * 设置切面范围
     */
    @Pointcut("@within(com.ds.dss.common.annotation.DataSource) || @annotation(com.ds.dss.common.annotation.DataSource)")
    public void pointCut() {
    }

    /**
     * 添加数据源上下文
     *
     * @param dataSource
     */
    @Before("pointCut() && @annotation(dataSource)")
    public void doBefore(DataSource dataSource) {
        log.info("选择数据源---" + dataSource.value().getValue());
        DataSourceContextHolder.setDataSource(dataSource.value().getValue());
    }

    /**
     * 清除数据源上下文
     */
    @After("pointCut()")
    public void doAfter() {
        DataSourceContextHolder.clear();
    }

    static {
        log = LoggerFactory.getLogger((Class) DataSourceAspect.class);
    }
}
