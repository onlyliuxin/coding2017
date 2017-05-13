import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by gongxun on 2017/5/9.
 */
public class StackWithTwoQueuesTest {

    @Before
    public void startUp() {

    }

    @After
    public void tearDown() {

    }

    @Test
    public void pushTest() {
        StackWithTwoQueues stack = new StackWithTwoQueues();
        stack.push(1);
        stack.push(2);
        stack.push(3);

        int ele = stack.pop();
        System.out.println(ele);
        ele = stack.pop();
        System.out.println(ele);
        stack.push(8);
        System.out.println(stack);
    }
}
