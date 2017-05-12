
import com.coding.basic.LinkedList;
import com.coding.basic.Queue;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import static org.junit.Assert.assertEquals;

/**
 * Queue_Test Tester.
 *
 * @author <Authors name>
 * @since <pre>bshu 26, 2017</pre>
 * @version 1.0
 */
public class Queue_Test {

    @Test
    public void enQueue() {
        Queue que = new Queue();
        que.enQueue("kkk");
        que.enQueue("kkk1");
        assertEquals(2,que.size());
    }

    @Test
    public void deQueue() {
        Queue que = new Queue();
        que.enQueue("kkk");
        que.enQueue("kkk1");
        Object data = que.deQueue();
        assertEquals("kkk",data);
    }

    @Test
    public void isempty() {
        Queue que = new Queue();
        assertEquals(true,que.isEmpty());
        que.enQueue("kk2");
        assertEquals(false,que.isEmpty());
    }

}
