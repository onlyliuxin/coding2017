package com.donaldy.basic.stack;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by DonaldY on 2017/5/4.
 */
public class QuickMinStackTest {

    @Test
    public void test() {

        QuickMinStack qmStack = new QuickMinStack();

        int [] intArr = {13, 2, 6, 23, 12, 1, 1, 5};

        for (int i = 0; i < intArr.length; ++i) {
            qmStack.push(intArr[i]);
        }

        Assert.assertEquals(5, qmStack.pop());
        Assert.assertEquals(1, qmStack.pop());

        Assert.assertEquals(1, qmStack.findMin());
        Assert.assertEquals(1, qmStack.pop());

        Assert.assertEquals(2, qmStack.findMin());

    }
}
