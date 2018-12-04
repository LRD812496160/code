package com.zs.pms.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zs.pms.exception.AppException;
import com.zs.pms.po.TDep;
import com.zs.pms.po.TUser;
import com.zs.pms.service.DepService;
import com.zs.pms.service.UserService;
import com.zs.pms.vo.QueryUser;

/**
 * �û�������
 * @author Administrator
 *
 */
@Controller
public class UserController {

	@Autowired
	UserService us; //�û�����
	
	@Autowired
	
	DepService ds;  //���ŷ���
	
	@RequestMapping("/user/list.do") //����url
	public String list(QueryUser query,String page,ModelMap map){
		//page�ǿ�
		if (page==null||"".equals(page)) {
			page="1"; //Ĭ�ϵ�һҳ
			
		}
		//�ش���ҳ����
		map.addAttribute("LIST",us.queryByPage(query, Integer.parseInt(page)));
		//�ش���ҳ��
		map.addAttribute("PAGECOUNT",us.queryPageCount(query));
		//�ش���ǰҳ
		map.addAttribute("PAGE",page);
		//�ش���ѯ����
		map.addAttribute("QUERY",query);
		//����user/list.jsp
		return "user/list";
		
	}
	
	@RequestMapping("/user/toadd.do")
	public String toadd(ModelMap map){
		//���һ�������б�
		List<TDep> list1=ds.queryByPid(0);
		map.addAttribute("DLIST",list1);
		//���Ĭ��һ�������µĶ�������
		List<TDep> list2=ds.queryByPid(list1.get(0).getId());
		map.addAttribute("DLIST2",list2);
		
		return "user/add";
	}
	
	
	/**
	 * ����ajax���󣬲��践��ҳ��
	 * ��ҳ�淵���������ͼ���
	 */
	@RequestMapping("/user/getdep.do")
	@ResponseBody
	public List<TDep> getDep(int pid){
		return ds.queryByPid(pid);
	}
	
	@RequestMapping("/user/add.do")
	public String add(TUser user,ModelMap map,HttpSession session){
		
		try {
			//���session�е��û���Ϣ
			TUser cuser=(TUser) session.getAttribute("CUSER");
			user.setCreator(cuser.getId());	//������
			user.setIsenabled(1);			//����
			us.insert(user);
			//��ת��ָ��url�� ����Ҫ����ʱ
			return "redirect:list.do";
		} catch (AppException e) {
			// TODO: handle exception
			map.addAttribute("msg",e.getErrMsg());
			//���÷�������Ҫ����ʱ
			return this.toadd(map);
		}
	}
	
	/**
	 * ɾ��һ��
	 * @param id
	 * @return
	 */
	@RequestMapping("/user/delete.do")
	public String delete(int id){
		try {
			us.delete(id);
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:list.do";
	}
	
	/**
	 * ɾ������
	 * @param id
	 * @return
	 */
	@RequestMapping("/user/deletes.do")
	public String deletes(int[] ids,ModelMap map){
		try {
			us.deleteByIds(ids);
			return "redirect:list.do";
		} catch (AppException e) {
			// TODO Auto-generated catch block
			map.addAttribute("msg",e.getErrMsg());
			return "redirect:list.do";
		}
		
	}
	
	/**
	 * ��ȡҪ�޸ĵ�����
	 * @param id
	 * @param map
	 * @return
	 */
	@RequestMapping("/user/get.do")
	public String get(int id,ModelMap map){
		TUser user=us.queryById(id);
		map.addAttribute("USER",user);
		//���һ�������б�
		List<TDep> list1=ds.queryByPid(0);
		map.addAttribute("DLIST",list1);
		//��ø��û���һ�������µĶ��������б�
		List<TDep> list2=ds.queryByPid(user.getDept().getPid());
		map.addAttribute("DLIST2",list2);
		
		return "user/update";
	}
	
	@RequestMapping("/user/update.do")
	public String update(TUser user,HttpSession session,ModelMap map){
		//���session�е��û���Ϣ
		TUser cuser=(TUser) session.getAttribute("CUSER");
		user.setUpdator(cuser.getId());
		try {
			us.update(user);
			return "redirect:list.do";
		} catch (AppException e) {
			// TODO Auto-generated catch block
			map.addAttribute("msg",e.getErrMsg());
			return get(user.getId(), map);
		}
	}
	
	
	
	
	
	
	
	
}
