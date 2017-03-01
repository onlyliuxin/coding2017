package cn.net.pikachu.basic; 

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import java.util.*;

/** 
* Stack Tester. 
* 
* @author pikachu 
* @since <pre>二月 25, 2017</pre> 
* @version 1.0 
*/ 
public class StackTest { 

    Stack stack;
    @Before
    public void before() throws Exception { 
        stack = new Stack();
    } 

    @After
    public void after() throws Exception { 
        
    } 

    /** 
    * 
    * Method: push(Object o) 
    * 
    */ 
    @Test
    public void testPush() {
        Assert.assertEquals("[]",stack.toString());
        for (int i = 0; i < 4; i++) {
            stack.push(i);
        }
        Assert.assertEquals("[0,1,2,3]",stack.toString());
    } 

    /** 
    * 
    * Method: pop() 
    * 
    */ 
    @Test
    public void testPop() {
        for (int i = 0; i < 4; i++) {
            stack.push(i);
        }
        for (int i = 3; i >= 0; i--) {
            Assert.assertEquals(i,stack.pop());
        }
    } 

    /** 
    * 
    * Method: peek() 
    * 
    */ 
    @Test
    public void testPeek() {
        for (int i = 0; i < 4; i++) {
            stack.push(i);
            Assert.assertEquals(i,stack.peek());
        }
    } 

    /** 
    * 
    * Method: isEmpty() 
    * 
    */ 
    @Test
    public void testIsEmpty() { 
        Assert.assertEquals(true,stack.isEmpty());
        for (int i = 0; i < 4; i++) {
            stack.push(i);
        }
        Assert.assertEquals(false,stack.isEmpty());
        for (int i = 0; i < 4; i++) {
            stack.pop();
        }
        Assert.assertEquals(true,stack.isEmpty());
    } 

    /** 
    * 
    * Method: size() 
    * 
    */ 
    @Test
    public void testSize() {
        for (int i = 0; i < 4; i++) {
            Assert.assertEquals(i,stack.size());
            stack.push(i);
        }
        Assert.assertEquals(4,stack.size());
    } 


} 
