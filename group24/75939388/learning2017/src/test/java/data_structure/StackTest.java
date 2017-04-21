package data_structure;

import basic.dataStructure.stack.Stack;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by macvi on 2017/4/4.
 */
public class StackTest {

    private Stack s;

    @Before
    public void init(){
        s = new Stack();
        for(int i = 0; i < 14; i ++){
            s.push(i + "");
        }
    }

    @Test
    public void test1(){
        Assert.assertEquals("13,12,11,10,9,8,7,6,5,4,3,2,1,0", s.toString());
        Assert.assertEquals("13", s.pop());
        Assert.assertEquals(13, s.size());
        Assert.assertEquals("12", s.peek());
    }

    @Test
    public void test2(){

    }

}
