package com.exercise.interfaceI;

import java.lang.annotation.*;

/**
 *不记录日志注解
 *
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})//只能在方法上使用此注解
public @interface NotLog {
}