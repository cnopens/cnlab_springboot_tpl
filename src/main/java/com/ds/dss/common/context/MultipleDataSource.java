package com.ds.dss.common.context;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/***
 * 动态数据源，需要继承AbstractRoutingDataSource
 * 作用：使用DatabaseContextHolder获取当前线程的DatabaseType
 *
 */
public class MultipleDataSource extends AbstractRoutingDataSource
{
    protected Object determineCurrentLookupKey() {
        return DataSourceContextHolder.getDataSource();
    }
}
