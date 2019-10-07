package com.cy.pj.sys.service;


import org.apache.ibatis.annotations.Param;

import com.cy.pj.common.vo.PageObject;
import com.cy.pj.sys.entity.SysRole;
import com.cy.pj.sys.vo.SysRoleMenuVo;

public interface SysRoleService {
	PageObject<SysRole> findPageObjects(@Param("username")String username,@Param("pageCurrent")Integer pageCurrent);
	int deleteObjectsByRoleId(@Param("roleId")Integer roleId);
	SysRoleMenuVo findObjectById(Integer id);
	int saveObject(SysRole sysRole,Integer[] menuIds);
	int updateObject(SysRole sysRole,Integer[] menuIds);
}
