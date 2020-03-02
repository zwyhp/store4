package com.exercise.util;

import com.exercise.controller.BussinessException;

public class BussinessExceptionUtil {
    public static void isNull(Object object, String error){
        if (object == null){
            throw new BussinessException(error);
        }
    }

    public static void error(String error){
        throw new BussinessException(error);
    }
}
