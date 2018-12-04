package com.zs.pms.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zs.pms.dao.ArticleDao;
import com.zs.pms.exception.AppException;
import com.zs.pms.po.Tarticle;
import com.zs.pms.service.ArticleService;
import com.zs.pms.utils.Constants;
import com.zs.pms.vo.QueryArticle;

/**
 * article����ʵ��
 * @author Administrator
 *
 */
@Service
@Transactional //��ҵ��֧������
public class ArticleServiceImpl implements ArticleService{

	@Autowired
	private ArticleDao adao;
	

	//1.ͨ��������ѯ
	@Override
	public List<Tarticle> queryByCon(QueryArticle query) {
		// TODO Auto-generated method stub
		return adao.queryByCon(query);
	}

	//2.���ҳ
	@Override
	public List<Tarticle> queryByPage(QueryArticle query, int page) {
		// TODO Auto-generated method stub
		//����ǰҳ���õ�������
		query.setPage(page);
		//����������ʵ�ͽ�ֹ
		return adao.queryByPage(query);
	}

	//3.����������ѯ
	@Override
	public Tarticle queryById(int id) {
		// TODO Auto-generated method stub
		return adao.queryById(id);
	}

	
	//4.����ɾ��
	@Override
	@Transactional(rollbackFor=Exception.class) //���쳣�ͻع������ύ
	public void deleteByIds(int[] ids) {
		// TODO Auto-generated method stub
		adao.deleteByIds(ids);
	}

	//5.�޸�
	@Override
	@Transactional(rollbackFor=Exception.class) //���쳣�ͻع������ύ
	public void update(Tarticle article) throws AppException {
		// TODO Auto-generated method stub
		
		adao.update(article);
		
	}

	//6.����
	@Override
	@Transactional(rollbackFor=Exception.class) //���쳣�ͻع������ύ
	public int insert(Tarticle article) throws AppException {
		// TODO Auto-generated method stub
//		adao.insert(article); //�ع� �������ɹ�
//		int a=1/0; //���쳣
		
		return adao.insert(article);
	}


	//7.ɾ��һ��
	@Override
	@Transactional(rollbackFor=Exception.class) //���쳣�ͻع������ύ
	public void delete(int id) throws AppException {
		// TODO Auto-generated method stub
		adao.delete(id);
	}

	//8.��ѯ��ҳ��
	@Override
	public int queryPageCount(QueryArticle query) {
		// TODO Auto-generated method stub
		//���������
		int count=adao.queryCount(query);
		//������
		if (count%Constants.PAGECOUNT==0) {
			return count/Constants.PAGECOUNT;
		} else {
			//��������
			return count/Constants.PAGECOUNT+1;
		}
		
	}
	
	
	
	
	
}
