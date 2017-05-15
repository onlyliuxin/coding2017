package week07.expr.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	InfixExprTest.class,
	InfixToPostfixTest.class,
	PostfixExprTest.class,
	PrefixExprTest.class,
	TokenParserTest.class
})

public class AllTest {

}
