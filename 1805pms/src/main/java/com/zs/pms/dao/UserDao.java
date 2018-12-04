package com.zs.pms.dao;

import com.zs.pms.po.TUser;
import com.zs.pms.vo.QueryUser;

import java.util.List;

/**
 * 用户表数据接口
 * @author Administrator
 *
 */

public interface UserDao {

	//1.通过条件查询
	public List<TUser> queryByCon(QueryUser query);
	//2.查分页
	public List<TUser> queryByPage(QueryUser query);
	//3.根据主键查询
	public TUser queryById(int id);
	//4.批量删除
	public void deleteByIds(int[] ids);
	//5.修改
	public void update(TUser user);
	//6.新增
	public int insert(TUser user);
	//7.删除一条
	public void delete(int id);
	//8.查询总数
	public int queryCount(QueryUser query);
}
