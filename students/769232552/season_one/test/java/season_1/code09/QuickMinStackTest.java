package code09;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by yyglider on 2017/5/4.
 */
public class QuickMinStackTest {
    QuickMinStack stack = new QuickMinStack();

    @Before
    public void before(){
        stack.push(3);
        stack.push(4);
        stack.push(2);
        stack.push(1);
    }

    @Test
    public void findMin() throws Exception {
        Assert.assertEquals(1,stack.findMin());
        stack.pop();
        Assert.assertEquals(2,stack.findMin());
        stack.pop();
        Assert.assertEquals(3,stack.findMin());
        stack.push(0);
        Assert.assertEquals(0,stack.findMin());
    }

}