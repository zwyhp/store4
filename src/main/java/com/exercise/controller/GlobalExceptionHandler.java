package com.exercise.controller;


import com.exercise.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.net.BindException;

/*

@ControllerAdvice
public class GlobalExceptionHandler {
    private Logger log = LoggerFactory.getLogger(this.getClass());


    */
/**
     * 数据校验异常
     *//*

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Object BindException(BindException e){
        log.error("参数异常:"+e.getMessage());
        return ResponseUtil.badArgument();
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Object handleException(Exception e){
        System.out.println(e.getMessage());
        log.error(e.getMessage());
        return ResponseUtil.unsupport();
    }
*/


    /**
     * 拦截业务异常
     *//*
    @ExceptionHandler(BussinessException.class)   //此处为自定义业务异常类
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)   //返回一个指定的http response状态码
    @ResponseBody
    public ErrorTip notFount(BussinessException e) {
        LogManager.me().executeLog(LogTaskFactory.exceptionLog(ShiroKit.getUser().getId(), e));
        getRequest().setAttribute("tip", e.getMessage());
        log.error("业务异常:", e);
        return new ErrorTip(e.getCode(), e.getMessage());
    }


    *//**
     * 用户未登录
     *//*
    @ExceptionHandler(AuthenticationException.class)   //此处为shiro未登录异常类
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String unAuth(AuthenticationException e) {
        log.error("用户未登陆：", e);
        return "/login.html";
    }

    *//**
     * 账号被冻结
     *//*
    @ExceptionHandler(DisabledAccountException.class)   //此处为shiro账号冻结异常类
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String accountLocked(DisabledAccountException e, Model model) {
        String username = getRequest().getParameter("username");
        LogManager.me().executeLog(LogTaskFactory.loginLog(username, "账号被冻结", getIp()));
        model.addAttribute("tips", "账号被冻结");
        return "/login.html";
    }

    *//**
     * 账号密码错误
     *//*
    @ExceptionHandler(CredentialsException.class)   //此处为shiro账号密码错误异常类
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String credentials(CredentialsException e, Model model) {
        String username = getRequest().getParameter("username");
        LogManager.me().executeLog(LogTaskFactory.loginLog(username, "账号密码错误", getIp()));
        model.addAttribute("tips", "账号密码错误");
        return "/login.html";
    }

    *//**
     * 验证码错误
     *//*
    @ExceptionHandler(InvalidKaptchaException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String credentials(InvalidKaptchaException e, Model model) {
        String username = getRequest().getParameter("username");
        LogManager.me().executeLog(LogTaskFactory.loginLog(username, "验证码错误", getIp()));
        model.addAttribute("tips", "验证码错误");
        return "/login.html";
    }

    *//**
     * 无权访问该资源
     *//*
    @ExceptionHandler(UndeclaredThrowableException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ErrorTip credentials(UndeclaredThrowableException e) {
        getRequest().setAttribute("tip", "权限异常");
        log.error("权限异常!", e);
        return new ErrorTip(BizExceptionEnum.NO_PERMITION);
    }

    *//**
     * 拦截未知的运行时异常
     *//*
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorTip notFount(RuntimeException e) {
        LogManager.me().executeLog(LogTaskFactory.exceptionLog(ShiroKit.getUser().getId(), e));
        getRequest().setAttribute("tip", "服务器未知运行时异常");
        log.error("运行时异常:", e);
        return new ErrorTip(BizExceptionEnum.SERVER_ERROR);
    }

    *//**
     * session失效的异常拦截
     *//*
    @ExceptionHandler(InvalidSessionException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String sessionTimeout(InvalidSessionException e, Model model, HttpServletRequest request, HttpServletResponse response) {
        model.addAttribute("tips", "session超时");
        assertAjax(request, response);
        return "/login.html";
    }

    *//**
     * session异常
     *//*
    @ExceptionHandler(UnknownSessionException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String sessionTimeout(UnknownSessionException e, Model model, HttpServletRequest request, HttpServletResponse response) {
        model.addAttribute("tips", "session超时");
        assertAjax(request, response);
        return "/login.html";
    }

    private void assertAjax(HttpServletRequest request, HttpServletResponse response) {
        if (request.getHeader("x-requested-with") != null
                && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
            //如果是ajax请求响应头会有，x-requested-with
            response.setHeader("sessionstatus", "timeout");//在响应头设置session状态
        }
    }*/

/*}*/
