package com.cy.pj.sys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.cy.pj.common.exception.ServiceException;
import com.cy.pj.common.vo.PageObject;
import com.cy.pj.sys.dao.SysUserDao;
import com.cy.pj.sys.dao.SysUserRoleDao;
import com.cy.pj.sys.entity.SysUser;
import com.cy.pj.sys.service.SysUserService;
import com.cy.pj.sys.vo.SysUserDeptVo;

@Service
public class SysUserServiceImpl implements SysUserService {
	@Autowired
	private SysUserDao sysUserDao;
	@Autowired
	private SysUserRoleDao sysUserRoleDao;
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
	public Map<String , Object> findObjectById(Integer id) {
		Map<String, Object> map = new HashMap<String, Object>();
		//1.参数校验
		if (id == null || id < 1)
			throw new IllegalArgumentException("用户不存在");
		//2.根据用户id查询用户信息
		SysUserDeptVo sysUserDeptVo = sysUserDao.findObjectById(id);
		if (sysUserDeptVo == null) 
			throw new IllegalArgumentException("用户信息可能已经不存在");
		//3.根据用户id查询角色id
		Integer[] roleIds = sysUserRoleDao.findRoleIdsByUserId(sysUserDeptVo.getId());
		map.put("user", sysUserDeptVo);
		map.put("roleIds", roleIds);
		//3.返回结果
		return map;
	}
	@Override
	public int saveObject(SysUser sysUser, Integer[] roleIds) {
		//1.参数校验
		if (sysUser == null)
			throw new ServiceException("保存对象不能为空");
		if (StringUtils.isEmpty(sysUser.getUsername()))
			throw new ServiceException("用户名不能为空");
		if (StringUtils.isEmpty(sysUser.getPassword()))
			throw new ServiceException("密码不能为空");
		if (StringUtils.isEmpty(sysUser.getDeptId()))
			throw new ServiceException("请选择所属部门");
		if (StringUtils.isEmpty(sysUser.getEmail()))
			throw new ServiceException("邮箱不能为空");
		if (StringUtils.isEmpty(sysUser.getMobile()))
			throw new ServiceException("手机号不能为空");
		if (roleIds == null || roleIds.length == 0) 
			throw new ServiceException("请选择角色");
		//2.保存用户自身信息
		//2.1对密码进行加密
		String source = sysUser.getPassword();
		String salt = UUID.randomUUID().toString();
		//shiro框架
		SimpleHash sh = new SimpleHash("MD5",//算法名称
										source,//原密码
										salt,//盐值
										1);//加密次数
		sysUser.setSalt(salt);
		sysUser.setPassword(sh.toHex());
		int rows;
		try {
			rows = sysUserDao.insertObject(sysUser);
		} catch (Exception e) {
			throw new ServiceException("该用户名已被占用");
		}
		//3.保存用户角色关系数据
		sysUserRoleDao.insertObjects(sysUser.getId(), roleIds);
		//3.返回结果
		return rows;
	}
	@Override
	public int updateObject(SysUser sysUser, Integer[] roleIds) {
		//1.参数校验
		if (sysUser == null)
			throw new ServiceException("保存对象不能为空");
		if (StringUtils.isEmpty(sysUser.getUsername()))
			throw new ServiceException("用户名不能为空");
		if (StringUtils.isEmpty(sysUser.getDeptId()))
			throw new ServiceException("请选择所属部门");
		if (StringUtils.isEmpty(sysUser.getEmail()))
			throw new ServiceException("邮箱不能为空");
		if (StringUtils.isEmpty(sysUser.getMobile()))
			throw new ServiceException("手机号不能为空");
		if (roleIds == null || roleIds.length == 0) 
			throw new ServiceException("请选择角色");
		int rows;
		try {
			rows = sysUserDao.updateObject(sysUser);
		} catch (Exception e) {
			throw new ServiceException("该用户名已被占用");
		}
		sysUserRoleDao.deleteObjectsByUserId(sysUser.getId());
		sysUserRoleDao.insertObjects(sysUser.getId(), roleIds);
		return rows;
	}
	
}
