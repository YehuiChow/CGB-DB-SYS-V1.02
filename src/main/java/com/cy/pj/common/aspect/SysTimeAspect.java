package com.cy.pj.common.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
@Order(1)//多个切面时设置优先级,数字越小优先级越高
public class SysTimeAspect {
	//1.粗粒度的的切入点表达式(只能精确到类)
	//1)bean表达式
	//@Pointcut("bean(sysMenuServiceImpl)")//提取切入点的方法
	//2)within表达式
	//@PointCut("within(com.cy.pj.sys.service.impl.* )")
	//2.细粒度的切入点表达式(可以精确到方法)
	//1)excution表达式(了解)
	//@PointCut("excution(返回值 全类名.方法名(参数))")
	//2)@anotation表达式(掌握)
	@Pointcut("bean(sysMenuServiceImpl)")
	public void doTimePointCut() {}
	
	@Before("doTimePointCut()")//切入点可以直接写,如:@Before("bean(sysMenuServiceImpl)")
	public void beforeAdvice() {
		log.info("time:beforeAdvice");
	}
	
	@After("doTimePointCut()")
	public void afterAdvice() {
		log.info("time:afterAdvice");
	}
	
	@AfterReturning("doTimePointCut()")
	public void afterReturningAdvice() {
		log.info("time:afterReturningAdvice");
	}
	
	@AfterThrowing("doTimePointCut()")
	public void afterThrowingAdvice() {
		log.info("time:afterThrowingAdvice");
	}
	
	@Around("doTimePointCut()")
	public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
		log.info("time:aroundAdvice.before");
		Object result = joinPoint.proceed();
		log.info("time:aroundAdvice.after");
		return result;
	}
}
