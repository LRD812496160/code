package com.zs.pms.dao;

import com.zs.pms.po.TUser;
import com.zs.pms.vo.QueryUser;

import java.util.List;

/**
 * �û������ݽӿ�
 * @author Administrator
 *
 */

public interface UserDao {

	//1.ͨ��������ѯ
	public List<TUser> queryByCon(QueryUser query);
	//2.���ҳ
	public List<TUser> queryByPage(QueryUser query);
	//3.����������ѯ
	public TUser queryById(int id);
	//4.����ɾ��
	public void deleteByIds(int[] ids);
	//5.�޸�
	public void update(TUser user);
	//6.����
	public int insert(TUser user);
	//7.ɾ��һ��
	public void delete(int id);
	//8.��ѯ����
	public int queryCount(QueryUser query);
}
