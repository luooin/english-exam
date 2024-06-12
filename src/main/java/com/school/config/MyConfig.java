package com.school.config;

import com.school.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class MyConfig extends WebMvcConfigurationSupport {

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        super.addResourceHandlers(registry);
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/page/login")//首页页面
                .excludePathPatterns("/static/**")//静态资源
                .excludePathPatterns("/page/register")//注册页面
                .excludePathPatterns("/page/forgot")//忘记密码页面
                .excludePathPatterns("/common/sendEmail")//用户邮箱找回密码
                .excludePathPatterns("/page/rePassword**")//邮箱找回密码
                .excludePathPatterns("/common/userRePassword")//用户邮箱修改密码
                .excludePathPatterns("/common/adminSendEmail")//管理员邮箱找回密码
                .excludePathPatterns("/page/adminRePassword**")//邮箱找回密码
                .excludePathPatterns("/common/adminRePassword")//管理员邮箱修改密码
                .excludePathPatterns("/user/login")//登录
                .excludePathPatterns("/user/register")//注册
                .excludePathPatterns("/admin/login")//管理员登录
                .excludePathPatterns("/page/adminForgot")
                .excludePathPatterns("/page/adminLogin");//管理员页面
        super.addInterceptors(registry);
    }

    //设置首页
    @Override
    protected void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/page/login");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        super.addViewControllers(registry);
    }
}
