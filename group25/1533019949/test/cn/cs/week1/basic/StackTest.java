package cn.cs.week1.basic;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by YCS on 2017/3/12.
 */
public class StackTest {
  private Stack myStack;
  @Before
  public void setUp(){
    myStack = new Stack();
  }
  @Test
  public void test(){
    //isEmpty()
    Assert.assertEquals(myStack.isEmpty(),true);
    //size()
    Assert.assertEquals(myStack.size(),0);
    //push()
    myStack.push(1);
    myStack.push(2);
    //isEmpty()
    Assert.assertEquals(myStack.isEmpty(),false);
    //size()
    Assert.assertEquals(myStack.size(),2);
    //peek()
    Assert.assertEquals(myStack.peek(),2);
    Assert.assertEquals(myStack.size(),2);
    //pop()
    Assert.assertEquals(myStack.pop(),2);
    Assert.assertEquals(myStack.size(),1);
    Assert.assertEquals(myStack.peek(),1);
  }
}
