package com.ds.dss.common.utils;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.*;

import java.util.Map;

public class JacksonUtil
{
    private static final ObjectMapper objectMapper;
    
    private JacksonUtil() {
    }
    
    public static ObjectMapper getInstance() {
        return JacksonUtil.objectMapper;
    }
    
    public static String obj2json(final Object obj) throws Exception {
        return JacksonUtil.objectMapper.writeValueAsString(obj);
    }
    
    public static <T> T json2pojo(final String jsonString, final Class<T> clazz) throws Exception {
        JacksonUtil.objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        return (T)JacksonUtil.objectMapper.readValue(jsonString, (Class)clazz);
    }
    
    public static <T> Map<String, Object> json2map(final String jsonString) throws Exception {
        final ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return (Map<String, Object>)mapper.readValue(jsonString, (Class) Map.class);
    }
    
    static {
        objectMapper = new ObjectMapper();
    }
}
