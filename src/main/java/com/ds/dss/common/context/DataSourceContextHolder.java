package com.ds.dss.common.context;

/***
 * 保存一个线程安全的DatabaseType容器
 */

/**
 * 保存一个线程安全的DatabaseType容器
 * @author Admin
 * @Date 2020/04/19
 */
public class DataSourceContextHolder {
    private static final ThreadLocal<String> contextHolder = new InheritableThreadLocal<>();

    /**
     *  设置数据源
     * @param db
     */
    public static void setDataSource(String db){
        contextHolder.set(db);
    }

    /**
     * 取得当前数据源
     * @return
     */
    public static String getDataSource(){
        return contextHolder.get();
    }

    /**
     * 清除上下文数据
     */
    public static void clear(){
        contextHolder.remove();
    }
}
