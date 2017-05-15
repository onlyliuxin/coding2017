package main.coding_170507;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by peterchen on 2017/5/5.
 */
public class QuickMinStackTest extends TestCase {
    @Test
    public void testPush() throws Exception {
        QuickMinStack minStack = new QuickMinStack();
        minStack.push(5);
        minStack.push(10);
        minStack.push(3);
        Assert.assertEquals(minStack.pop(),3);
        Assert.assertEquals(minStack.pop(),10);
        Assert.assertEquals(minStack.pop(),5);
    }

    @Test
    public void testPop() throws Exception {
        QuickMinStack minStack = new QuickMinStack();
        minStack.push(5);
        minStack.push(10);
        Assert.assertEquals(minStack.pop(),10);
        Assert.assertEquals(minStack.pop(),5);
    }

    @Test
    public void testFindMin() throws Exception {
        QuickMinStack minStack = new QuickMinStack();
        minStack.push(15);
        minStack.push(10);
        Assert.assertEquals(minStack.findMin(),10);
        minStack.pop();
        minStack.push(20);
        Assert.assertEquals(minStack.findMin(),15);

    }

}