package com.zs.pms.dao;

import java.util.List;

import com.zs.pms.po.TDep;

public interface DepDao {
	//�����ϼ�idȡ������Ϣ
	public List<TDep> queryByPid(int pid);
}
