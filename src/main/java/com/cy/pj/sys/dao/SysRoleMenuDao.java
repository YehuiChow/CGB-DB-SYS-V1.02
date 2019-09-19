package com.cy.pj.sys.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysRoleMenuDao {
	/**
	 * 	基于菜单id删除关系数据
	 * @param menuId
	 * @return
	 */
	@Delete("delete from sys_role_menus where menu_id=#{menuId}")
	int deleteObjectsByMenuId(Integer menuId);
}
