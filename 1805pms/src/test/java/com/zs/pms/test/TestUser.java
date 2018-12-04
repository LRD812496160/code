package com.zs.pms.test;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zs.pms.exception.AppException;
import com.zs.pms.po.TDep;
import com.zs.pms.po.TPermission;
import com.zs.pms.po.TUser;
import com.zs.pms.service.UserService;
import com.zs.pms.vo.QueryUser;

@RunWith(SpringJUnit4ClassRunner.class) //使用spring测试框架
@ContextConfiguration("classpath:applicationcontext.xml") //引入配置文件
public class TestUser {

	@Autowired
	UserService us;
	
	//0.登录
	public void testlogin(){
		QueryUser query=new QueryUser();
		query.setLoginname("test1");
		query.setPassword("1234");//明码
		try {
			TUser user=us.chkLogin(query);
			System.out.println(user.getRealname()+"-"+user.getDept().getName());
			for (TPermission per:user.getPermissions()) {
				System.out.println(per.getPname());
			}
			System.out.println("--------------整理后----------------");
			
			for (TPermission per1 : user.getMenu()) {
				System.out.println(per1.getPname());
				
				for (TPermission per2 : per1.getChildren()) {
					System.out.println("\t\t"+per2.getPname());
				}
			}
		} catch (AppException e) {
			// TODO: handle exception
			System.out.println(e.getErrMsg());
		} catch (Exception e1) {
			// TODO: handle exception
			e1.printStackTrace();
		}		
	}
	
	//1.通过条件查询
	public void testQuery(){
		QueryUser query=new QueryUser();
//		query.setLoginname("test1");
		query.setPassword("1234");//明码
		
//		query.setIsenabled(1);
//		query.setPage(2);
		us.queryByCon(query);
	}
	
	//2.查分页
	
	public void testPage(){
		QueryUser query=new QueryUser();
		
		System.out.println("当前总页数"+us.queryPageCount(query));
		for (TUser user : us.queryByPage(query, 2)) {
			System.out.println(user.getRealname());
		}
	}
	//3.根据主键查询
	@Test
	public void testQueryById(){
		int id=3086;
		
		System.out.println(us.queryById(id).getLoginname());
	}
	
	//4.批量删除
	public void testDeletes() throws AppException{
		int[] ids={3090,3091};
		us.deleteByIds(ids);
	}
	
	//5.修改
	public void testUpdate(){
		TUser user=new TUser();
		user.setId(3089);
		user.setBirthday(new Date());
		//部门
		TDep dep=new TDep();
		dep.setId(3);
		user.setDept(dep);
		
		user.setEmail("qwe@123.com");
		user.setIsenabled(-1);
		user.setPassword("4321");
		user.setPic("aaa.jpg");
		user.setRealname("测试员33");
		user.setSex("女");
		user.setUpdator(100);
		try {
			us.update(user);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	
	//6.新增
	public void testAdd(){
		TUser user=new TUser();
		user.setBirthday(new Date());
		//部门
		TDep dep=new TDep();
		dep.setId(3);
		user.setDept(dep);
		
		user.setLoginname("trans005");
		user.setEmail("qwe@123.com");

		user.setPassword("4321");
		user.setPic("aaa.jpg");
		user.setRealname("测试员003");
		user.setSex("女");
		user.setCreator(1001);
		try {
			us.insert(user);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
	
	//7.删除一条
	
	public void testDelete() throws AppException{
		int id=6;
		us.delete(id);
	}
	
	//8.查询总页数
	
	public void testPageCount(){
		QueryUser query=new QueryUser();
		System.out.println("查询到总页数为："+us.queryPageCount(query));
	}
	
}
