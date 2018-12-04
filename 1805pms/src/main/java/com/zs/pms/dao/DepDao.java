package com.zs.pms.dao;

import java.util.List;

import com.zs.pms.po.TDep;

public interface DepDao {
	//根据上级id取部门信息
	public List<TDep> queryByPid(int pid);
}
