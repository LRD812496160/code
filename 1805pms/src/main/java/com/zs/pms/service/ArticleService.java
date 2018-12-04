package com.zs.pms.service;

import java.util.List;

import com.zs.pms.exception.AppException;
import com.zs.pms.po.Tarticle;
import com.zs.pms.vo.QueryArticle;

public interface ArticleService {
	
	//1.ͨ��������ѯ
	public List<Tarticle> queryByCon(QueryArticle query);
	//2.���ҳ
	public List<Tarticle> queryByPage(QueryArticle query,int page);
	//3.����������ѯ
	public Tarticle queryById(int id);
	//4.����ɾ��
	public void deleteByIds(int[] ids);
	//5.�޸�
	public void update(Tarticle user) throws AppException;
	//6.����
	public int insert(Tarticle user) throws AppException;
	//7.ɾ��һ��
	public void delete(int id) throws AppException;
	//8.��ѯ��ҳ��
	public int queryPageCount(QueryArticle query);
}
