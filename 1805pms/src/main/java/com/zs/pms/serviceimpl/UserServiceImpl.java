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
 * user����ʵ��
 * @author Administrator
 *
 */
@Service
@Transactional //��ҵ��֧������
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao udao;
	
	//0.��¼
	@Override
	public TUser chkLogin(QueryUser query) throws AppException {
		// TODO Auto-generated method stub
		
		//������������
		MD5 md5=new MD5();
		//���ܺ������
		String p1=md5.getMD5ofStr(query.getPassword());
		//������ŵ�query��
		query.setPassword(p1);
		
		
		List<TUser> list=udao.queryByCon(query);
		//û���û�
		if (list==null||list.size()!=1) {
			throw new AppException(1000, "�û���������������������������");
		}
		//��õ�һ��
		TUser user=list.get(0);
		
		//����Ȩ���б���
		return udao.queryById(user.getId());
	}

	//1.ͨ��������ѯ
	@Override
	public List<TUser> queryByCon(QueryUser query) {
		// TODO Auto-generated method stub
		return udao.queryByCon(query);
	}

	//2.���ҳ
	@Override
	public List<TUser> queryByPage(QueryUser query, int page) {
		// TODO Auto-generated method stub
		//����ǰҳ���õ�������
		query.setPage(page);
		//����������ʵ�ͽ�ֹ
		return udao.queryByPage(query);
	}

	//3.����������ѯ
	@Override
	public TUser queryById(int id) {
		// TODO Auto-generated method stub
		return udao.queryById(id);
	}

	
	//4.����ɾ��
	@Override
	@Transactional(rollbackFor=Exception.class) //���쳣�ͻع������ύ
	public void deleteByIds(int[] ids) throws AppException {
		// TODO Auto-generated method stub
		
		if (ids==null) {
			throw new AppException(1004, "��ѡ��Ҫɾ��������");
		}
		udao.deleteByIds(ids);
	}

	//5.�޸�
	@Override
	@Transactional(rollbackFor=Exception.class) //���쳣�ͻع������ύ
	public void update(TUser user) throws AppException {
		// TODO Auto-generated method stub
		//������
		if (user.getIsenabled()==-1) {
			throw new AppException(1003, "�����޸Ĳ������û�");
		}
		
		//���ԭ���û�
		TUser ouser=udao.queryById(user.getId());
		//ԭ���벻����������ż���
		if (user.getPassword()!=null && (!"".equals(user.getPassword()))&&!user.getPassword().equals(ouser.getPassword())) {
			//�������
			MD5 md5=new MD5();
			user.setPassword(md5.getMD5ofStr(user.getPassword()));
		}
		udao.update(user);
		
	}

	//6.����
	@Override
	@Transactional(rollbackFor=Exception.class) //���쳣�ͻع������ύ
	public int insert(TUser user) throws AppException {
		// TODO Auto-generated method stub
//		udao.insert(user); //�ع� �������ɹ�
//		int a=1/0; //���쳣
		
		//��¼��
		if ("admin".equals(user.getLoginname())) {
			throw new AppException(1002, "��¼������Ϊadmin");
		}
		return udao.insert(user);
	}


	//7.ɾ��һ��
	@Override
	@Transactional(rollbackFor=Exception.class) //���쳣�ͻع������ύ
	public void delete(int id) throws AppException {
		// TODO Auto-generated method stub
		udao.delete(id);
	}

	//8.��ѯ��ҳ��
	@Override
	public int queryPageCount(QueryUser query) {
		// TODO Auto-generated method stub
		//���������
		int count=udao.queryCount(query);
		//������
		if (count%Constants.PAGECOUNT==0) {
			return count/Constants.PAGECOUNT;
		} else {
			//��������
			return count/Constants.PAGECOUNT+1;
		}
		
	}
	
	
	
	
	
}
