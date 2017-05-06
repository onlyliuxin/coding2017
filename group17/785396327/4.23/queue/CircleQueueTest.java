package queue;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by gongxun on 2017/4/25.
 */
public class CircleQueueTest {
    @Before
    public void startUp() {

    }

    @After
    public void tearDown() {

    }

    @Test
    public void testEnQueue() {
        CircleQueue<Integer> circleQueue = new CircleQueue<Integer>();
        {
            for (int i = 0; i < 9; i++) {
                circleQueue.enQueue(i);
            }
        }

        {

            Assert.assertEquals("[0, 1, 2, 3, 4, 5, 6, 7, 8, null]", circleQueue.toString());
            Assert.assertEquals(9, circleQueue.size());

            circleQueue.enQueue(9);
            Assert.assertEquals("[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]", circleQueue.toString());
            Assert.assertEquals(10, circleQueue.size());

            circleQueue.enQueue(10);
            Assert.assertEquals("[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]", circleQueue.toString());
            Assert.assertEquals(10, circleQueue.size());
        }

        {
            int removeEle = circleQueue.deQueue();
            Assert.assertEquals(0, removeEle);
            circleQueue.deQueue();
            circleQueue.deQueue();
            circleQueue.deQueue();
            Assert.assertEquals("[null, null, null, null, 4, 5, 6, 7, 8, 9]", circleQueue.toString());
        }
        {
            circleQueue.enQueue(-1);
            Assert.assertEquals("[-1, null, null, null, 4, 5, 6, 7, 8, 9]", circleQueue.toString());
            Assert.assertEquals(7, circleQueue.size());
        }

    }


}
