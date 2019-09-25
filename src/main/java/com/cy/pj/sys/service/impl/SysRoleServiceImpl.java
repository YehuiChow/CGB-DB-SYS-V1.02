package com.cy.pj.sys.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.pj.common.exception.ServiceException;
import com.cy.pj.common.vo.PageObject;
import com.cy.pj.sys.dao.SysRoleDao;
import com.cy.pj.sys.dao.SysRoleMenuDao;
import com.cy.pj.sys.dao.SysUserRoleDao;
import com.cy.pj.sys.entity.SysRole;
import com.cy.pj.sys.service.SysRoleService;

@Service
public class SysRoleServiceImpl implements SysRoleService{
	@Autowired
	private SysRoleDao sysRoleDao;
	@Autowired
	private SysRoleMenuDao sysRoleMenuDao;
	@Autowired
	private SysUserRoleDao sysUserRoleDao;

	@Override
	public PageObject<SysRole> findPageObjects(String name, Integer pageCurrent) {
		//1.对参数进行校验
		if(pageCurrent==null || pageCurrent<1)
			throw new IllegalArgumentException("当前页码值无效");
		//2.查询总记录数并进行校验
		int rowCount = sysRoleDao.getRowCount(name);
		//3.查询当前页记录
		if(rowCount==0)
			throw new ServiceException("没有找到对应记录");
		//4.对查询记录进行封装并返回
		int pageSize = 5;//每页显示数据量
		int startIndex = (pageCurrent-1)*pageSize;//当前页的首行数据的索引(0,1,2,3...),比如当前页为2时,第一条数据的索引就为(2-1)*5=5
		//将参数传给Dao层的方法
		List<SysRole> records = sysRoleDao.findPageObjects(name, startIndex, pageSize);
		//封装查询到的数据以及分页信息并返回
		return new PageObject<>(pageCurrent, pageSize, rowCount, records);
	}

	@Override
	public int deleteObjectsByRoleId(Integer roleId) {
		//1.参数校验
		if(roleId==null) 
			throw new ServiceException("参数值不存在");
		//2.执行删除
		sysRoleMenuDao.deleteObjectsByRoleId(roleId);
		sysUserRoleDao.deleteObjectsByRoleId(roleId);
		int rows = sysRoleDao.deleteObjectsByRoleId(roleId);
		if(rows == 0)
			throw new ServiceException("记录可能已经不存在");
		//3.返回结果
		return rows;
	}



}
