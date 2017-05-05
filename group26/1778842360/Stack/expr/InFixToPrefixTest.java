package FixEXpr;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class InFixToPrefixTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		{
			List<Token> tokens = InfixToPrefix.convert("2+3");
			Assert.assertEquals("[+, 2, 3]", tokens.toString());
		}
		{
			List<Token> tokens = InfixToPrefix.convert("2+3*5");
			Assert.assertEquals("[+, 2, *, 3, 5]", tokens.toString());
		}
		{
			List<Token> tokens = InfixToPrefix.convert("2*6+6");
			Assert.assertEquals("[+, *, 2, 6, 6]", tokens.toString());
		}
		{
			List<Token> tokens = InfixToPrefix.convert("2*3+2/1");
			Assert.assertEquals("[+, *, 2, 3, /, 2, 1]", tokens.toString());
		}
	}

}
