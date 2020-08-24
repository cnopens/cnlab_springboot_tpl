package com.ds.dss.common.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CommonUtils {

    public static void main(String[] args) {
//    System.out.println("192.168.88.1".substring(0,"192.168.88.1".lastIndexOf(".")));
        System.out.println(CommonUtils.dateFormat(Calendar.getInstance().getTime()));
    }


    public static String dateFormat(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(date);
    }


    public static String dateFormat(Date date, String pattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(date);
    }


}
