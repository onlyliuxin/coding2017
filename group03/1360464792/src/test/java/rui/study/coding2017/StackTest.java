package rui.study.coding2017;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * 测试栈
 * Created by 赵睿 on 2017/2/25.
 */
public class StackTest {
    @Test
    public void push() throws Exception {
        Stack stack=new Stack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());

    }

    @Test
    public void peek() throws Exception {
        Stack stack=new Stack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack.peek());
        System.out.println(stack.peek());


    }

    @Test
    public void isEmpty() throws Exception {
        Stack stack=new Stack();
        System.out.println(stack.isEmpty());
        stack.push(1);
        System.out.println(stack.isEmpty());

    }

    @Test
    public void size() throws Exception {
        Stack stack=new Stack();
        System.out.println(stack.size());
        stack.push(1);
        System.out.println(stack.size());
    }

}