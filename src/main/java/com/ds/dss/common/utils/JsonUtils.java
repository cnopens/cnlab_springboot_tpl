package com.ds.dss.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.ValueFilter;

public class JsonUtils {
    //将对象中的null值变为空字符串
    public static String ObjToJsonNotNull(Object result){

        ValueFilter valueFilter = new ValueFilter() {
            @Override
            public Object process(Object object, String name, Object value) {
                if (value == null)
                    return "";
                return value;
            }
        };
        String string = JSONObject.toJSONString(result, valueFilter);
        return string;

    }
}
