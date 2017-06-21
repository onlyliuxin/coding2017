package com.coderising.ood.srp.bean;

import java.io.Serializable;

/**
 * 服务配置
 * <p>标题: </p>
 * <p>描述: </p>
 * @autho zx
 * @time 2017年6月13日 上午1:22:38
*/
public class ServerBean implements Serializable{
	
	/**  */
	private static final long serialVersionUID = -1842399098772577584L;

	/** 服务器地址 */
	private String smtpHost;
	
	/** 备用服务器地址 */
	private String altSmtpHost;
	
	/** 发送地址 */
	private String fromAddress;
	
	/** 接受地址 */
	private String toAddress;

	public String getSmtpHost() {
		return smtpHost;
	}

	public void setSmtpHost(String smtpHost) {
		this.smtpHost = smtpHost;
	}

	public String getAltSmtpHost() {
		return altSmtpHost;
	}

	public void setAltSmtpHost(String altSmtpHost) {
		this.altSmtpHost = altSmtpHost;
	}

	public String getFromAddress() {
		return fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	public String getToAddress() {
		return toAddress;
	}

	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}
	
}
