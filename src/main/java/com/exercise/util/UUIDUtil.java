package com.exercise.util;

import java.util.UUID;

/**
 * 获得唯一标识
 */
public class UUIDUtil {
    public static String genUUID(){
        return UUID.randomUUID().toString().replace("","");
    }
}
