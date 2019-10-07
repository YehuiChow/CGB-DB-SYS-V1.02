package com.cy.pj.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.cy.pj.sys.entity.SysRole;
import com.cy.pj.sys.vo.SysRoleMenuVo;

@Mapper
public interface SysRoleDao {
	int getRowCount(@Param("name")String name);
	List<SysRole> findPageObjects(@Param("name")String name,@Param("startIndex")Integer startIndex,@Param("pageSize")Integer pageSize);
	int deleteObjectsByRoleId(@Param("roleId")Integer roleId);
	SysRoleMenuVo findObjectById(Integer id);
	int saveObject(SysRole sysRole);
	int updateObject(SysRole sysRole);
}
