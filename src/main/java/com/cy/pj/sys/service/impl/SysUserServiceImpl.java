package com.cy.pj.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.pj.common.vo.PageObject;
import com.cy.pj.sys.dao.SysUserDao;
import com.cy.pj.sys.service.SysUserService;
import com.cy.pj.sys.vo.SysUserDeptVo;

@Service
public class SysUserServiceImpl implements SysUserService {
	@Autowired
	private SysUserDao sysUserDao;
	@Override
	public PageObject<SysUserDeptVo> findPageObjects(String username, Integer pageCurrent) {
		//1.参数校验
		if (pageCurrent == null || pageCurrent <1) 
			throw new IllegalArgumentException("页码不正确");
		//2.查询总记录数并校验
		int rowCount = sysUserDao.getRowCount(username); 
		if(rowCount == 0)
			throw new IllegalArgumentException("没有对应记录");
		//3.查询当前页记录
		int pageSize=3;
		int startIndex = (pageCurrent-1)*pageSize;
		List<SysUserDeptVo> records = sysUserDao.findPageObjects(username, startIndex, pageSize);
		//4.对查询结果进行封装并返回
		return new PageObject<>(pageCurrent, pageSize, rowCount, records);
	}
	@Override
	public Integer doValidById(Integer id, Integer valid,String modifiedUser) {
		//1.参数校验
		if(id == null || id<1)
			throw new IllegalArgumentException("用户不存在");
		if(valid!=0 && valid!=1 )
			throw new IllegalArgumentException("该用户状态信息出错");
		//2.修改禁用/启用信息
		int row = sysUserDao.updateValidById(id,valid,modifiedUser);
		if (row == 0)
			throw new IllegalArgumentException("用户可能已经不存在");
		return row;
	}
	@Override
	public SysUserDeptVo findObjectById(Integer id) {
		SysUserDeptVo sysUserDeptVo = sysUserDao.findObjectById(id);
		return sysUserDeptVo;
	}
	
}
