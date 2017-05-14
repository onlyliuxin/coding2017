package data_structure.stack;

import basic.dataStructure.stack.*;
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
//        Assert.assertEquals("[4, 3]", Arrays.toString(StackUtil.getTop(s, 2)));
//        Assert.assertEquals(true, StackUtil.isValidPairs("([e{d}f])"));
        Assert.assertEquals(false, StackUtil.isValidPairs("([b{x]y})"));

    }

    /**
     * QuickMinStack
     *
     * StackWithTwoQueues
     *
     * TwoStackInOneArray
     */
    @Test
    public void test3(){
        //QuickMinStack
        {
            QuickMinStack qms = new QuickMinStack();
            qms.push(0);
            qms.push(-2);
            qms.push(10);
            for(int i = 0; i < 14; i++){
                qms.push(i);
            }
            Assert.assertEquals(-2, qms.findMin());
            Assert.assertEquals(17, qms.size());
            Assert.assertEquals(13, qms.pop());
            System.out.println(qms.toString());
        }
        //StackWithTwoQueues
        {
            StackWithTwoQueues stack = new StackWithTwoQueues();
            stack.push(0);
            stack.push(2);
            stack.push(5);
            stack.push(10);
            Assert.assertEquals(10, stack.pop());
        }
        //TwoStackInOneArray
        {
            TwoStackInOneArray stack = new TwoStackInOneArray();
            for(int i = 0; i < 7; i++){
                stack.push1(i);
            }

            for(int i = 0; i < 8; i++){
                stack.push2(i);
            }
            System.out.println("size1 ->" + stack.size1());
            System.out.println("size2 ->" + stack.size2());
            System.out.println(stack.toString());

            Assert.assertEquals(stack.peek1(), 6);
            Assert.assertEquals(stack.peek2(), 7);

            Assert.assertEquals(stack.pop1(), 6);
            Assert.assertEquals(stack.size1(), 6);

            Assert.assertEquals(stack.pop2(), 7);
            Assert.assertEquals(stack.size2(), 7);
        }
    }

}
