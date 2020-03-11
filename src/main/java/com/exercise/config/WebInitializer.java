package com.exercise.config;

import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{RootConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {MyMvcConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/*","/"};
    }
    @Override
    protected Filter[] getServletFilters() {
        return new Filter[]{delegatingFilterProxy(), new HiddenHttpMethodFilter()};
    }


    protected DelegatingFilterProxy delegatingFilterProxy(){
        DelegatingFilterProxy delegatingFilterProxy = new DelegatingFilterProxy();
        delegatingFilterProxy.setTargetBeanName("shiroFilter");
        delegatingFilterProxy.setTargetFilterLifecycle(true);
       return delegatingFilterProxy;
   }


}
