package data_structure;

import org.junit.Assert;
import org.junit.Test;
import basic.dataStructure.Queue;

/**
 * Created by macvi on 2017/4/4.
 */
public class QueueTest {

    private Queue newQueue(){
        Queue q = new Queue();
        for(int i = 0; i < 13; i++){
            q.enQueue(i + "");
        }

        return q;
    }

    @Test
    public void testEnqueue(){
        Queue q = newQueue();
        q.enQueue(10 + "");
        q.enQueue( "xxoo");
        System.out.println("queue-->" + q.toString());
    }

    @Test
    public void testSize(){
        Queue q = newQueue();

        Assert.assertEquals(13, q.size());
    }

    @Test
    public void testDequeue(){
        Queue q = newQueue();
        Object obj = q.deQueue();

        Assert.assertEquals("0", obj);
    }

    @Test
    public void testIsEmpty(){
        Queue q = newQueue();

        Assert.assertEquals(false, q.isEmpty());
    }
}
