package com.ralf.stack.expr;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TokenParserTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		TokenParser tokenParser = new TokenParser();
		
		List<Token> list = tokenParser.parse("300*20+12*5-20/4");
		Assert.assertEquals(300, list.get(0).getValue());
		Assert.assertEquals("*", list.get(1).toString());
		Assert.assertEquals(20, list.get(2).getValue());
		Assert.assertEquals("+", list.get(3).toString());
		Assert.assertEquals(12, list.get(4).getValue());
		Assert.assertEquals("*", list.get(5).toString());
		Assert.assertEquals(5, list.get(6).getValue());
		Assert.assertEquals("-", list.get(7).toString());
		Assert.assertEquals(20, list.get(8).getValue());
		Assert.assertEquals("/", list.get(9).toString());
		Assert.assertEquals(4, list.get(10).getValue());
		
	}

}
