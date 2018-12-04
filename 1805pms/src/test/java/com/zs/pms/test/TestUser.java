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

@RunWith(SpringJUnit4ClassRunner.class) //ʹ��spring���Կ��
@ContextConfiguration("classpath:applicationcontext.xml") //���������ļ�
public class TestUser {

	@Autowired
	UserService us;
	
	//0.��¼
	public void testlogin(){
		QueryUser query=new QueryUser();
		query.setLoginname("test1");
		query.setPassword("1234");//����
		try {
			TUser user=us.chkLogin(query);
			System.out.println(user.getRealname()+"-"+user.getDept().getName());
			for (TPermission per:user.getPermissions()) {
				System.out.println(per.getPname());
			}
			System.out.println("--------------�����----------------");
			
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
	
	//1.ͨ��������ѯ
	public void testQuery(){
		QueryUser query=new QueryUser();
//		query.setLoginname("test1");
		query.setPassword("1234");//����
		
//		query.setIsenabled(1);
//		query.setPage(2);
		us.queryByCon(query);
	}
	
	//2.���ҳ
	
	public void testPage(){
		QueryUser query=new QueryUser();
		
		System.out.println("��ǰ��ҳ��"+us.queryPageCount(query));
		for (TUser user : us.queryByPage(query, 2)) {
			System.out.println(user.getRealname());
		}
	}
	//3.����������ѯ
	@Test
	public void testQueryById(){
		int id=3086;
		
		System.out.println(us.queryById(id).getLoginname());
	}
	
	//4.����ɾ��
	public void testDeletes() throws AppException{
		int[] ids={3090,3091};
		us.deleteByIds(ids);
	}
	
	//5.�޸�
	public void testUpdate(){
		TUser user=new TUser();
		user.setId(3089);
		user.setBirthday(new Date());
		//����
		TDep dep=new TDep();
		dep.setId(3);
		user.setDept(dep);
		
		user.setEmail("qwe@123.com");
		user.setIsenabled(-1);
		user.setPassword("4321");
		user.setPic("aaa.jpg");
		user.setRealname("����Ա33");
		user.setSex("Ů");
		user.setUpdator(100);
		try {
			us.update(user);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	
	//6.����
	public void testAdd(){
		TUser user=new TUser();
		user.setBirthday(new Date());
		//����
		TDep dep=new TDep();
		dep.setId(3);
		user.setDept(dep);
		
		user.setLoginname("trans005");
		user.setEmail("qwe@123.com");

		user.setPassword("4321");
		user.setPic("aaa.jpg");
		user.setRealname("����Ա003");
		user.setSex("Ů");
		user.setCreator(1001);
		try {
			us.insert(user);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
	
	//7.ɾ��һ��
	
	public void testDelete() throws AppException{
		int id=6;
		us.delete(id);
	}
	
	//8.��ѯ��ҳ��
	
	public void testPageCount(){
		QueryUser query=new QueryUser();
		System.out.println("��ѯ����ҳ��Ϊ��"+us.queryPageCount(query));
	}
	
}
