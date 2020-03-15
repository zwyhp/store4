package com.exercise.aop;


import com.exercise.interfaceI.NotLog;
import com.exercise.log.service.ILogService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;

/**
 * 日志切面类
 *
 */
@Aspect
@Component
public class LogAspect {
    private final static Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Autowired
    private ILogService logService;
    //设置切入点：这里直接拦截被@RestController注解的类
    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void pointcut() {

    }

    /**
     * 切面方法,记录日志
     *
     * @return
     * @throws Throwable
     */
    @Around("pointcut()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long beginTime = System.currentTimeMillis();//1、开始时间
        //利用RequestContextHolder获取requst对象
        ServletRequestAttributes requestAttr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        String uri = requestAttr.getRequest().getRequestURI();
        logger.info("开始计时: {}  URI: {}", new Date(), uri);
        //访问目标方法的参数 可动态改变参数值
        Object[] args = joinPoint.getArgs();
        //方法名获取
        String methodName = joinPoint.getSignature().getName();
        logger.info("请求方法：{}, 请求参数: {}", methodName, Arrays.toString(args));
        Signature signature = joinPoint.getSignature();
        if (!(signature instanceof MethodSignature)) {
            throw new IllegalArgumentException("暂不支持非方法注解");
        }
        //调用实际方法
        Object object = joinPoint.proceed();
        //获取执行的方法
        MethodSignature methodSign = (MethodSignature) signature;
        Method method = methodSign.getMethod();
        //判断是否包含了 无需记录日志的方法
        NotLog loggerAnno = AnnotationUtils.getAnnotation(method, NotLog.class);
        if (loggerAnno != null ) {
            return object;
        }
        long endTime = System.currentTimeMillis();
        logger.info("结束计时: {},  URI: {},耗时：{}", new Date(), uri, endTime - beginTime);
        Subject subject = SecurityUtils.getSubject();
        Object principal = subject.getPrincipal();
        if (!(principal == null || principal == "")) {
            logService.addLog(uri, (String) principal);
        }
        return object;

    }
}