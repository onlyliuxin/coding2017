package cn.cs.week1.basic;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by YCS on 2017/3/12.
 */
public class QueueTest {
  @Test
  public void test(){
    Queue queue = new Queue();
    //size()
    Assert.assertEquals(queue.size(),0);
    //enQueue()
    queue.enQueue(1);
    queue.enQueue(2);
    Assert.assertEquals(queue.size(),2);
    //deQueue()
    Assert.assertEquals(queue.deQueue(),2);
    Assert.assertEquals(queue.deQueue(),1);
    //isEmpty()
    Assert.assertEquals(queue.isEmpty(),true);
  }
}
