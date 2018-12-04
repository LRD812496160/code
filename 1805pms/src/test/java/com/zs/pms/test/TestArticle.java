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

@RunWith(SpringJUnit4ClassRunner.class) //ʹ��spring���Կ��
@ContextConfiguration("classpath:applicationcontext.xml") //���������ļ�
public class TestArticle {

	@Autowired
	ArticleService as;
	
	
	//1.ͨ��������ѯ
	@Test
	public void testQuery(){
		QueryArticle query=new QueryArticle();
		query.setTitle("����");;
		
		
//		query.setIshot(1);
//		query.setPage(2);
		as.queryByCon(query);
	}
	
	//2.���ҳ
	
	public void testPage(){
		QueryArticle query=new QueryArticle();
		
		System.out.println("��ǰ��ҳ��"+as.queryPageCount(query));
		for (Tarticle article : as.queryByPage(query, 2)) {
			System.out.println(article.getTitle());
		}
	}
	//3.����������ѯ
	
	public void testQueryById(){
		int id=1;
		
		System.out.println(as.queryById(id).getTitle());
	}
	
	//4.����ɾ��
	
	public void testDeletes(){
		int[] ids={102,103};
		as.deleteByIds(ids);
	}
	
	//5.�޸�
	
	public void testUpdate(){
		Tarticle article=new Tarticle();
		article.setId(1);
		article.setTitle("�����");
		article.setContent("����ԭ");
		article.setUpdator("����");
		
		//����
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
	
	
	//6.����
	
	public void testAdd(){
		Tarticle article=new Tarticle();
		article.setTitle("trans005");
		article.setContent("����Ա003");
		//����
		TChan chan=new TChan();
		chan.setId(3);
		article.setChannel(chan);
		
		article.setCreator("����");

		article.setIsremod(1);
		article.setIshot(1);
		try {
			as.insert(article);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
	
	//7.ɾ��һ��
	
	public void testDelete() throws AppException{
		int id=101;
		as.delete(id);
	}
	
	//8.��ѯ��ҳ��
	
	public void testPageCount(){
		QueryArticle query=new QueryArticle();
		System.out.println("��ѯ����ҳ��Ϊ��"+as.queryPageCount(query));
	}
	
}
