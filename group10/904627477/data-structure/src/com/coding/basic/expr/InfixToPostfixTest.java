package com.coding.basic.expr;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class InfixToPostfixTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testConvert() {
		assertEquals("2 5 6 * + 7 - ", listToString(InfixToPostfix.convert("2+5*6-7")));
		assertEquals("10 2 3 * - 50 + ", listToString(InfixToPostfix.convert("10-2*3+50")));
		assertEquals("8 17 * 2 5 * + ", listToString(InfixToPostfix.convert("8*17+2*5")));
	}

	private String listToString(List<Token> tokens){
		StringBuffer sb = new StringBuffer();
		for (Token token : tokens) {
			sb.append(token.toString()+" ");
		}
		return sb.toString();
	}
	
}
