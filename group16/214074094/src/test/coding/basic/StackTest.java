package coding.basic;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @Author shane
 * @Time 2017/2/26 16:58
 * @Email stevenchenguang@gmail.com
 * @Desc ...
 */
public class StackTest extends AbstractTest {

    private static Stack stack;

    @Before
    public void before() {
        stack = new Stack();

        stack.push("a");
        stack.push("b");
        stack.push("c");
        stack.push("d");
        stack.push("e");

        printStar();
        System.out.println("Before Test data :" + stack);
        printHyphen();
    }

    @After
    public void after() {
        printHyphen();
        System.out.println("After Test data : " + stack);
        printStar();
    }

    @Test
    public void testPop() {
        Assert.assertEquals("e", stack.pop());
    }

    @Test
    public void testPeek() {
        Assert.assertEquals("e", stack.peek());
    }

    @Test
    public void testIsEmpty() {
        Assert.assertEquals(false, stack.isEmpty());

        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();

        Assert.assertEquals(true, stack.isEmpty());
    }

    @Test
    public void testSize() {
        Assert.assertEquals(5, stack.size());
    }

}
