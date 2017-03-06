import org.junit.*;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: guohairui
 * Date: 17-2-26
 * Time: 下午7:20
 * To change this template use File | Settings | File Templates.
 */
public class MyStackTest {
    @org.junit.Test
    public void testPush() throws Exception {
        MyStack myStack = new MyStack();
        myStack.push("abc1");
        myStack.push("abc2");
        myStack.push("abc3");
        System.out.println(myStack);
        myStack.push("abc4");
        System.out.println(myStack);
        System.out.println("myStack.peek:"+myStack.peek());
        myStack.pop();
        System.out.println("myStack.size"+myStack.size());
        System.out.println(myStack);
    }

    @Test
    public void testPop() throws Exception {

    }

    @Test
    public void testPeek() throws Exception {

    }
}
