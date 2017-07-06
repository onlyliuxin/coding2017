package com.coderising.ood.srp.bean;

import java.io.Serializable;

/**
 * 商品类
 * <p>标题: </p>
 * <p>描述: </p>
 * @autho zx
 * @time 2017年6月13日 上午12:29:56
*/
public class Product implements Serializable{
	
	/**  */
	private static final long serialVersionUID = 2966621699675433678L;

	/** 商品Id */
	private String productId;
	
	/** 商品描述 */
	private String productDesc;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	
	
}
