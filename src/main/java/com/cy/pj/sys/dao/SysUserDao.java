package com.cy.pj.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cy.pj.sys.entity.SysUser;
import com.cy.pj.sys.vo.SysUserDeptVo;

@Mapper
public interface SysUserDao {
	/**
	 * 基于用户名统计用户总数
	 * @param username
	 */
	int getRowCount(@Param("username")String username);
	/**
	 * 分页查询所用用户信息
	 * @param username
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	List<SysUserDeptVo> findPageObjects(@Param("username")String username,@Param("startIndex")Integer startIndex,@Param("pageSize")Integer pageSize);
	int updateValidById(@Param("id")Integer id, @Param("valid")Integer valid,@Param("modifiedUser")String modifiedUser);
	/** 根据*/
	SysUserDeptVo findObjectById(Integer id);
	
	/**保存用户自身信息*/
	int insertObject(SysUser sysUser);
	int updateObject(SysUser entity);
	
	/**更新用户信息*/
}
