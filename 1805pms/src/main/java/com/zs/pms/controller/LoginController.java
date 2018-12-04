package com.zs.pms.controller;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zs.pms.exception.AppException;
import com.zs.pms.po.TUser;
import com.zs.pms.service.UserService;
import com.zs.pms.utils.DateUtil;
import com.zs.pms.vo.QueryUser;
/**
 * 登录页和主页
 * @author Administrator
 *
 */
@Controller //控制器
public class LoginController {

	@Autowired
	UserService us;	//注入业务
	
	@RequestMapping("/tologin.do")
	//去登录页
	public String toLogin(){
		return "login";
	}
	
	/**
	 * 检测登录
	 * @param query	登录名和密码
	 * @param code	验证码
	 * @param session 产生会话
	 * @param map	  回带数据
	 * @return		  返回页面
	 */
	@RequestMapping("/login.do")	//url
	public String login(QueryUser query,String code,HttpSession session,ModelMap map){
		//验证码验证
		//从session中取得kaptcha生成的验证码
		String ocode=(String) session.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		
		//验证码不同
		if (!ocode.equals(code)) {
			//页面回带信息
			map.addAttribute("msg","验证码输入有误，请重新输入");
			//回到登录页面
			return "login";
			
		}
		//验证码正确，校验登录
		TUser user;
		
		try {
			user=us.chkLogin(query);
			session.setAttribute("CUSER", user);
			//当前日期
			session.setAttribute("TODAY", DateUtil.getStrDate(new Date()));
			//转发主页面
			return "main";
			
		} 
		//业务异常
		catch (AppException e) {
			// TODO Auto-generated catch block
			//页面带信息
			map.addAttribute("msg",e.getErrMsg());
			//回到登录页面
			return "login";
		}
		//系统异常
		catch (Exception e1) {
			// TODO: handle exception
			e1.printStackTrace();
			return "error";
		}
			
		
	}
		
		
	//去left页	
	@RequestMapping("/toleft.do")
	public String toLeft(){
		return "left";
	}
	
	//去right页
	@RequestMapping("/toright.do")
	public String toRight(){
		return "right";
	}
	
	//去top页
	@RequestMapping("/totop.do")
	public String toTop(){
		return "top";
	}
		
		
		
		
		
		
		
		
		
		
	
}
