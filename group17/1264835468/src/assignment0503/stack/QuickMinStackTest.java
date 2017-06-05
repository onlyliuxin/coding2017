package assignment0503.stack;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Administrator on 2017/5/7.
 */
public class QuickMinStackTest {
    QuickMinStack quickMinStack = new QuickMinStack();

    @Test
    public void minStackTest() {

        pushAll(5, 2, 4, 1, 7);
        Assert.assertEquals(quickMinStack.pop(), 7);
        Assert.assertEquals(quickMinStack.findMin(), 1);
        Assert.assertEquals(quickMinStack.pop(), 1);
        Assert.assertEquals(quickMinStack.findMin(), 2);
        pushAll(0);
        Assert.assertEquals(quickMinStack.findMin(), 0);
        quickMinStack.pop();
        Assert.assertEquals(quickMinStack.findMin(), 2);
    }

    private void pushAll(int... data) {
        for (int i : data) {
            quickMinStack.push(i);
        }
    }

}