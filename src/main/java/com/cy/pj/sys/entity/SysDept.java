package com.cy.pj.sys.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class SysDept implements Serializable{
	private static final long serialVersionUID = 8876920804134951849L;
	private Integer id;
	/**菜单名称*/
	private String name;
	/**排序(序号)*/
	private Integer sort;
	/**备注*/
	private String note;
	/**上级菜单id*/
	private Integer parentId;
	/**创建用户*/
	private String createdUser;
	/**修改用户*/
	private String modifiedUser;
	private Date createdTime;
	private Date modifiedTime;
}
