package com.cy.pj.sys.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.cy.pj.common.vo.Node;
import com.cy.pj.sys.entity.SysDept;

@Mapper
public interface SysDeptDao {
	/**
	 * 	查询所有部门以及上级部门信息
	 * 	@return 所有部门
	 */
	List<Map<String, Object>> findObjects();
	
	/**
	 * 	基于id统计子部门数
	 * @param id
	 * @return
	 */
	int getChildObjects(Integer id);
	
	/**
	 * 	删除部门自身信息
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
	 * 	 添加部门
	 * @param sysMenu
	 * @return
	 */
	int insertObject(SysDept sysDept);
	
	/**
	 * 	更新部门信息
	 * @param sysMenu
	 * @return
	 */
	int updateObject(SysDept sysDept);
}
