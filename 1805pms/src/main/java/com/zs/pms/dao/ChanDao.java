package com.zs.pms.dao;

import java.util.List;

import com.zs.pms.po.TChan;

public interface ChanDao {
	//�����ϼ�idȡ������Ϣ
	public List<TChan> queryByPid(int pid);
	
	public List<TChan> queryById(int id);
	
}
