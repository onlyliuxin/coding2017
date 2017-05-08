import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by gongxun on 2017/5/8.
 */
public class QuickMinStackTest {

    @Before
    public void startUp() {

    }

    @After
    public void tearDown() {

    }

    @Test
    public void pushTest() {
        QuickMinStack stack = new QuickMinStack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        System.out.println(stack);
    }

    @Test
    public void popTest() {
        QuickMinStack stack = new QuickMinStack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        System.out.println(stack);
    }

    @Test
    public void findMinTest() {
        QuickMinStack stack = new QuickMinStack();
        stack.push(3);
        stack.push(4);
        stack.push(2);
        stack.push(1);
        stack.push(7);
        int min = stack.findMin();
        System.out.println(min);
    }
}
