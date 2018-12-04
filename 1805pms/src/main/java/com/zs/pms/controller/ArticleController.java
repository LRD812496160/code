package com.zs.pms.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zs.pms.exception.AppException;
import com.zs.pms.po.TChan;
import com.zs.pms.po.Tarticle;
import com.zs.pms.service.ChanService;
import com.zs.pms.service.ArticleService;
import com.zs.pms.vo.QueryArticle;

/**
 * 文章控制器
 * @author Administrator
 *
 */
@Controller
public class ArticleController {

	@Autowired
	ArticleService as; //文章服务
	
	@Autowired
	
	ChanService cs;  //栏目服务
	
	@RequestMapping("/article/list.do") //二级url
	public String list(QueryArticle query,String page,ModelMap map){
		//page是空
		if (page==null||"".equals(page)) {
			page="1"; //默认第一页
			
		}
		//回带分页数据
		map.addAttribute("LIST",as.queryByPage(query, Integer.parseInt(page)));
		//回带总页数
		map.addAttribute("PAGECOUNT",as.queryPageCount(query));
		//回带当前页
		map.addAttribute("PAGE",page);
		//回带查询条件
		map.addAttribute("QUERY",query);
		//返回Article/list.jsp
		return "article/list";
		
	}
	
	@RequestMapping("/article/toadd.do")
	public String toadd(ModelMap map){
		//获得一级栏目列表
		List<TChan> list1=cs.queryByPid(0);
		map.addAttribute("CLIST",list1);
		//获得默认一级部门下的二级部门
//		List<TChan> list2=cs.queryByPid(list1.get(0).getId());
//		map.addAttribute("CLIST2",list2);
		return "article/add";
	}
	
/*	
	/**
	 * 处理ajax请求，不需返回页面
	 * 向页面返回数据类型即可
	 *//*
	@RequestMapping("/article/gechan.do")
	@ResponseBody
	public List<TChan> getChan(int pid){
		return cs.queryByPid(pid);
	}
*/	
	@RequestMapping("/article/add.do")
	public String add(Tarticle article,ModelMap map,HttpSession session){
		
		try {
			//获得session中的文章信息
			Tarticle casr=(Tarticle) session.getAttribute("CARTICLE");
			article.setCreator(casr.getUpdator());	//创建人
			
			as.insert(article);
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
	@RequestMapping("/article/delete.do")
	public String delete(int id){
		try {
			as.delete(id);
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
	@RequestMapping("/article/deletes.do")
	public String deletes(int[] ids){
		as.deleteByIds(ids);
		return "redirect:list.do";
	}
	
	/**
	 * 获取要修改的数据
	 * @param id
	 * @param map
	 * @return
	 */
	@RequestMapping("/article/get.do")
	public String get(int id,ModelMap map){
		Tarticle article=as.queryById(id);
		map.addAttribute("ARTICLE",article);
		//获得一级栏目列表
		List<TChan> list1=cs.queryByPid(0);
		map.addAttribute("CLIST",list1);
		//获得该用户的一级部门下的二级部门列表
//		List<TChan> list2=cs.queryByPid(article.getChannel().getPid());
//		map.addAttribute("CLIST2",list2);
		return "article/update";
	}
	
	@RequestMapping("/article/update.do")
	public String update(Tarticle article,HttpSession session,ModelMap map){
		//获得session中的文章信息
		Tarticle carticle=(Tarticle) session.getAttribute("CARTICLE");
		article.setUpdator(carticle.getUpdator());
		try {
			as.update(article);
			return "redirect:list.do";
		} catch (AppException e) {
			// TODO Auto-generated catch block
			map.addAttribute("msg",e.getErrMsg());
			return get(article.getId(), map);
		}
	}
	
	
	
	
	
	
	
	
}
