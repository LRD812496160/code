package com.zs.pms.po;

import java.io.Serializable;
/**
 * 栏目表的PO
 * @author Administrator
 *
 */
public class TChan implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6693929339309480899L;

	
	private int id;
	private String cname;
	private int pid; //上级id
	private int lev; 
	private int isleaf; 
	private int sort;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public int getLev() {
		return lev;
	}
	public void setLev(int lev) {
		this.lev = lev;
	}
	public int getIsleaf() {
		return isleaf;
	}
	public void setIsleaf(int isleaf) {
		this.isleaf = isleaf;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	} 
	
	
	
	
	

}
