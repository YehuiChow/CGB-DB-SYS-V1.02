package com.cy.pj.common.vo;

import java.io.Serializable;

import lombok.Data;
/**
 * 通过此对象封装服务端要返回给客户端的数据，
 * 此对象最核心的作用是为业务层对象的执行结果
 * 添加状态信息
 */
@Data
public class JsonResult implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7081912909210011540L;
	/**状态码*/
	private int state=1;//1表示SUCCESS,0表示ERROR
	/**状态信息*/
	private String message="ok";
	/**正确数据*/
	private Object data;
	public JsonResult() {}
	public JsonResult(String message){
		this.message=message;
	}
	/**一般查询时调用，封装查询结果*/
	public JsonResult(Object data) {
		this.data=data;
	}
	/**出现异常时时调用*/
	public JsonResult(Throwable t){
		this.state=0;
		this.message=t.getMessage();
	}
}
