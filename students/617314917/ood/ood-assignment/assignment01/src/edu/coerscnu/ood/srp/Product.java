package edu.coerscnu.ood.srp;

/**
 * 产品类，包含id和描述两个属性
 * 
 * @author xujie
 *
 */
public class Product {
	protected String productID = null;
	protected String productDesc = null;

	public Product(String id, String desc) {
		productID = id;
		productDesc = desc;
	}

	public void setProductID(String id) {
		productID = id;
	}

	public void setProductDesc(String desc) {
		productDesc = desc;
	}

	public String getProductID() {
		return productID;
	}

	public String getProductDesc() {
		return productDesc;
	}
}
