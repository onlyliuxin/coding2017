package Test;

import com.coding.basic.Queue;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by zhangwj on 2017/2/23.
 */
public class QueueTest {
    private Queue queue = new Queue();
    @Before
    public void setUp() throws Exception {
            System.out.println("初始化队列,元素为a,b,c,d");

            String[] s = {"a", "b","c","d"};
        for (String a:s
             ) {
            queue.enQueue(a);
        }

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void enQueue() throws Exception {
            queue.enQueue("dasdas");
            assertEquals(5, queue.size());
//            assertEquals("dasdas", queue.);
    }

    @Test
    public void deQueue() throws Exception {
        assertEquals("a",queue.deQueue());
        assertEquals(3, queue.size());

    }

    @Test
    public void isEmpty() throws Exception {
        assertEquals(false, queue.isEmpty());

    }

    @Test
    public void size() throws Exception {
        assertEquals(4, queue.size());

    }

}