package com.cy.pj.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.cy.pj.sys.entity.SysRole;

@Mapper
public interface SysRoleDao {
	int getRowCount(@Param("username")String username);
	List<SysRole> findPageObjects(@Param("username")String username,@Param("startIndex")Integer startIndex,@Param("pageSize")Integer pageSize);
}
