package com.zs.pms.serviceimpl;

import org.springframework.stereotype.Service;

/**
 * 模拟业务
 * @author Administrator
 *
 */
@Service
public class LoginServiceImpl {

	public String getWelcome(){
		return "登录界面";
	}
}
