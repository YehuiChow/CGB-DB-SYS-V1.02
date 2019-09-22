package com.cy.pj.sys.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cy.pj.common.vo.JsonResult;
import com.cy.pj.sys.entity.SysDept;
import com.cy.pj.sys.service.SysDeptService;

@RestController
@RequestMapping("/dept/")
public class SysDeptController {
	
	@Autowired
	private SysDeptService sysDeptService;
	
	@RequestMapping("doFindObjects")
	public JsonResult doFindObjects() {
		return new JsonResult(sysDeptService.findObjects());
	}
	
	@RequestMapping("doDeleteObject")
	public JsonResult doDeleteObject(Integer id) {
		sysDeptService.deleteObject(id);
		return new JsonResult("delete ok");
	}
	
	@RequestMapping("doFindZTreeDeptNodes")
	public JsonResult doFindZtreeDeptNodes() {
		return new JsonResult(sysDeptService.findZtreeMenuNodes());
	}
	
	@RequestMapping("doSaveObject")
	public JsonResult doSaveObject(SysDept sysDept) {
		sysDeptService.saveObject(sysDept);
		return new JsonResult("save ok");
	}
	
	@RequestMapping("doUpdateObject")
	public JsonResult doUpdateObject(SysDept sysDept ) {
		sysDeptService.updateObject(sysDept);
		return new JsonResult("update ok");
	}
}
