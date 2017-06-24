package com.coderising.ood.srp.good.template;

import java.util.Map;

public class TextMailBodyTemplate implements MailBodyTemplate {
	
	private Map<String,String >paramMap ;
	
	public TextMailBodyTemplate(Map<String,String> map){
		paramMap = map;
	}
	
	@Override
	public String render() {
		//使用某种模板技术实现Render
		return null;
	}

}
