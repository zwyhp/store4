package com.exercise.util;


/**
 * 业务异常工具类
 */
public class BussinessUtil {
    public static final String USER_INEXISTENCE = "用户不存在";
    public static final String ROLE_INEXISTENCE = "角色不存在";
    public static final String PER_INEXISTENCE = "权限不存在";
    public static final String PRODUCT_INEXISTENCE = "商品不存在";
    public static final String ORDER_INEXISTENCE = "订单不存在";
    public static final String LOG_INEXISTENCE = "日志不存在";

    public static final String USERNAME_REPETITION = "用户名重复";
    public static final String ROLENAME_REPETITION = "角色名重复";
    public static final String PERNAME_REPETITION = "权限名重复";
    public static final String PRODUCTNAME_REPETITION = "商品名重复";

    public static final String UNDER_STOCK = "库存不足";

    public static final String LOG_ERROR = "日志不能修改";



    private BussinessUtil() { throw new IllegalStateException("Utility class"); }

    public static void isNull(Object object, String error){
        if (object == null){
            throw new BussinessException(error);
        }
    }
    public static void isnotNull(Object object, String error){
        if (object != null){
            throw new BussinessException(error);
        }
    }

    public static void error(String error){
        throw new BussinessException(error);
    }

    public static void pagingfind(boolean judge){
        if (judge){
            throw new BussinessException("当前页码超出总页数");
        }
    }
}
