package com.cy.pj.sys.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.cy.pj.common.exception.ServiceException;
import com.cy.pj.common.vo.Node;
import com.cy.pj.sys.dao.SysDeptDao;
import com.cy.pj.sys.entity.SysDept;
import com.cy.pj.sys.service.SysDeptService;

import lombok.extern.slf4j.Slf4j;
@Slf4j//添加日志
@Service
public class SysDeptServiceImpl implements SysDeptService {
	
	@Autowired
	private SysDeptDao sysDeptDao;
	
	@Override
	public List<Map<String, Object>> findObjects() {
		List<Map<String, Object>> list = sysDeptDao.findObjects();
		if (list.size()==0)
			throw new ServiceException("no records");
		return list;
	}
	
	@Override
	public int deleteObject(Integer id) {
		//1.参数校验
		if(id==null||id<1) 
			throw new IllegalArgumentException("id值无效");
		//2.基于id统计子部门并校验
		int rowcount = sysDeptDao.getChildObjects(id);
		if (rowcount>0) 
			throw new ServiceException("请先删除子部门");
		//3.删除部门自身信息
		int rows = sysDeptDao.deleteObject(id);
		if(rows==0) 
			throw new ServiceException("部门可能已经不存在");
		//4.返回结果
		return rows;
	}
	
	@Override
	public List<Node> findZtreeMenuNodes() {
		return sysDeptDao.findZtreeMenuNodes();
	}

	@Override
	public int saveObject(SysDept sysDept) {
		//1.参数校验
		if(sysDept==null)
			throw new IllegalArgumentException("保存对象不能为空");
		if(StringUtils.isEmpty(sysDept.getName())) 
			throw new IllegalArgumentException("部门名称不能为空");
		//保存菜单对象
		int rows=0;
		try {
			rows = sysDeptDao.insertObject(sysDept);
		} catch (Throwable e) {
			log.error(e.getMessage());//log是日志对象,添加@Slf4j后可以直接使用
			//send msg
			throw new ServiceException("系统维护中");
		}
		//返回结果
		return rows;
	}

	@Override
	public int updateObject(SysDept sysDept) {
		//参数校验
		if(sysDept==null)
			throw new IllegalArgumentException("保存对象不能为空");
		if(StringUtils.isEmpty(sysDept.getName())) 
			throw new IllegalArgumentException("部门名称不能为空");

		//保存菜单对象
		int rows=0;
		try {
			rows = sysDeptDao.updateObject(sysDept);
		} catch (Throwable e) {
			log.error(e.getMessage());//log是日志对象,添加@Slf4j后可以直接使用
			//send msg
			throw new ServiceException("系统维护中");
		}
		//返回结果
		return rows;
	}

}
