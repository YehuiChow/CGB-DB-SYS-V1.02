package com.cy.pj.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cy.pj.sys.entity.SysLog;

/**日志模块数据层
 * 1)接收业务层参数数据(页码值,用户名,...)
 * 2)基于参数进行数据查询
 * 3)将查询结果进行封装(SysLog)
 * 4)将结果返回业务层对象
 */
@Mapper
public interface SysLogDao {
	/**分页查询当前日志信息
	 * @param username 查询条件
	 * @param startIndex 当前页起始位置
	 * @param pageSize 当前页面大小
	 * @return 当前页查询到的记录
	 */
	List<SysLog> findPageObjects(@Param("username")String username,@Param("startIndex")Integer startIndex,@Param("pageSize")Integer pageSize);
	/**依据条件查询日记记录总数
	 * @param username 查询条件
	 * @return 查询到记录总数
	 */
	int getRowCount(@Param("username")String username);
	/**
	 * 基于id执行删除操作
	 * @param id
	 * @return
	 */
	int deleteObjects(@Param("ids")Integer...ids);
}
