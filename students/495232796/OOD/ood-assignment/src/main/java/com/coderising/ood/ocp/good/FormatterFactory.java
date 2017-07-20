package com.coderising.ood.ocp.good;

public class FormatterFactory {
	public static Formatter createFormatter(int type){
		if(type == 1){
			return  new RawFormatter();
		}
		if (type == 2){
			 return new HtmlFormatter();
		}
		return null;
	}	
}
