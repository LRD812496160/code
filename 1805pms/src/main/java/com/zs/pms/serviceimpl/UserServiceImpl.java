package com.zs.pms.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zs.pms.dao.UserDao;
import com.zs.pms.exception.AppException;
import com.zs.pms.po.TUser;
import com.zs.pms.service.UserService;
import com.zs.pms.utils.Constants;
import com.zs.pms.utils.MD5;
import com.zs.pms.vo.QueryUser;

/**
 * user服务实现
 * @author Administrator
 *
 */
@Service
@Transactional //该业务支持事务
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao udao;
	
	//0.登录
	@Override
	public TUser chkLogin(QueryUser query) throws AppException {
		// TODO Auto-generated method stub
		
		//将明码变成密码
		MD5 md5=new MD5();
		//加密后的密码
		String p1=md5.getMD5ofStr(query.getPassword());
		//将密码放到query中
		query.setPassword(p1);
		
		
		List<TUser> list=udao.queryByCon(query);
		//没有用户
		if (list==null||list.size()!=1) {
			throw new AppException(1000, "用户名或密码输入有误，请重新输入");
		}
		//获得第一条
		TUser user=list.get(0);
		
		//关联权限列表返回
		return udao.queryById(user.getId());
	}

	//1.通过条件查询
	@Override
	public List<TUser> queryByCon(QueryUser query) {
		// TODO Auto-generated method stub
		return udao.queryByCon(query);
	}

	//2.查分页
	@Override
	public List<TUser> queryByPage(QueryUser query, int page) {
		// TODO Auto-generated method stub
		//将当前页设置到条件中
		query.setPage(page);
		//可以设置其实和截止
		return udao.queryByPage(query);
	}

	//3.根据主键查询
	@Override
	public TUser queryById(int id) {
		// TODO Auto-generated method stub
		return udao.queryById(id);
	}

	
	//4.批量删除
	@Override
	@Transactional(rollbackFor=Exception.class) //有异常就回滚否则提交
	public void deleteByIds(int[] ids) throws AppException {
		// TODO Auto-generated method stub
		
		if (ids==null) {
			throw new AppException(1004, "请选择要删除的数据");
		}
		udao.deleteByIds(ids);
	}

	//5.修改
	@Override
	@Transactional(rollbackFor=Exception.class) //有异常就回滚否则提交
	public void update(TUser user) throws AppException {
		// TODO Auto-generated method stub
		//不可用
		if (user.getIsenabled()==-1) {
			throw new AppException(1003, "不能修改不可用用户");
		}
		
		//获得原来用户
		TUser ouser=udao.queryById(user.getId());
		//原密码不等于新密码才加密
		if (user.getPassword()!=null && (!"".equals(user.getPassword()))&&!user.getPassword().equals(ouser.getPassword())) {
			//密码加密
			MD5 md5=new MD5();
			user.setPassword(md5.getMD5ofStr(user.getPassword()));
		}
		udao.update(user);
		
	}

	//6.新增
	@Override
	@Transactional(rollbackFor=Exception.class) //有异常就回滚否则提交
	public int insert(TUser user) throws AppException {
		// TODO Auto-generated method stub
//		udao.insert(user); //回滚 新增不成功
//		int a=1/0; //抛异常
		
		//登录名
		if ("admin".equals(user.getLoginname())) {
			throw new AppException(1002, "登录名不能为admin");
		}
		return udao.insert(user);
	}


	//7.删除一条
	@Override
	@Transactional(rollbackFor=Exception.class) //有异常就回滚否则提交
	public void delete(int id) throws AppException {
		// TODO Auto-generated method stub
		udao.delete(id);
	}

	//8.查询总页数
	@Override
	public int queryPageCount(QueryUser query) {
		// TODO Auto-generated method stub
		//获得总条数
		int count=udao.queryCount(query);
		//能整除
		if (count%Constants.PAGECOUNT==0) {
			return count/Constants.PAGECOUNT;
		} else {
			//不能整除
			return count/Constants.PAGECOUNT+1;
		}
		
	}
	
	
	
	
	
}
