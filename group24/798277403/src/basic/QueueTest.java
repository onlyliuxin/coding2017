package basic;

import org.junit.Before;
import org.junit.Test;

/**
 * 自己实现的队列，先进先出
 * Created by zhouliang on 2017-03-10.
 */
public class QueueTest {
    private Queue<Integer> queue = new Queue<>();


    @Before
    public void setUp(){
        for(int i=0; i<10; i++){
            queue.enQueue(i);
        }
    }

    @Test
    public void enQueue() throws Exception {
        System.out.println(queue.size());
    }

    @Test
    public void deQueue() throws Exception {
        System.out.println(queue.deQueue()+" "+queue.size());
    }

    @Test
    public void isEmpty() throws Exception {

    }

}