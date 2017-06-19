package com.coderising.ood.bean;

import java.util.List;

/**
 * <p>Title: ProductBean</p>
 * <p>Description: 产品信息</p>
 * <p>Company: smartisan</p>
 * @author Administrator
 * @date 2017年6月18日
 */
public class ProductBean {
	
	/**
	 * 产品编号
	 */
	private String productID;
	
	/**
	 * 产品描述
	 */
	private String productDesc;

	private List<UserBean> users;
	
	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

	public String getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	public List<UserBean> getUsers() {
		return users;
	}

	public void setUsers(List<UserBean> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "ProductBean [productID=" + productID + ", productDesc="
				+ productDesc + ", users=" + users + "]";
	}

}
