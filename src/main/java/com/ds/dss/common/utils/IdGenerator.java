package com.ds.dss.common.utils;

import java.util.UUID;

/***
 * @Desc 解决其他生成策略可能重复问题 (目前仅支持单体应用)
 */
public class IdGenerator {

    public static String getId() {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        return uuid;
    }

    public static void main(final String[] args) {
        System.out.println(getId());
        System.out.println(getId());
        IdGenerator idGen = new IdGenerator();
        System.out.println(idGen.toString());
    }
}
