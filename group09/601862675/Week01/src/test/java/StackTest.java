import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;

public class StackTest {
    @Rule
    public final SystemOutRule log = new SystemOutRule().enableLog();

    @Test
    public void testPush() {
        log.clearLog();
        Stack stack = new Stack();
        stack.push("1");
        System.out.print(stack);
        Assert.assertEquals(1, stack.size());
        Assert.assertEquals("Stack: [ 1 ]", log.getLog());
    }

    @Test
    public void testPop() {
        Stack stack = new Stack();
        stack.push(10);
        Object o = stack.pop();
        Assert.assertEquals(10, o);
        Assert.assertEquals(0, stack.size());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testPopWithException() {
        Stack stack = new Stack();
        stack.push(10);
        stack.pop();
        stack.pop();
    }

    @Test
    public void testPeek() {
        Stack stack = new Stack();
        stack.push(10);
        Object o = stack.peek();
        Assert.assertEquals(10, o);
        Assert.assertEquals(1, stack.size());
    }

    @Test
    public void testIsEmpty() {
        Stack stack = new Stack();
        stack.push(10);
        Assert.assertEquals(false, stack.isEmpty());
        stack.pop();
        Assert.assertEquals(true, stack.isEmpty());
    }

    @Test
    public void testSize() {
        Stack stack = new Stack();
        stack.push(1);
        Assert.assertEquals(1, stack.size());
    }
}
