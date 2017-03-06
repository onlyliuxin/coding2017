package dataStructure;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by zj on 2017/2/24.
 */
public class StackTest {
    Stack list = null;

    @Before
    public void setUp() throws Exception {
        list = new Stack();
        for (int i = 0; i < 5; i++) {
            list.push(i);
        }
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("----------------tearDown------------------");
        int count=list.size();
        for (int i = 0; i < count; i++) {
            System.out.println("list.pop():"+list.pop());
        }
    }

    @Test
    public void push() throws Exception {
        list.push(11);
    }

    @Test
    public void pop() throws Exception {
        System.out.println("list.pop():"+list.pop());
        System.out.println("list.pop():"+list.pop());
    }

    @Test
    public void peek() throws Exception {
        System.out.println("list.peek():"+list.peek());
    }

    @Test
    public void testIsEmpty() throws Exception {
        System.out.println("testIsEmpty:"+list.isEmpty());
    }

    @Test
    public void testSize() throws Exception {
        System.out.println("testSize:"+list.size());
    }

}