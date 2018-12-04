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
 * ��¼ҳ����ҳ
 * @author Administrator
 *
 */
@Controller //������
public class LoginController {

	@Autowired
	UserService us;	//ע��ҵ��
	
	@RequestMapping("/tologin.do")
	//ȥ��¼ҳ
	public String toLogin(){
		return "login";
	}
	
	/**
	 * ����¼
	 * @param query	��¼��������
	 * @param code	��֤��
	 * @param session �����Ự
	 * @param map	  �ش�����
	 * @return		  ����ҳ��
	 */
	@RequestMapping("/login.do")	//url
	public String login(QueryUser query,String code,HttpSession session,ModelMap map){
		//��֤����֤
		//��session��ȡ��kaptcha���ɵ���֤��
		String ocode=(String) session.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		
		//��֤�벻ͬ
		if (!ocode.equals(code)) {
			//ҳ��ش���Ϣ
			map.addAttribute("msg","��֤��������������������");
			//�ص���¼ҳ��
			return "login";
			
		}
		//��֤����ȷ��У���¼
		TUser user;
		
		try {
			user=us.chkLogin(query);
			session.setAttribute("CUSER", user);
			//��ǰ����
			session.setAttribute("TODAY", DateUtil.getStrDate(new Date()));
			//ת����ҳ��
			return "main";
			
		} 
		//ҵ���쳣
		catch (AppException e) {
			// TODO Auto-generated catch block
			//ҳ�����Ϣ
			map.addAttribute("msg",e.getErrMsg());
			//�ص���¼ҳ��
			return "login";
		}
		//ϵͳ�쳣
		catch (Exception e1) {
			// TODO: handle exception
			e1.printStackTrace();
			return "error";
		}
			
		
	}
		
		
	//ȥleftҳ	
	@RequestMapping("/toleft.do")
	public String toLeft(){
		return "left";
	}
	
	//ȥrightҳ
	@RequestMapping("/toright.do")
	public String toRight(){
		return "right";
	}
	
	//ȥtopҳ
	@RequestMapping("/totop.do")
	public String toTop(){
		return "top";
	}
		
		
		
		
		
		
		
		
		
		
	
}
