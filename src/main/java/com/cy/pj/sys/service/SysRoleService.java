package com.cy.pj.sys.service;


import org.apache.ibatis.annotations.Param;

import com.cy.pj.common.vo.PageObject;
import com.cy.pj.sys.entity.SysRole;

public interface SysRoleService {
	PageObject<SysRole> findPageObjects(@Param("username")String username,@Param("pageCurrent")Integer pageCurrent);
}
