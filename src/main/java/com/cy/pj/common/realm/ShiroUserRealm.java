package com.cy.pj.common.realm;


import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.pj.sys.dao.SysUserDao;

@Service
public class ShiroUserRealm extends AuthorizingRealm {
	@Autowired
	private SysUserDao sysUserDao;
	
	/**
	 * 	设置凭证匹配器,通过此对象指定加密算法
	 */
	@Override
	public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
		
		super.setCredentialsMatcher(credentialsMatcher);
	}
	/**
	 *	 负责认证信息的获取和封装
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		//1. 获取用户名(从token获取)
		//2.基于用户名查询用户对象
		//3.判断用户是否存在
		//4.判断用户是否被禁用
		//5.封装用户信息并返回,传递给认证管理器进行认证
		return null;
	}
	
	/**
	 * 	负责授权信息的获取和封装
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
	
		return null;
	}
	

}
