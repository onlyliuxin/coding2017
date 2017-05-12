package com.johnChnia.coding2017.basic.stack;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by john on 2017/5/7.
 */
public class QuickMinStackTest {
    QuickMinStack quickMinStack1;
    QuickMinStack quickMinStack2;
    QuickMinStack quickMinStack3;
    @Before
    public void setUp() throws Exception {
        quickMinStack1 = new QuickMinStack();
        quickMinStack2 = new QuickMinStack();
        quickMinStack3 = new QuickMinStack();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testPush() throws Exception {
        for (int i = 0; i < 4; i++) {
            quickMinStack1.push(i);
        }
        Assert.assertEquals("3,2,1,0,", quickMinStack1.toString());
    }

    @Test
    public void testPop() throws Exception {
        for (int i = 0; i < 4; i++) {
            quickMinStack2.push(i);
        }
        quickMinStack2.pop();
        Assert.assertEquals("2,1,0,", quickMinStack2.toString());
    }

    @Test
    public void testFindMin() throws Exception {
        for (int i = 0; i < 4; i++) {
            quickMinStack3.push(i);
        }
        quickMinStack3.pop();
        Assert.assertEquals(0, quickMinStack3.findMin());
    }

}