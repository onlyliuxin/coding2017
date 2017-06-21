package com.coderising.ood.srp;

public class Message {
	
	public static StringBuilder saleMessage(String userName ,String goodsName){
		StringBuilder buffer = new StringBuilder();
		buffer.append("Subject:").append("您关注的产品降价了").append("\n");
		buffer.append("Content:").append("尊敬的 "+userName+", 您关注的产品 " + goodsName + " 降价了，欢迎购买!").append("\n");
		return buffer;
	}

}
