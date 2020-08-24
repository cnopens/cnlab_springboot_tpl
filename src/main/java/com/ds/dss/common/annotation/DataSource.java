package com.ds.dss.common.annotation;


import com.ds.dss.common.enums.DataSourceEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/***
 * Datasource annotation
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface DataSource {
    DataSourceEnum value() default DataSourceEnum.DEFAULT_DS;
}
