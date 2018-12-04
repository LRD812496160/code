package com.zs.pms.bean;

public class UserBean {

	private int id;
	private String loginname;
	private String password;
	private String realname;
	private String sex;
	private String birthday;
	private String email;
	private int dept;
	private int enabled;//
	private int creatman;//
	private String dname;//
	private String enabledTxt;//
	
	public int getEnabled() {
		return enabled;
	}
	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}
	public int getCreatman() {
		return creatman;
	}
	public void setCreatman(int creatman) {
		this.creatman = creatman;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public String getEnabledTxt() {
		return enabledTxt;
	}
	public void setEnabledTxt(String enabledTxt) {
		this.enabledTxt = enabledTxt;
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
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getDept() {
		return dept;
	}
	public void setDept(int i) {
		this.dept = i;
	}
	
	
}
