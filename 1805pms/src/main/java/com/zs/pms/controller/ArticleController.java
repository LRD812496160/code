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
 * ���¿�����
 * @author Administrator
 *
 */
@Controller
public class ArticleController {

	@Autowired
	ArticleService as; //���·���
	
	@Autowired
	
	ChanService cs;  //��Ŀ����
	
	@RequestMapping("/article/list.do") //����url
	public String list(QueryArticle query,String page,ModelMap map){
		//page�ǿ�
		if (page==null||"".equals(page)) {
			page="1"; //Ĭ�ϵ�һҳ
			
		}
		//�ش���ҳ����
		map.addAttribute("LIST",as.queryByPage(query, Integer.parseInt(page)));
		//�ش���ҳ��
		map.addAttribute("PAGECOUNT",as.queryPageCount(query));
		//�ش���ǰҳ
		map.addAttribute("PAGE",page);
		//�ش���ѯ����
		map.addAttribute("QUERY",query);
		//����Article/list.jsp
		return "article/list";
		
	}
	
	@RequestMapping("/article/toadd.do")
	public String toadd(ModelMap map){
		//���һ����Ŀ�б�
		List<TChan> list1=cs.queryByPid(0);
		map.addAttribute("CLIST",list1);
		//���Ĭ��һ�������µĶ�������
//		List<TChan> list2=cs.queryByPid(list1.get(0).getId());
//		map.addAttribute("CLIST2",list2);
		return "article/add";
	}
	
/*	
	/**
	 * ����ajax���󣬲��践��ҳ��
	 * ��ҳ�淵���������ͼ���
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
			//���session�е�������Ϣ
			Tarticle casr=(Tarticle) session.getAttribute("CARTICLE");
			article.setCreator(casr.getUpdator());	//������
			
			as.insert(article);
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
	 * ɾ������
	 * @param id
	 * @return
	 */
	@RequestMapping("/article/deletes.do")
	public String deletes(int[] ids){
		as.deleteByIds(ids);
		return "redirect:list.do";
	}
	
	/**
	 * ��ȡҪ�޸ĵ�����
	 * @param id
	 * @param map
	 * @return
	 */
	@RequestMapping("/article/get.do")
	public String get(int id,ModelMap map){
		Tarticle article=as.queryById(id);
		map.addAttribute("ARTICLE",article);
		//���һ����Ŀ�б�
		List<TChan> list1=cs.queryByPid(0);
		map.addAttribute("CLIST",list1);
		//��ø��û���һ�������µĶ��������б�
//		List<TChan> list2=cs.queryByPid(article.getChannel().getPid());
//		map.addAttribute("CLIST2",list2);
		return "article/update";
	}
	
	@RequestMapping("/article/update.do")
	public String update(Tarticle article,HttpSession session,ModelMap map){
		//���session�е�������Ϣ
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
