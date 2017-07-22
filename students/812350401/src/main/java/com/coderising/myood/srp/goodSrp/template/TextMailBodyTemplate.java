package com.coderising.myood.srp.goodSrp.template;


public class TextMailBodyTemplate implements MailBodyTemplate {
	String productDescription;
	String name;
	String toAdress;
	public TextMailBodyTemplate(String name, String productDescription, String toAdress){
		this.productDescription = productDescription;
		this.name = name;
		this.toAdress = toAdress;
	}
	
	@Override
	public String render() {
		//使用某种模板技术实现Render
        return "尊敬的 "+ name +", 您关注的产品 " + productDescription + " 降价了，欢迎购买!" ;
	}

}
