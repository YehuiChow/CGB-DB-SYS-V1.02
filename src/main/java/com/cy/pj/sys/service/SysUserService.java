package com.cy.pj.sys.service;

import com.cy.pj.common.vo.PageObject;
import com.cy.pj.sys.vo.SysUserDeptVo;

public interface SysUserService {
	/**
	 * 分页查询用户以及用户对应的部门信息
	 * @param username
	 * @param pageCurrent
	 * @return
	 */
	PageObject<SysUserDeptVo> findPageObjects(String username,Integer pageCurrent);
}
