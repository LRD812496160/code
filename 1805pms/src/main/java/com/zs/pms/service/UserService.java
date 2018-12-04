package com.zs.pms.service;

import java.util.List;

import com.zs.pms.exception.AppException;
import com.zs.pms.po.TUser;
import com.zs.pms.vo.QueryUser;

public interface UserService {
	//0.��¼
	public TUser chkLogin(QueryUser query) throws AppException;
	//1.ͨ��������ѯ
	public List<TUser> queryByCon(QueryUser query);
	//2.���ҳ
	public List<TUser> queryByPage(QueryUser query,int page);
	//3.����������ѯ
	public TUser queryById(int id);
	//4.����ɾ��
	public void deleteByIds(int[] ids) throws AppException;
	//5.�޸�
	public void update(TUser user) throws AppException;
	//6.����
	public int insert(TUser user) throws AppException;
	//7.ɾ��һ��
	public void delete(int id) throws AppException;
	//8.��ѯ��ҳ��
	public int queryPageCount(QueryUser query);
}
