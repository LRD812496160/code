package com.zs.pms.service;

import java.util.List;

import com.zs.pms.po.TChan;

public interface ChanService {

	public List<TChan> queryByPid(int pid);
	
	public List<TChan> queryById(int id);
}
