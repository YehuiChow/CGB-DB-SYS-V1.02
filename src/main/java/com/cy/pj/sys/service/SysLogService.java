package com.cy.pj.sys.service;

import org.apache.ibatis.annotations.Param;

import com.cy.pj.common.vo.PageObject;
import com.cy.pj.sys.entity.SysLog;

public interface SysLogService {
	/**
	 * 分页查询当前页记录
	 * @param username
	 * @param pageCurrent
	 * @return
	 */
	PageObject<SysLog> findPageObjects(String username,Integer pageCurrent);
	/**
	 * 基于id执行删除操作
	 * @param id
	 * @return
	 */
	int deleteObjects(@Param("ids")Integer...ids);
}
