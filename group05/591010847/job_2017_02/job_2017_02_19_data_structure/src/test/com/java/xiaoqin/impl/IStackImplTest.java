package test.com.java.xiaoqin.impl;

import com.java.xiaoqin.impl.StackImpl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * IQueueImplTest Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>二月 26, 2017</pre>
 */
public class IStackImplTest {

    private StackImpl<Integer> mStack;

    @Before
    public void before() throws Exception {
        mStack = new StackImpl<>();
    }

    @After
    public void after() throws Exception {
        mStack = null;
    }

    /**
     * Method: push(T t)
     */
    @Test
    public void pushT() throws Exception {
        for (int i = 0; i < 10; i++) {
            mStack.push(i);
        }
        System.out.println(mStack.toString());
    }

    /**
     * Method: pop
     */
    @Test
    public void pop() throws Exception {
        for (int i = 0; i < 10; i++) {
            mStack.push(i);
        }
        Assert.assertEquals(mStack.pop(), (Integer) 9);
        Assert.assertEquals(mStack.pop(), (Integer) 8);
    }

    /**
     * Method: peek
     */
    @Test
    public void peek() throws Exception {
        Assert.assertEquals(true, mStack.isEmpty());
        for (int i = 0; i < 10; i++) {
            mStack.push(i);
        }
        Assert.assertEquals(mStack.peek(), (Integer) 9);
        Assert.assertEquals(mStack.peek(), (Integer) 9);
        Assert.assertEquals(mStack.peek(), (Integer) 9);
    }

    /**
     * Method: isEmpty
     */
    @Test
    public void isEmpty() throws Exception {
        Assert.assertEquals(true, mStack.isEmpty());
        for (int i = 0; i < 10; i++) {
            mStack.push(i);
        }
        Assert.assertEquals(false, mStack.isEmpty());
    }

    /**
     * Method:  size();
     */
    @Test
    public void size() throws Exception {
        for (int i = 0; i < 10; i++) {
            mStack.push(i);
        }
        Assert.assertEquals(10, mStack.size());
    }

} 
