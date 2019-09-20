package com.cy.pj.sys.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.cy.pj.common.exception.ServiceException;
import com.cy.pj.common.vo.Node;
import com.cy.pj.sys.dao.SysMenuDao;
import com.cy.pj.sys.dao.SysRoleMenuDao;
import com.cy.pj.sys.entity.SysMenu;
import com.cy.pj.sys.service.SysMenuService;

import lombok.extern.slf4j.Slf4j;
@Slf4j//添加日志
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
		int rowcount = sysMenuDao.getChildObjects(id);
		if (rowcount>0) 
			throw new ServiceException("请先删除子菜单");
		//3.删除菜单角色关系数据
		sysRoleMenuDao.deleteObjectsByMenuId(id);
		//4.删除菜单自身信息
		int rows = sysMenuDao.deleteObject(id);
		if(rows==0) 
			throw new ServiceException("菜单可能已经不存在");
		//5.返回结果
		return rows;
	}
	
	@Override
	public List<Node> findZtreeMenuNodes() {
		return sysMenuDao.findZtreeMenuNodes();
	}

	@Override
	public int saveObject(SysMenu sysMenu) {
		//1.参数校验
		if(sysMenu==null)
			throw new IllegalArgumentException("保存对象不能为空");
		if(StringUtils.isEmpty(sysMenu.getName())) 
			throw new IllegalArgumentException("菜单名不能为空");
		if(StringUtils.isEmpty(sysMenu.getPermission()))
			throw new IllegalArgumentException("授权标识不能为空");
		if (StringUtils.isEmpty(sysMenu.getUrl()))
			throw new IllegalArgumentException("菜单URL不能为空");
		//保存菜单对象
		int rows=0;
		try {
			rows = sysMenuDao.insertObject(sysMenu);
		} catch (Throwable e) {
			log.error(e.getMessage());//log是日志对象,添加@Slf4j后可以直接使用
			//send msg
			throw new ServiceException("系统维护中");
		}
		//返回结果
		return rows;
	}

}
