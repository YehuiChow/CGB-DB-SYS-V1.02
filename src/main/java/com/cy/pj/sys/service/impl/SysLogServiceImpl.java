package com.cy.pj.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.pj.common.exception.ServiceException;
import com.cy.pj.common.vo.PageObject;
import com.cy.pj.sys.dao.SysLogDao;
import com.cy.pj.sys.entity.SysLog;
import com.cy.pj.sys.service.SysLogService;
@Service
public class SysLogServiceImpl implements SysLogService {
	@Autowired
	private SysLogDao sysLogDao;
	@Override
	public PageObject<SysLog> findPageObjects(String username, Integer pageCurrent) {
		//1.对参数进行校验
		if (pageCurrent==null || pageCurrent<1) 
			throw new IllegalArgumentException("当前页码值无效");
		//2.查询总记录数并进行校验
		int rowCount = sysLogDao.getRowCount(username);
		//3.查询当前页记录
		if (rowCount==0)
			throw new ServiceException("没有找到对应记录");
		//4.对查询记录进行封装并返回
		int pageSize = 5;
		int startIndex = (pageCurrent-1)*pageSize;
		List<SysLog> records = sysLogDao.findPageObjects(username, startIndex, pageSize);
		return new PageObject<>(pageCurrent, pageSize, rowCount, records);
	}
	@Override
	public int deleteObjects(Integer... ids) {
		//1.参数校验
		if(ids==null||ids.length==0) 
			throw new ServiceException("参数值不存在");
		//2.执行删除
		int rows = sysLogDao.deleteObjects(ids);
		if(rows==0) 
			throw new ServiceException("记录可能已经不存在");
		//3.返回结果
		return rows;
	}

}
