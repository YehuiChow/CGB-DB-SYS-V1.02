package com.cy.pj.sys.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.cy.pj.common.vo.Node;
import com.cy.pj.sys.entity.SysMenu;

@Mapper
public interface SysMenuDao {
	/**
	 * 	查询所有菜单以及上级菜单信息
	 * 	@return 所有菜单
	 */
	List<Map<String, Object>> findObjects();
	
	/**
	 * 	基于id统计子菜单数
	 * @param id
	 * @return
	 */
	int getChildObjects(Integer id);
	
	/**
	 * 	删除菜单自身信息
	 * @param id
	 * @return
	 */
	int deleteObject(Integer id);
	
	/**
	 * 
	 * @return
	 */
	List<Node> findZtreeMenuNodes();
	
	/**
	 *  添加菜单
	 * @param sysMenu
	 * @return
	 */
	int insertObject(SysMenu sysMenu);
}
