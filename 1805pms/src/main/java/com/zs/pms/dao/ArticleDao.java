package com.zs.pms.dao;

import com.zs.pms.po.Tarticle;
import com.zs.pms.vo.QueryArticle;
import java.util.List;

/**
 * 文章表数据接口
 * @author Administrator
 *
 */

public interface ArticleDao {

	//1.通过条件查询
	public List<Tarticle> queryByCon(QueryArticle query);
	//2.查分页
	public List<Tarticle> queryByPage(QueryArticle query);
	//3.根据主键查询
	public Tarticle queryById(int id);
	//4.批量删除
	public void deleteByIds(int[] ids);
	//5.修改
	public void update(Tarticle user);
	//6.新增
	public int insert(Tarticle user);
	//7.删除一条
	public void delete(int id);
	//8.查询总数
	public int queryCount(QueryArticle query);
}
