package test;

import junit.framework.Assert;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import stack.Stack;
import stack.StackUtil;

import static com.sun.xml.internal.ws.dump.LoggingDumpTube.Position.After;
import static com.sun.xml.internal.ws.dump.LoggingDumpTube.Position.Before;

/** 
* stack.StackUtil Tester.
* 
* @author <Authors name> 
* @since <pre>ËÄÔÂ 8, 2017</pre> 
* @version 1.0 
*/ 
public class StackUtilTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: reverse(stack.Stack s)
* 
*/ 
@Test
public void testReverse() throws Exception { 
//TODO: Test goes here...
    Stack s = new Stack();
    for (int i = 1; i <=5 ; i++) {
        s.push(i);
    }
    Assert.assertEquals("5,4,3,2,1,",s.toString());
    StackUtil.reverse(s);

    Assert.assertEquals("1,2,3,4,5,",s.toString());
} 

/** 
* 
* Method: remove(stack.Stack s, Object o)
* 
*/ 
@Test
public void testRemove() throws Exception {
    Stack s = new Stack();
    for (int i = 1; i <=5 ; i++) {
        s.push(i);
    }
    Assert.assertEquals("5,4,3,2,1,",s.toString());
    StackUtil.remove(s,1);

    Assert.assertEquals("5,4,3,2,",s.toString());
//TODO: Test goes here... 
} 

/** 
* 
* Method: getTop(stack.Stack s, int len)
* 
*/ 
@Test
public void testGetTop() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: isValidPairs(String s) 
* 
*/ 
@Test
public void testIsValidPairs() throws Exception {
//TODO: Test goes here...
    String  s = "([e{d}f])";
    boolean b = StackUtil.isValidPairs(s);
    Assert.assertEquals(true,b);
    s = "([b{x]y})";
    b = StackUtil.isValidPairs(s);
    Assert.assertEquals(false,b);

} 


} 
