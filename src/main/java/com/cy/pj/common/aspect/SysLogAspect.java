package com.cy.pj.common.aspect;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cy.pj.common.annotation.RequiredLog;
import com.cy.pj.common.util.IPUtils;
import com.cy.pj.sys.entity.SysLog;
import com.cy.pj.sys.service.SysLogService;

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
	//@Pointcut("bean(sysMenuServiceImpl)")//bean(bean名称或者一个表达式)
	@Pointcut("@annotation(com.cy.pj.common.annotation.RequiredLog)")
	public void logPointCut() {}
	
	@Around("logPointCut()") //切入点由logPointCut()定义的一个环绕通知方法
	public Object aroundAdivce(ProceedingJoinPoint jp) throws Throwable{
		long start = System.currentTimeMillis();
		log.info("start:"+start);
		Object result = jp.proceed();//调用下一个切面或者目标方法
		long end = System.currentTimeMillis();
		log.info("end:"+end);
		//记录日志(用户行为信息)
		saveLog(jp,(end-start));
		return result;
	}
	
	//日志记录
	@Autowired
	private SysLogService sysLogService;
	private void saveLog(ProceedingJoinPoint jp, long time) throws Throwable {
		//1.获取用户行为日志(ip,username,operation,method,params,time,createdTime)
		//获取目标方法所在类的字节码对象,通过字节码对象获取方法信息
		Class<? extends Object> targetCls = jp.getTarget().getClass();
		System.out.println(targetCls);
		//获取方法签名(通过此签名目标获取方法信息)
		MethodSignature ms = (MethodSignature) jp.getSignature();
		//获取目标方法上的注解指定的操作名称
		Method targetMethod = targetCls.getDeclaredMethod(ms.getName(), ms.getParameterTypes());
		RequiredLog requiredLog = targetMethod.getAnnotation(RequiredLog.class);
		String operation = requiredLog.value();
		//获取目标方法名(目标类型+方法名)
		String targetClsName = targetCls.getName();
		String targetObjectMethodNameString = targetClsName + "." + ms.getName();
		//获取请求参数
		String targetMethodParams = Arrays.toString(jp.getArgs());
		//2.封装用户行为日志(SysLog)
		SysLog sysLog = new SysLog();
		sysLog.setIp(IPUtils.getIpAddr());
		sysLog.setUsername("admin");
		sysLog.setOperation(operation);
		sysLog.setMethod(targetObjectMethodNameString);
		sysLog.setParams(targetMethodParams);
		sysLog.setTime(time);
		
		//3.调用业务层对象方法(saveObject)将日志写入数据库
		sysLogService.saveObject(sysLog);
	}
	
}
