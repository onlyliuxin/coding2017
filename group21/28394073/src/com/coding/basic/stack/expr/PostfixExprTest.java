package com.coding.basic.stack.expr;



import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


//Yang：后序表达式的用处在于简化了中序的计算过程 -- 
//从左到右读表达式，如果读到操作数就将它压入栈S中，
//如果读到n元运算符(即需要参数个数为n的运算符)则取出由栈顶向下的n项按操作数运算，再将运算的结果代替原栈顶的n项，压入栈S中 。
//如果后缀表达式未读完，则重复上面过程，最后输出栈顶的数值则为结束。
//怎样把一个中序变成后序呢？

public class PostfixExprTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testEvaluate() {
//		{
//			PostfixExpr expr = new PostfixExpr("6 5 2 3 + 8 * + 3 + *");
//			Assert.assertEquals(288, expr.evaluate(),0.0f);
//		}
		{
			//9+(3-1)*3+10/2
			PostfixExpr expr = new PostfixExpr("9 3 1-3*+ 10 2/+");
			Assert.assertEquals(20, expr.evaluate(),0.0f);
		}
		
		{
			//10-2*3+50
			PostfixExpr expr = new PostfixExpr("10 2 3 * - 50 +");
			Assert.assertEquals(54, expr.evaluate(),0.0f);
		}
	}

}
