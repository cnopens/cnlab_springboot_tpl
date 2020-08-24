package com.ds.dss.config;

import com.alibaba.druid.support.http.StatViewServlet;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.servlet.Servlet;

public class DruidConfiguration
{
    @Bean
    public ServletRegistrationBean startViewServlet() {
        final ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean((Servlet)new StatViewServlet(), new String[] { "/druid/*" });
        servletRegistrationBean.addInitParameter("allow", "127.0.0.1");
        servletRegistrationBean.addInitParameter("deny", "127.0.0.1");
        servletRegistrationBean.addInitParameter("loginUsername", "admin");
        servletRegistrationBean.addInitParameter("loginPassword", "123456");
        servletRegistrationBean.addInitParameter("resetEnable", "false");
        return servletRegistrationBean;
    }
    
    @Bean
    public FilterRegistrationBean statFilter() {
        return null;
    }
}
