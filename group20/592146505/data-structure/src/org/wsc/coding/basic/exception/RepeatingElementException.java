package org.wsc.coding.basic.exception;

/**
 *
 * 重复元素异常
 * @author Administrator
 * @date 2017年2月26日下午4:15:49
 * @version v1.0
 *
 */
public class RepeatingElementException extends RuntimeException {

	private static final long serialVersionUID = 4729177529481680909L;

	public RepeatingElementException() {
		super();
	}

	public RepeatingElementException(String message) {
		super(message);
	}
	
}
