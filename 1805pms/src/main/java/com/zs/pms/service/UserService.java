package com.zs.pms.service;

import java.util.List;

import com.zs.pms.exception.AppException;
import com.zs.pms.po.TUser;
import com.zs.pms.vo.QueryUser;

public interface UserService {
	//0.登录
	public TUser chkLogin(QueryUser query) throws AppException;
	//1.通过条件查询
	public List<TUser> queryByCon(QueryUser query);
	//2.查分页
	public List<TUser> queryByPage(QueryUser query,int page);
	//3.根据主键查询
	public TUser queryById(int id);
	//4.批量删除
	public void deleteByIds(int[] ids) throws AppException;
	//5.修改
	public void update(TUser user) throws AppException;
	//6.新增
	public int insert(TUser user) throws AppException;
	//7.删除一条
	public void delete(int id) throws AppException;
	//8.查询总页数
	public int queryPageCount(QueryUser query);
}
