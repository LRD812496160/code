package com.zs.pms.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zs.pms.dao.ChanDao;
import com.zs.pms.po.TChan;
import com.zs.pms.service.ChanService;

@Service
public class ChanServiceImpl implements ChanService{

	@Autowired
	ChanDao dao;

	@Override
	public List<TChan> queryByPid(int pid) {
		// TODO Auto-generated method stub
		return dao.queryByPid(pid);
	}

	@Override
	public List<TChan> queryById(int id) {
		// TODO Auto-generated method stub
		return dao.queryById(id);
	}
	
	
}
