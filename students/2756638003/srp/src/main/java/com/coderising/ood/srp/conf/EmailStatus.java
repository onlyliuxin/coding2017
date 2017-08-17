package com.coderising.ood.srp.conf;

/**
 * 邮件发送状态常量
 */
public enum EmailStatus {
	/**
	 * 未发送
	 */
	READY(0),
	/**
	 * 已发送
	 */
	SEND(1),
	/**
	 * 已查阅
	 */
	RECEIVED(2),
	/**
	 * 被退回
	 */
	REJECTED(3);

	@SuppressWarnings("unused")
	private int value;

	private EmailStatus(int value) {
		this.value = value;
	}

}
