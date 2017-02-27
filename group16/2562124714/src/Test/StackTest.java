package Test;

import com.coding.basic.Stack;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by zhangwj on 2017/2/23.
 */
public class StackTest {
    private Stack stack = new Stack();
    @Before
    public void setUp() throws Exception {
        System.out.println("初始化栈,元素为a,b,c,d");

        String[] s = {"a", "b","c","d"};
        for (String a:s
                ) {
            stack.push(a);
        }

    }

    @Test
    public void push() throws Exception {
        stack.push("aaa");
        assertEquals(5, stack.size());
        assertEquals("aaa", stack.peek());

    }

    @Test
    public void pop() throws Exception {
        assertEquals("d", stack.pop());
        assertEquals(3, stack.size());

    }

    @Test
    public void peek() throws Exception {
        assertEquals("d", stack.peek());
        assertEquals(4, stack.size());
    }

    @Test
    public void isEmpty() throws Exception {
        assertEquals(false, stack.isEmpty());
    }

    @Test
    public void size() throws Exception {
        assertEquals(4, stack.size());
    }

}