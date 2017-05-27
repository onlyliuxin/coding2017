package com.coding.week9;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Administrator on 2017/5/7 0007.
 */
public class QuickMinStackTest {

    @Test
    public void testFindMin() throws Exception {
        QuickMinStack quickMinStack = new QuickMinStack();
        quickMinStack.push(10);
        quickMinStack.push(9);
        quickMinStack.push(100);
        quickMinStack.push(19);
        Assert.assertEquals(quickMinStack.findMin(), 9);
    }
}