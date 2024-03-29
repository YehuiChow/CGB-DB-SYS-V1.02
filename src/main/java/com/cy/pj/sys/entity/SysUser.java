package com.cy.pj.sys.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class SysUser implements Serializable{
	private static final long serialVersionUID = -2084506667460906909L;
	private Integer id;
	private String username;
	private String password;//md5
	/**盐值(与密码一起加密,保证密码更加安全)*/
	private String salt;    
	private String email;
	private String mobile;
	/**用户状态值:禁用、启用*/
	private Integer valid=1;
	private Integer deptId;
	private Date createdTime;
	private Date modifiedTime;
	private String createdUser;
	private String modifiedUser;
}
