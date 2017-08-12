package com.github.orajavac.coding2017.ood.dp.decorator;

public class EmailImpl implements Email{
	private String content;

	public EmailImpl(String content) {
		this.content = content;
	}
	public String getContent()   {
		return content;
	}
}
