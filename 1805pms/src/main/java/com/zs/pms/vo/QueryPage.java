package com.zs.pms.vo;

import com.zs.pms.utils.Constants;

/**
 * ��ҳ����
 * @author Administrator
 *
 */
public class QueryPage {
	
	protected int start; //��ʼ��	���Լ̳�
	protected int end;   //��ֹ��
	
	protected int page;  //��ǰҳ

	/*
	 * ������ʼ�� 
	 */
	public int getStart() {
		//(��ǰҳ-1)*ÿҳ����+1
		return (page-1)*Constants.PAGECOUNT+1;
	}

	public void setStart(int start) {
		this.start = start;
	}
	
	/*
	 * �����ֹ�� 
	 */
	public int getEnd() {
		//��ǰҳ*ÿҳ����
		return page*Constants.PAGECOUNT;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}
	
	
}
