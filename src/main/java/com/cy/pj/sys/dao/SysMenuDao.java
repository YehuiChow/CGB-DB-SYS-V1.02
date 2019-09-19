package com.cy.pj.sys.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SysMenuDao {
	/**
	 * 	查询所有菜单以及上级菜单信息
	 * 	@return 所有菜单
	 */
	@Select("select c.*,p.name parentName from sys_menus c left join sys_menus p on c.parentId=p.id")
	List<Map<String, Object>> findObjects();
	
	/**
	 * 	基于id统计子菜单数
	 * @param id
	 * @return
	 */
	@Select("select count(*) from sys_menus where parentId=#{id}")
	int getChildObject(Integer id);
	
	/**
	 * 	删除菜单自身信息
	 * @param id
	 * @return
	 */
	@Delete("delete from sys_menus where id=#{id}")
	int deleteObjects(Integer id);
}
