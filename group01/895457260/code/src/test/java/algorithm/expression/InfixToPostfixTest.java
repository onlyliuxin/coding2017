package algorithm.expression;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After; 

/** 
* InfixToPostfix Tester. 
* 
* @author <Authors name> 
* @since <pre>四月 22, 2017</pre>
* @version 1.0 
*/ 
public class InfixToPostfixTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: convert(String expr) 
* 
*/ 
@Test
public void testConvert() throws Exception { 
    {
        String expr = "2+3*4+5";
        Assert.assertEquals("[2, 3, 4, *, +, 5, +]",
                InfixToPostfix.convert(expr).toString());
    }

    {
        String expr = "3*20+12*5-40/2";
        Assert.assertEquals("[3, 20, *, 12, 5, *, +, 40, 2, /, -]",
                InfixToPostfix.convert(expr).toString());
    }

    {
        String expr = "3*20/2";
        Assert.assertEquals("[3, 20, *, 2, /]",
                InfixToPostfix.convert(expr).toString());
    }

    {
        String expr = "20/2*3";
        Assert.assertEquals("[20, 2, /, 3, *]",
                InfixToPostfix.convert(expr).toString());
    }

    {
        String expr = "10-30+50";
        Assert.assertEquals("[10, 30, -, 50, +]",
                InfixToPostfix.convert(expr).toString());
    }
} 

} 
