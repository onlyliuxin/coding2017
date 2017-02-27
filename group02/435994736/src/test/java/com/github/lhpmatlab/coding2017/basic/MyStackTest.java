package com.github.lhpmatlab.coding2017.basic; 

import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;

import static org.junit.Assert.*;

/** 
* MyStack Tester. 
* 
* @author <Authors name> 
* @since <pre>���� 26, 2017</pre> 
* @version 1.0 
*/ 
public class MyStackTest {

    MyStack<String> stack;


    @Before
    public void init() throws Exception {
        stack = new MyStack<>();
    }

    @After
    public void after() throws Exception {
    }

    /**
    *
    * Method: push(T t)
    *
    */
    @Test
    public void testPush() throws Exception {
        assertEquals("init stack ", stack.size(), 0);
        stack.push("1");
        assertEquals("pust stack ", stack.size(),1);
    }

    /**
    *
    * Method: pop()
    *
    */
    @Test
    public void testPop() throws Exception {
        assertEquals("init stack ", stack.size(), 0);
        stack.push("1");
        stack.push("2");
        stack.pop();
        assertEquals("after pop ",stack.size(),1);
    }

    /**
    *
    * Method: peek()
    *
    */
    @Test
    public void testPeek() throws Exception {
        assertEquals("init stack ", stack.size(), 0);
        stack.push("1");
        stack.push("2");
        assertEquals("peek ", stack.peek(),"2");
    }

    /**
    *测试判空方法
    * Method: isEmpty()
    *
    */
    @Test
    public void testIsEmpty() throws Exception {
        assertEquals("stack is empty ", stack.isEmpty(), true);
    }

    /**
     *测试判空方法,不为空的情况
     * Method: isEmpty()
     *
     */
    @Test
    public void testIsNotEmpty() throws Exception {
        stack.push("1");
        assertEquals("stack is empty ", stack.isEmpty(), false);
    }

    /**
    *
    * Method: size()
    *
    */
    @Test
    public void testSize() throws Exception {
        assertEquals("init stack ", stack.size(), 0);
        stack.push("1");
        stack.push("2");
        assertEquals("size is 2", stack.size(), 2);
    }


} 
