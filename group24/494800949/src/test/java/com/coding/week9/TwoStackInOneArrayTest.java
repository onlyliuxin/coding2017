package com.coding.week9;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Administrator on 2017/5/7 0007.
 */
public class TwoStackInOneArrayTest {

    private TwoStackInOneArray stackInOneArray;

    @Before
    public void setup(){
        stackInOneArray = new TwoStackInOneArray();
    }
    @Test
    public void testPush1() throws Exception {
        stackInOneArray.push1(10);
        stackInOneArray.push1(20);
        stackInOneArray.push1(30);
        stackInOneArray.push1(88);
        stackInOneArray.push1(30);
        stackInOneArray.push1(99);
        stackInOneArray.push2(100);
        stackInOneArray.push2(90);
        stackInOneArray.push2(80);
        stackInOneArray.push2(77);
        stackInOneArray.push2(66);
        Assert.assertEquals(stackInOneArray.peek1(), 99);
        Assert.assertEquals(stackInOneArray.pop1(), 99);
        Assert.assertEquals(stackInOneArray.pop1(), 30);

        Assert.assertEquals(stackInOneArray.peek2(), 66);
        Assert.assertEquals(stackInOneArray.pop2(), 66);
        Assert.assertEquals(stackInOneArray.pop2(), 77);
        Assert.assertEquals(stackInOneArray.pop2(), 80);
        Assert.assertEquals(stackInOneArray.pop2(), 90);
    }


}