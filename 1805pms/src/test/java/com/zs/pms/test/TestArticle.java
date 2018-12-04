package com.zs.pms.test;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zs.pms.exception.AppException;
import com.zs.pms.po.TChan;
import com.zs.pms.po.Tarticle;
import com.zs.pms.service.ArticleService;
import com.zs.pms.vo.QueryArticle;

@RunWith(SpringJUnit4ClassRunner.class) //使用spring测试框架
@ContextConfiguration("classpath:applicationcontext.xml") //引入配置文件
public class TestArticle {

	@Autowired
	ArticleService as;
	
	
	//1.通过条件查询
	@Test
	public void testQuery(){
		QueryArticle query=new QueryArticle();
		query.setTitle("三国");;
		
		
//		query.setIshot(1);
//		query.setPage(2);
		as.queryByCon(query);
	}
	
	//2.查分页
	
	public void testPage(){
		QueryArticle query=new QueryArticle();
		
		System.out.println("当前总页数"+as.queryPageCount(query));
		for (Tarticle article : as.queryByPage(query, 2)) {
			System.out.println(article.getTitle());
		}
	}
	//3.根据主键查询
	
	public void testQueryById(){
		int id=1;
		
		System.out.println(as.queryById(id).getTitle());
	}
	
	//4.批量删除
	
	public void testDeletes(){
		int[] ids={102,103};
		as.deleteByIds(ids);
	}
	
	//5.修改
	
	public void testUpdate(){
		Tarticle article=new Tarticle();
		article.setId(1);
		article.setTitle("诸葛亮");
		article.setContent("五丈原");
		article.setUpdator("刘备");
		
		//部门
		TChan chan=new TChan();
		chan.setId(3);
		article.setChannel(chan);
		
		article.setIsremod(-1);
		article.setIshot(-1);
		try {
			as.update(article);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	
	//6.新增
	
	public void testAdd(){
		Tarticle article=new Tarticle();
		article.setTitle("trans005");
		article.setContent("测试员003");
		//部门
		TChan chan=new TChan();
		chan.setId(3);
		article.setChannel(chan);
		
		article.setCreator("法正");

		article.setIsremod(1);
		article.setIshot(1);
		try {
			as.insert(article);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
	
	//7.删除一条
	
	public void testDelete() throws AppException{
		int id=101;
		as.delete(id);
	}
	
	//8.查询总页数
	
	public void testPageCount(){
		QueryArticle query=new QueryArticle();
		System.out.println("查询到总页数为："+as.queryPageCount(query));
	}
	
}
