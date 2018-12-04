package com.zs.pms.po;

import java.io.Serializable;
import java.util.Date;

import com.zs.pms.utils.DateUtil;
/**
 * 文章表的PO
 * @author Administrator
 *
 */
public class Tarticle implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5293214558214995122L;

	private int id;
	private String title;
	private String content;
	private String creator;
	private Date ctime;
	private String updator;
	private Date uptime;
	
	private TChan channel; //关联对象 一对一
	
	private int isremod;
	private int ishot;
	
	public String getIsremodTxt() {
		if (ishot==1) {
			return "推荐";
		} else {
			return "不推荐";
		}
	}


	public String getIshotTxt() {
		if (isremod==1) {
			return "热点";
		} else {
			return "非热点";
		}
	}

	public String getCtimeTxt() {
		return DateUtil.getStrDate(ctime);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	public String getUpdator() {
		return updator;
	}

	public void setUpdator(String updator) {
		this.updator = updator;
	}

	public Date getUptime() {
		return uptime;
	}

	public void setUptime(Date uptime) {
		this.uptime = uptime;
	}

	public TChan getChannel() {
		return channel;
	}

	public void setChannel(TChan channel) {
		this.channel = channel;
	}

	public int getIsremod() {
		return isremod;
	}

	public void setIsremod(int isremod) {
		this.isremod = isremod;
	}

	public int getIshot() {
		return ishot;
	}

	public void setIshot(int ishot) {
		this.ishot = ishot;
	}
	
	
}
