package data_structure.queue;

import basic.dataStructure.queue.QueueWithTwoStacks;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author : 温友朝
 * @date : 2017/5/2
 */
public class TwoStacksQueueTest {

    private QueueWithTwoStacks queue;

    @Before
    public void init(){
        queue = new QueueWithTwoStacks();
    }

    @After
    public void after(){

    }

    @Test
    public void testQueue(){
        queue.enQueue(10 + "");
        queue.enQueue( "xxoo");
        queue.enQueue( "ppxxoo");
        Assert.assertEquals(3, queue.size());
        Assert.assertEquals("10", queue.deQueue());
    }
}
