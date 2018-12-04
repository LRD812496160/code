package com.zs.pms.dao;

import com.zs.pms.po.Tarticle;
import com.zs.pms.vo.QueryArticle;
import java.util.List;

/**
 * ���±����ݽӿ�
 * @author Administrator
 *
 */

public interface ArticleDao {

	//1.ͨ��������ѯ
	public List<Tarticle> queryByCon(QueryArticle query);
	//2.���ҳ
	public List<Tarticle> queryByPage(QueryArticle query);
	//3.����������ѯ
	public Tarticle queryById(int id);
	//4.����ɾ��
	public void deleteByIds(int[] ids);
	//5.�޸�
	public void update(Tarticle user);
	//6.����
	public int insert(Tarticle user);
	//7.ɾ��һ��
	public void delete(int id);
	//8.��ѯ����
	public int queryCount(QueryArticle query);
}
