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
 * article服务实现
 * @author Administrator
 *
 */
@Service
@Transactional //该业务支持事务
public class ArticleServiceImpl implements ArticleService{

	@Autowired
	private ArticleDao adao;
	

	//1.通过条件查询
	@Override
	public List<Tarticle> queryByCon(QueryArticle query) {
		// TODO Auto-generated method stub
		return adao.queryByCon(query);
	}

	//2.查分页
	@Override
	public List<Tarticle> queryByPage(QueryArticle query, int page) {
		// TODO Auto-generated method stub
		//将当前页设置到条件中
		query.setPage(page);
		//可以设置其实和截止
		return adao.queryByPage(query);
	}

	//3.根据主键查询
	@Override
	public Tarticle queryById(int id) {
		// TODO Auto-generated method stub
		return adao.queryById(id);
	}

	
	//4.批量删除
	@Override
	@Transactional(rollbackFor=Exception.class) //有异常就回滚否则提交
	public void deleteByIds(int[] ids) {
		// TODO Auto-generated method stub
		adao.deleteByIds(ids);
	}

	//5.修改
	@Override
	@Transactional(rollbackFor=Exception.class) //有异常就回滚否则提交
	public void update(Tarticle article) throws AppException {
		// TODO Auto-generated method stub
		
		adao.update(article);
		
	}

	//6.新增
	@Override
	@Transactional(rollbackFor=Exception.class) //有异常就回滚否则提交
	public int insert(Tarticle article) throws AppException {
		// TODO Auto-generated method stub
//		adao.insert(article); //回滚 新增不成功
//		int a=1/0; //抛异常
		
		return adao.insert(article);
	}


	//7.删除一条
	@Override
	@Transactional(rollbackFor=Exception.class) //有异常就回滚否则提交
	public void delete(int id) throws AppException {
		// TODO Auto-generated method stub
		adao.delete(id);
	}

	//8.查询总页数
	@Override
	public int queryPageCount(QueryArticle query) {
		// TODO Auto-generated method stub
		//获得总条数
		int count=adao.queryCount(query);
		//能整除
		if (count%Constants.PAGECOUNT==0) {
			return count/Constants.PAGECOUNT;
		} else {
			//不能整除
			return count/Constants.PAGECOUNT+1;
		}
		
	}
	
	
	
	
	
}
