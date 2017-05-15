package main.coding_170507;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by peterchen on 2017/5/5.
 */
public class TwoStackInOneArrayTest extends TestCase {
    @Test
    public void testPush1() throws Exception {
        TwoStackInOneArray inOneArray = new TwoStackInOneArray();
        inOneArray.push1(1);
        inOneArray.push1(2);
        inOneArray.push1(3);
        Assert.assertEquals(3,inOneArray.peek1());
        inOneArray.pop1();
        Assert.assertEquals(2,inOneArray.peek1());
    }

    @Test
    public void testPop1() throws Exception {
        TwoStackInOneArray inOneArray = new TwoStackInOneArray();
        inOneArray.push1(10);
        inOneArray.push1(20);
        inOneArray.pop1();
        inOneArray.push1(30);
        inOneArray.push1(50);
        Assert.assertEquals(50,inOneArray.peek1());
        inOneArray.pop1();
        Assert.assertEquals(30,inOneArray.peek1());
    }

    @Test
    public void testPeek1() throws Exception {
        TwoStackInOneArray inOneArray = new TwoStackInOneArray();
        inOneArray.push1(100);
        Assert.assertEquals(100,inOneArray.peek1());
    }

    @Test
    public void testPush2() throws Exception {
        TwoStackInOneArray inOneArray = new TwoStackInOneArray();
        inOneArray.push2(10);
        inOneArray.push2(20);
        inOneArray.push1(15);
        inOneArray.push1(25);
        Assert.assertEquals(20,inOneArray.peek2());
        Assert.assertEquals(25,inOneArray.peek1());
    }

    @Test
    public void testPop2() throws Exception {
        TwoStackInOneArray inOneArray = new TwoStackInOneArray();
        inOneArray.push2(10);
        inOneArray.push2(20);
        inOneArray.pop2();
        inOneArray.pop2();
        inOneArray.push2(25);
        inOneArray.push2(24);
        Assert.assertEquals(24,inOneArray.peek2());
        inOneArray.pop2();
        Assert.assertEquals(25,inOneArray.peek2());
    }

    @Test
    public void testPeek2() throws Exception {
        TwoStackInOneArray inOneArray = new TwoStackInOneArray();
        inOneArray.push2(100);
        inOneArray.push2(200);
        Assert.assertEquals(200,inOneArray.peek2());
    }

}