package com.zs.pms.po;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.zs.pms.utils.DateUtil;
/**
 * �û����PO
 * @author Administrator
 *
 */
public class TUser implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5293214558214995122L;

	private int id;
	private String loginname;
	private String password;
	private String sex;
	private Date birthday;
	private String email;
	
	private TDep dept; //�������� һ��һ
	
	private String realname;
	private int creator;
	private Date creatime;
	private int updator;
	private Date updatetime;
	private String pic;
	private int isenabled;
	//��ʾ�ֶ�
	private String sexTxt;
	public String getBirthdayTxt() {
		
		return DateUtil.getStrDate(birthday);
	}

	public String getIsenabledTxt() {
		if (isenabled==1) {
			return "����";
		} else {
			return "������";
		}
		
	}

	
	
	public String getSexTxt() {
		return sexTxt;
	}

	public void setSexTxt(String sexTxt) {
		this.sexTxt = sexTxt;
	}

	
	//����	Ȩ���б�
	private List<TPermission> permissions;
	//���˵�����permissions�������
	private List<TPermission> menu=new ArrayList<>();
	
	/**
	 * ����˵�
	 * @return
	 */
	public List<TPermission> getMenu() {
		//���
		menu.clear();
		
		for (TPermission per1 : permissions) {
			//һ���˵�
			if (per1.getPid()==0) {
				//���
				per1.getChildren().clear();
				
				//װ��һ���˵��µĶ����˵�
				for (TPermission per2 : permissions) {
					//һ���˵���id=�����˵���pid
					//˵����Ȩ����һ���˵�����Ȩ��
					if (per1.getId()==per2.getPid()) {
						per1.getChildren().add(per2);
					}
				}
				//��װ�غõ�һ���˵�����˵���
				menu.add(per1);
			}
			
		}
		return menu;
	}
	
	public List<TPermission> getPermissions() {
		return permissions;
	}
	public void setPermissions(List<TPermission> permissions) {
		this.permissions = permissions;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public TDep getDept() {
		return dept;
	}
	public void setDept(TDep dept) {
		this.dept = dept;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public int getCreator() {
		return creator;
	}
	public void setCreator(int creator) {
		this.creator = creator;
	}
	public Date getCreatime() {
		return creatime;
	}
	public void setCreatime(Date creatime) {
		this.creatime = creatime;
	}
	public int getUpdator() {
		return updator;
	}
	public void setUpdator(int updator) {
		this.updator = updator;
	}
	public Date getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public int getIsenabled() {
		return isenabled;
	}
	public void setIsenabled(int isenabled) {
		this.isenabled = isenabled;
	}



	
}
