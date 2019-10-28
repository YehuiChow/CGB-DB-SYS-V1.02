package com.cy.pj.common.config;


import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

@Configuration
public class SpringWebConfig {
	
	@Bean
	public FilterRegistrationBean<DelegatingFilterProxy> filterRegistrationBean() {
		FilterRegistrationBean<DelegatingFilterProxy> rBean = new FilterRegistrationBean<>();
		rBean.setFilter(new DelegatingFilterProxy("shiroFilterFactory"));
		rBean.addUrlPatterns("/*");
		return rBean;
	}
}
