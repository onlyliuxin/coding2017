package com.coderising.ood.bean;

import java.util.List;

/**
 * <p>Title: UserBean</p>
 * <p>Description: 用户信息</p>
 * <p>Company: smartisan</p>
 * @author Administrator
 * @date 2017年6月18日
 */
public class UserBean {
	
	/**
	 * 用户ID
	 */
	private String userid;
	
	/**
	 * 用户姓名
	 */
	private String name;
	
	/**
	 * 邮箱
	 */
	private String email;
	
	/**
	 * 邮件推送标识 0-不推送;1-推送
	 */
	private String sendFlag;
	
	/**
	 * 关注的产品
	 */
	private List<ProductBean> pros;

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSendFlag() {
		return sendFlag;
	}

	public void setSendFlag(String sendFlag) {
		this.sendFlag = sendFlag;
	}

	public List<ProductBean> getPros() {
		return pros;
	}

	public void setPros(List<ProductBean> pros) {
		this.pros = pros;
	}

	@Override
	public String toString() {
		return "UserBean [userid=" + userid + ", name=" + name + ", email="
				+ email + ", sendFlag=" + sendFlag + ", pros=" + pros + "]";
	}

}
