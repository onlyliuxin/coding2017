package test.xdx.homework.first;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import xdx.homework.first.Stack;

/**
 * Stack Tester.
 *
 * @version 1.0
 */
public class StackTest {

    private Stack<String> defaultStack;

    @Before
    public void before() throws Exception {

        defaultStack = new Stack<>();
        defaultStack.push("孙悟空");
        defaultStack.push("唐僧");
        defaultStack.push("猪八戒");
        defaultStack.push("沙僧");
    }

    @After
    public void after() throws Exception {
    }

    /**
     *
     * Method: push(E e)
     *
     */
    @Test
    public void testPush() throws Exception {

        Stack<String> testStack = new Stack<>();
        testStack.push("java");
        testStack.push("C++");
        testStack.push("python");
        System.out.println(testStack);
    }

    /**
     *
     * Method: pop()
     *
     */
    @Test
    public void testPop() throws Exception {

        Stack<String> testStack = new Stack<>();
        testStack.push("java");
        testStack.push("C++");
        testStack.push("python");
        System.out.println(testStack);
        Assert.assertEquals("python", testStack.pop());
        Assert.assertEquals("C++", testStack.pop());
        Assert.assertEquals("java", testStack.pop());

    }

    /**
     *
     * Method: isEmpty()
     *
     */
    @Test
    public void testIsEmpty() throws Exception {

        Stack<String> testStack = new Stack<>();
        testStack.push("java");
        testStack.push("C++");
        testStack.push("python");
        System.out.println(testStack);
        Assert.assertEquals("python", testStack.pop());
        Assert.assertEquals("C++", testStack.pop());
        Assert.assertEquals("java", testStack.pop());
        Assert.assertTrue(testStack.isEmpty());
    }

    /**
     *
     * Method: peek()
     *
     */
    @Test
    public void testPeek() throws Exception {
        Assert.assertEquals("沙僧", defaultStack.peek());
    }

    /**
     *
     * Method: size()
     *
     */
    @Test
    public void testSize() throws Exception {
        Assert.assertEquals(4, defaultStack.size());
    }

    /**
     *
     * Method: clear()
     *
     */
    @Test
    public void testClear() throws Exception {

        Stack<String> testStack = new Stack<>();
        testStack.push("java");
        testStack.push("C++");
        testStack.push("python");
        System.out.println("清空前:" + testStack);
        testStack.clear();
        System.out.println("清空后:" + testStack);
        Assert.assertTrue(testStack.isEmpty());


    }


}
