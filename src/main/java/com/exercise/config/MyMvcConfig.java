package com.exercise.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;

@EnableTransactionManagement
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.exercise"})
public class MyMvcConfig extends WebMvcConfigurerAdapter {
    @Resource
    DBConfig dbConfig;
    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        viewResolver.setViewClass(JstlView.class);
        return viewResolver;
    }
    /*静态资源拦截*/
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("/WEB-INF/static/");
    }
    /*配置cors跨域问题*/
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }
    /*配置拦截器*/
    /*@Override
    public void addInterceptors(InterceptorRegistry registry) {
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
        // 映射为 user 的控制器下的所有映射
        registry.addInterceptor().addPathPatterns("/admin/*").excludePathPatterns("/index", "/");
        super.addInterceptors(registry);
    }*/



    @Bean
    public ComboPooledDataSource dataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(dbConfig.driver);
        dataSource.setJdbcUrl(dbConfig.url);
        dataSource.setUser(dbConfig.username);
        dataSource.setPassword(dbConfig.password);
        return dataSource;
    }

    //配置事务管理
    @Bean
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory)
    {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory);
        return transactionManager;
    }


    @Bean
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
        LocalSessionFactoryBean sfb = new LocalSessionFactoryBean();
        sfb.setDataSource(dataSource);
        sfb.setPackagesToScan("com.exercise.user.domain","com.exercise.orderform.domain","com.exercise.product.domain");
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect","org.hibernate.dialect.MySQL5Dialect");
        properties.setProperty("hibernate.show_sql","true");
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        sfb.setHibernateProperties(properties);
        return sfb;
    }



}

