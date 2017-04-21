package data_structure;

import basic.dataStructure.stack.Stack;
import basic.dataStructure.stack.StackUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by macvi on 2017/4/4.
 */
public class StackTest {

    private Stack s;

    @Before
    public void init(){
        s = new Stack();
        for(int i = 0; i < 5; i ++){
            s.push(i + "");
        }
    }

    @Test
    public void test1(){
        Assert.assertEquals("4,3,2,1,0", s.toString());
        Assert.assertEquals("13", s.pop());
        Assert.assertEquals(13, s.size());
        Assert.assertEquals("12", s.peek());
    }

    @Test
    public void test2(){
//        Assert.assertEquals("0,1,2,3,4", StackUtil.reverse(s).toString());
//        Assert.assertEquals("0,1,3,4", StackUtil.remove(s, "2").toString());
        Assert.assertEquals("[4, 3]", Arrays.toString(StackUtil.getTop(s, 2)));
    }

}
