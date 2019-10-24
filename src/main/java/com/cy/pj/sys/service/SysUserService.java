package com.cy.pj.sys.service;

import java.util.Map;

import com.cy.pj.common.vo.PageObject;
import com.cy.pj.sys.entity.SysUser;
import com.cy.pj.sys.vo.SysUserDeptVo;

public interface SysUserService {
	/**
	 * 分页查询用户以及用户对应的部门信息
	 * @param username
	 * @param pageCurrent
	 * @return
	 */
	PageObject<SysUserDeptVo> findPageObjects(String username,Integer pageCurrent);

	Integer doValidById(Integer id, Integer valid,String modifiedUser);

	Map<String, Object> findObjectById(Integer id);
	
	int saveObject(SysUser sysUser,Integer[] roleIds);

	int updateObject(SysUser entity, Integer[] roleIds);
	
}
