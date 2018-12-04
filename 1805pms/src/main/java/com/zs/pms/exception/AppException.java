package com.zs.pms.exception;

public class AppException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3393156607369914270L;
	// 程序自定义异常类与异常信息
	// 异常码
	private int errCode;
	// 异常属性
	private String errMsg;

	// setter、getter
	public int getErrCode() {
		return errCode;
	}

	public void setErrCode(int errCode) {
		this.errCode = errCode;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	/**
	 * 带参构造函数 用来给异常信息、异常编码赋值
	 * 
	 * @param errCode
	 * @param errMsg
	 */
	public AppException(int errCode, String errMsg) {
		super();
		this.errCode = errCode;
		this.errMsg = errMsg;
	}
}
