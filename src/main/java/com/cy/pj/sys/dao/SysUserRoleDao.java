package com.cy.pj.sys.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysUserRoleDao {
	/**
	 * 	基于角色id删除关系数据
	 * @param role
	 * @return
	 */
	int deleteObjectsByRoleId(Integer roleId);
}
