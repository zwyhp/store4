package com.exercise.util;

/**
 * 业务异常
 */
public class BussinessException extends RuntimeException  {

    public BussinessException(String message){
        super(message);
    }
}
