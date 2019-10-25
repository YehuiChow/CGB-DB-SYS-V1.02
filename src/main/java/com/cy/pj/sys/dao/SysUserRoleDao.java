package com.cy.pj.sys.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysUserRoleDao {
	/**
	 * 	基于角色id删除关系数据
	 * @param role
	 * @return
	 */
	int deleteObjectsByRoleId(Integer roleId);
	/**
	 *	 保存用户和角色的关系数据
	 * @param userId
	 * @param roleIds
	 * @return
	 */
	int insertObjects(@Param("userId")Integer userId,@Param("roleIds")Integer[] roleIds);
	
	Integer[] findRoleIdsByUserId(Integer userId) ;
	int updateObjects(@Param("id")Integer id, @Param("roleIds")Integer[] roleIds);
	int deleteObjectsByUserId(Integer userId);
	
	
}
