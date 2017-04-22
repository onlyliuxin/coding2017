package com.coding.basic.stack.expr;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

//Yang：前序表达式的用处在于简化了中序的计算过程 -- 
//从右向左遍历前序表达式，只要遇到符号即POP栈顶的两个元素做计算，然后再push回栈；
//怎样把一个中序变成前序呢？
//先转换成2叉树，然后从下至上，从左至右，follow这样的原则：根节点-》左节点-》右节点

public class PrefixExprTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testEvaluate() {
		{
			// 2*3+4*5 
			PrefixExpr expr = new PrefixExpr("+ * 2 3* 4 5");
			Assert.assertEquals(26, expr.evaluate(),0.001f);
		}
		{
			// 4*2 + 6+9*2/3 -8
			PrefixExpr expr = new PrefixExpr("-++6/*2 9 3 * 4 2 8");
			Assert.assertEquals(12, expr.evaluate(),0.001f);
		}
		{
			//(3+4)*5-6
			PrefixExpr expr = new PrefixExpr("- * + 3 4 5 6");
			Assert.assertEquals(29, expr.evaluate(),0.001f);
		}
		{
			//1+((2+3)*4)-5
			PrefixExpr expr = new PrefixExpr("- + 1 * + 2 3 4 5");
			Assert.assertEquals(16, expr.evaluate(),0.001f);
		}
		
		
	}

}
