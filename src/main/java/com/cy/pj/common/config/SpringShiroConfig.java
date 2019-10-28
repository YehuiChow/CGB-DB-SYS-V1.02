package com.cy.pj.common.config;

import java.util.LinkedHashMap;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Configuration 注解描述的类为一个配置对象
 * 此对象也会交给spring管理
 */
@Configuration//bean
public class SpringShiroConfig {
	@Bean
	public SecurityManager securityManager() {
		DefaultWebSecurityManager sManager = new DefaultWebSecurityManager();
		return sManager;
	}
	
	@Bean//默认key为方法名
	public ShiroFilterFactoryBean shiroFilterFactory(SecurityManager securityManager) {
		//构建bean对象,通过此对象创建过滤器工厂
		ShiroFilterFactoryBean fBean = new ShiroFilterFactoryBean();
		//注入SecurityManager
		fBean.setSecurityManager(securityManager);
		//设置登录url
		fBean.setLoginUrl("/doLoginUI");
		//设置过滤规则
		LinkedHashMap<String, String> cMap = new LinkedHashMap<>();
		cMap.put("/bower_components/**", "anon");
		cMap.put("/build/**", "anon");
		cMap.put("/dist/**", "anon");
		cMap.put("/plugins/**", "anon");//anon表示允许匿名访问
		cMap.put("/**", "authc");///**表示排除上面的静态文件以外的所有，authc表示需要认证以后访问
		fBean.setFilterChainDefinitionMap(cMap);
		return fBean; 
	}
}
