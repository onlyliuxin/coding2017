package com.donaldy.basic.stack;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by DonaldY on 2017/5/4.
 */
public class TwoStackInOneArrayTest {

    @Test
    public void test() {

        //第二个栈为空
        TwoStackInOneArray stack = new TwoStackInOneArray();

        for (int i = 1; i <= 10; ++i) {
            stack.push1(i);
            Assert.assertEquals(i, stack.peek1());
        }

        for (int i = 10; i >= 2; --i) {
            Assert.assertEquals(i, stack.pop1());
        }

        //第一个栈为空
        stack = new TwoStackInOneArray();

        for (int i = 1; i <= 10; ++i) {
            stack.push2(i);
            Assert.assertEquals(i, stack.peek2());
        }

        for (int i = 10; i >= 2; --i) {
            Assert.assertEquals(i, stack.pop2());
        }


        //两个栈
        stack = new TwoStackInOneArray();

        for (int i = 1; i <= 4; ++i) {
            stack.push1(i);
            stack.push2(i);
        }

        stack.push1(5);

        Assert.assertEquals(5, stack.peek1());
        Assert.assertEquals(4, stack.peek2());

        for (int i = 5; i >= 1; --i) {
            Assert.assertEquals(i, stack.pop1());
        }

        for (int i = 4; i >= 1; --i) {
            Assert.assertEquals(i, stack.pop2());
        }

    }

}
