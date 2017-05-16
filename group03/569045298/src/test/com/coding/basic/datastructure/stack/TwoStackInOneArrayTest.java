package com.coding.basic.datastructure.stack;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by zt
 * 2017/5/6 19:35
 */
public class TwoStackInOneArrayTest {

    @Test
    public void pop() throws Exception {
        TwoStackInOneArray twoStackInOneArray = new TwoStackInOneArray(15);
        for (int i = 0; i < 10; i++) {
            twoStackInOneArray.push1(new Integer(i));
            twoStackInOneArray.push2(new Integer(i + 10));
        }
        Assert.assertEquals(new Integer(9), twoStackInOneArray.pop1());
        Assert.assertEquals(new Integer(19), twoStackInOneArray.pop2());
        Assert.assertEquals(new Integer(8), twoStackInOneArray.pop1());
        Assert.assertEquals(new Integer(7), twoStackInOneArray.pop1());
        Assert.assertEquals(new Integer(18), twoStackInOneArray.pop2());
        twoStackInOneArray.push1(666);
        Assert.assertEquals(new Integer(666), twoStackInOneArray.pop1());
    }

}