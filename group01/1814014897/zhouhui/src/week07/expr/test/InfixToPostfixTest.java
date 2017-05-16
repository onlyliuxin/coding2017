package week07.expr.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import week07.expr.InfixToPostfix;
import week07.expr.Token;

public class InfixToPostfixTest {

	@Test
	public void testConvert() {
		{
			List<Token> tokens = InfixToPostfix.convert("2+3");
			Assert.assertEquals("[2, 3, +]", tokens.toString());
		}
		{
		
			List<Token> tokens = InfixToPostfix.convert("2+3*4");
			Assert.assertEquals("[2, 3, 4, *, +]", tokens.toString());
		}
		
		{
			
			List<Token> tokens = InfixToPostfix.convert("2-3*4+5");
			Assert.assertEquals("[2, 3, 4, *, -, 5, +]", tokens.toString());
		}
	}

}
