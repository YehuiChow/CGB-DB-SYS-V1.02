package com.cy.pj.sys.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.pj.common.exception.ServiceException;
import com.cy.pj.sys.dao.SysMenuDao;
import com.cy.pj.sys.dao.SysRoleMenuDao;
import com.cy.pj.sys.service.SysMenuService;

@Service
public class SysMenuSeriveImpl implements SysMenuService {
	
	@Autowired
	private SysMenuDao sysMenuDao;
	@Autowired
	private SysRoleMenuDao sysRoleMenuDao;
	@Override
	public List<Map<String, Object>> findObjects() {
		List<Map<String, Object>> list = sysMenuDao.findObjects();
		if (list.size()==0)
			throw new ServiceException("no records");
		return list;
	}
	@Override
	public int deleteObject(Integer id) {
		//1.参数校验
		if(id==null||id<1) 
			throw new IllegalArgumentException("id值无效");
		//2.基于id统计子菜单并校验
		int rowcount = sysMenuDao.getChildObject(id);
		if (rowcount>0) 
			throw new ServiceException("请先删除子菜单");
		//3.删除菜单角色关系数据
		sysRoleMenuDao.deleteObjectsByMenuId(id);
		//4.删除菜单自身信息
		int rows = sysMenuDao.deleteObjects(id);
		if(rows==0) 
			throw new ServiceException("菜单可能已经不存在");
		//5.返回结果
		return rows;
	}

}
