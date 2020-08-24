package com.ds.dss.common.enums;

public enum DataSourceEnum
{
    DEFAULT_DS("db1"), 
    SLAVE_ZX_PRODUCT("db2");
    
    private String value;
    
    private DataSourceEnum(final String value) {
        this.value = value;
    }
    
    public String getValue() {
        return this.value;
    }
}
