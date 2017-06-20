package com.coderising.ood.srp;

import java.util.Map;

public class PromotionMail {

	protected String toAddress = null;
	protected String subject = null;
	protected String message = null;
	protected String productID = null;
	protected String productDesc = null;

	protected static final String NAME_KEY = "NAME";
	protected static final String EMAIL_KEY = "EMAIL";
	protected static final String ID_KEY = "ID";
	protected static final String DESC_KEY = "DESC";
	
	public PromotionMail(Map user, Map good){
		String name = (String)user.get(NAME_KEY);
		this.productDesc= (String)good.get(DESC_KEY);
		this.productID= (String)good.get(ID_KEY);
		this.toAddress= (String)user.get(EMAIL_KEY);
		this.subject = "您关注的产品降价了";
		this.message = "尊敬的 "+name+", 您关注的产品 " + productDesc + " 降价了，欢迎购买!" ;
	}
}
