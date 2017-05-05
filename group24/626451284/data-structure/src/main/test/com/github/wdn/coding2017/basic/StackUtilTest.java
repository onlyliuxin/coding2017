package com.github.wdn.coding2017.basic;

import com.github.wdn.coding2017.basic.stack.StackUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by Administrator on 2017/4/6 0006.
 */
public class StackUtilTest {

    @Test
    public void testReverse(){
        Stack stack = new Stack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        StackUtil.reverse(stack);
        while (!stack.isEmpty()){
            System.out.println(stack.pop());
        }
    }
    @Test
    public void testRemove(){
        Stack stack = new Stack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        StackUtil.remove(stack,4);
        while (!stack.isEmpty()){
            System.out.println(stack.pop());
        }
    }
    @Test
    public void testGetTop(){
        Stack stack = new Stack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        Object[] o = StackUtil.getTop(stack,0);
        System.out.println(Arrays.toString(o));
        while (!stack.isEmpty()){
            System.out.println(stack.pop());
        }
    }
    @Test
    public void testIsValidPairs(){
        Assert.assertEquals(true,StackUtil.isValidPairs("([e{d}f])"));
        Assert.assertEquals(false,StackUtil.isValidPairs("([b{x]y})"));
        Assert.assertEquals(false,StackUtil.isValidPairs("([({e}f])"));
    }
}
