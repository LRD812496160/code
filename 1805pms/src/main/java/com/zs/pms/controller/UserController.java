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
 * 用户控制器
 * @author Administrator
 *
 */
@Controller
public class UserController {

	@Autowired
	UserService us; //用户服务
	
	@Autowired
	
	DepService ds;  //部门服务
	
	@RequestMapping("/user/list.do") //二级url
	public String list(QueryUser query,String page,ModelMap map){
		//page是空
		if (page==null||"".equals(page)) {
			page="1"; //默认第一页
			
		}
		//回带分页数据
		map.addAttribute("LIST",us.queryByPage(query, Integer.parseInt(page)));
		//回带总页数
		map.addAttribute("PAGECOUNT",us.queryPageCount(query));
		//回带当前页
		map.addAttribute("PAGE",page);
		//回带查询条件
		map.addAttribute("QUERY",query);
		//返回user/list.jsp
		return "user/list";
		
	}
	
	@RequestMapping("/user/toadd.do")
	public String toadd(ModelMap map){
		//获得一级部门列表
		List<TDep> list1=ds.queryByPid(0);
		map.addAttribute("DLIST",list1);
		//获得默认一级部门下的二级部门
		List<TDep> list2=ds.queryByPid(list1.get(0).getId());
		map.addAttribute("DLIST2",list2);
		
		return "user/add";
	}
	
	
	/**
	 * 处理ajax请求，不需返回页面
	 * 向页面返回数据类型即可
	 */
	@RequestMapping("/user/getdep.do")
	@ResponseBody
	public List<TDep> getDep(int pid){
		return ds.queryByPid(pid);
	}
	
	@RequestMapping("/user/add.do")
	public String add(TUser user,ModelMap map,HttpSession session){
		
		try {
			//获得session中的用户信息
			TUser cuser=(TUser) session.getAttribute("CUSER");
			user.setCreator(cuser.getId());	//创建人
			user.setIsenabled(1);			//可用
			us.insert(user);
			//跳转到指定url： 不需要传参时
			return "redirect:list.do";
		} catch (AppException e) {
			// TODO: handle exception
			map.addAttribute("msg",e.getErrMsg());
			//调用方法：需要传参时
			return this.toadd(map);
		}
	}
	
	/**
	 * 删除一条
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
	 * 删除多条
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
	 * 获取要修改的数据
	 * @param id
	 * @param map
	 * @return
	 */
	@RequestMapping("/user/get.do")
	public String get(int id,ModelMap map){
		TUser user=us.queryById(id);
		map.addAttribute("USER",user);
		//获得一级部门列表
		List<TDep> list1=ds.queryByPid(0);
		map.addAttribute("DLIST",list1);
		//获得该用户的一级部门下的二级部门列表
		List<TDep> list2=ds.queryByPid(user.getDept().getPid());
		map.addAttribute("DLIST2",list2);
		
		return "user/update";
	}
	
	@RequestMapping("/user/update.do")
	public String update(TUser user,HttpSession session,ModelMap map){
		//获得session中的用户信息
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
