package com.coderising.dp.decorator;

public class DecoratorTest {
	public static void main(String[] args) {
		Email e1 = new EmailEcript(new EmailDeclare(new EmailImpl("???§Ó??????????")));
		System.out.println(e1.getContent());
		
		e1 = new EmailDeclare (new EmailEcript(new EmailImpl("???§Ó??????????")));
		System.out.println(e1.getContent());
	}
}
