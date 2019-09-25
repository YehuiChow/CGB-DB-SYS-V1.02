package com.cy.pj.sys.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysRoleMenuDao {
	/**
	 * 	基于菜单id删除关系数据
	 * @param menuId
	 * @return
	 */
	int deleteObjectsByMenuId(Integer menuId);
	
	/**
	 * 	基于角色id删除关系数据
	 * @param roleId
	 * @return
	 */
	int deleteObjectsByRoleId(Integer roleId);
	
}
