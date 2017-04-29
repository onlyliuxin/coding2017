package data_structure;

import org.junit.Assert;
import org.junit.Test;
import basic.dataStructure.Stack;

/**
 * Created by macvi on 2017/4/4.
 */
public class StackTest {

    private Stack getStack(){
        Stack s = new Stack();
        for(int i = 0; i < 14; i ++){
            s.push(i + "");
        }

        return s;
    }

    @Test
    public void pushTest(){
        Stack s = getStack();

        System.out.println("stack-->" + s.toString());
    }

    @Test
    public void testSize(){
        Stack s = getStack();

        Assert.assertEquals(14, s.size());
    }

    @Test
    public void testPeek(){
        Stack s = getStack();

        Assert.assertEquals("13", s.peek());
    }

    @Test
    public void testPop(){
        Stack s = getStack();

        Assert.assertEquals("13", s.pop());
        Assert.assertEquals(13, s.size());
    }
}
