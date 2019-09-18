package com.cy.pj.common.web;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cy.pj.common.vo.JsonResult;
/**
 * 当控制层对象出现异常后：
 * 1)检测控制层对象内部有没有@ExceptionHandler描述异常处理方法
 * 2)检测Spring容器中是否有对象使用了@ControllerAdvice注解修饰的异常处理方法，
 * 	 假如有，则使用类中@ExceptionHandler修饰的方法处理特定异常
 *
 */
//@ControllerAdvice
//@ResponseBody
@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(RuntimeException.class)
	//@ResponseBody
	public JsonResult doHandleRuntimeException(
			RuntimeException e){
    	e.printStackTrace();//也可以写日志
		return new JsonResult(e);//封装异常信息
	}
}
