package com.cy.pj.common.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * @Aspect 描述的类为切面类,此类中实现:
 * 	1)切入点(Pointcut)的定义
 * 	2)通知(advice)的定义(扩展功能)
 * @author Administrator
 *
 */
@Slf4j
@Aspect
@Component
public class SysLogAspect {
	
	/**
	 * bean("")切入点的一种表达式
	 */
	@Pointcut("bean(sysMenuServiceImpl)")//bean(bean名称或者一个表达式)
	public void logPointCut() {}
	
	@Around("logPointCut()") //切入点由logPointCut()定义的一个环绕通知方法
	public Object aroundAdivce(ProceedingJoinPoint jp) throws Throwable{
		long start = System.currentTimeMillis();
		log.info("start:"+start);
		Object result = jp.proceed();//调用下一个切面或者目标方法
		long end = System.currentTimeMillis();
		log.info("end:"+end);
		return result;
	}
	
}
