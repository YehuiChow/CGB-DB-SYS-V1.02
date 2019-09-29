package com.cy.pj.sys.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cy.pj.common.vo.JsonResult;
import com.cy.pj.common.vo.PageObject;
import com.cy.pj.sys.entity.SysRole;
import com.cy.pj.sys.service.SysRoleService;

@RestController
@RequestMapping("/role/")
public class SysRoleController {
	
	@Autowired
	private SysRoleService sysRoleService;
	
	@RequestMapping("doFindPageObjects")
	public JsonResult dofindPageObjects(String name,Integer pageCurrent) {
		PageObject<SysRole> pageObject = sysRoleService.findPageObjects(name, pageCurrent);
		return new JsonResult(pageObject);
	}
	 
	@RequestMapping("doDeleteObject")
	public JsonResult doDeleteObject(Integer id) {
		sysRoleService.deleteObjectsByRoleId(id);
		return new JsonResult("delete ok");
	}
	
	@RequestMapping("doFindObjectById")
	public JsonResult doFindObjectById(Integer id) {
		SysRole sysRole = sysRoleService.findObjectById(id);
		return new JsonResult(sysRole);
	}
	
	@RequestMapping("doSaveObject")
	public JsonResult doSaveObject(SysRole sysRole) {
		sysRoleService.saveObject(sysRole);
		return new JsonResult("save ok");
	}
	
	@RequestMapping("doUpdateObject")
	public JsonResult doUpdateObject(SysRole sysRole) {
		sysRoleService.updateObjectById(sysRole);
		return new JsonResult("update ok");
	}
	

}
