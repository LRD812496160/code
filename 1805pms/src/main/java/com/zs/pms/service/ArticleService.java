package com.zs.pms.service;

import java.util.List;

import com.zs.pms.exception.AppException;
import com.zs.pms.po.Tarticle;
import com.zs.pms.vo.QueryArticle;

public interface ArticleService {
	
	//1.通过条件查询
	public List<Tarticle> queryByCon(QueryArticle query);
	//2.查分页
	public List<Tarticle> queryByPage(QueryArticle query,int page);
	//3.根据主键查询
	public Tarticle queryById(int id);
	//4.批量删除
	public void deleteByIds(int[] ids);
	//5.修改
	public void update(Tarticle user) throws AppException;
	//6.新增
	public int insert(Tarticle user) throws AppException;
	//7.删除一条
	public void delete(int id) throws AppException;
	//8.查询总页数
	public int queryPageCount(QueryArticle query);
}
