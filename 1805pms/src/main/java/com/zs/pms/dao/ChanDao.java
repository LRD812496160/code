package com.zs.pms.dao;

import java.util.List;

import com.zs.pms.po.TChan;

public interface ChanDao {
	//根据上级id取部门信息
	public List<TChan> queryByPid(int pid);
	
	public List<TChan> queryById(int id);
	
}
