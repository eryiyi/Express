package com.eln.lib.exception;

/**
 * @projectName DBModules
 * @PackageName com.xinxin.android.exception
 * @Title XxinException
 * @Description 所有异常的基类
 * @Author XinXinTeam(xiaofeng.Lu)
 * @Date 2014-3-24
 * @Version V1.0
 */
public class NDException extends Exception {
	private static final long serialVersionUID = 1L;

	public NDException() {
		super();
	}

	public NDException(String detailMessage) {
		super(detailMessage);
	}
}