package com.coderising.ood.srp.bean;

/**
 * 推送的消息 实体
 * <p>标题: </p>
 * <p>描述: </p>
 * @autho zx
 * @time 2017年6月13日 上午2:01:17
*/
public class Message extends ServerBean{
	
	/**  */
	private static final long serialVersionUID = 3850050693864793038L;

	/** 标题 */
	private String subject;
	
	/** 消息 */
	private String message;

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
