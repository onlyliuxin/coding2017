package code01;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by yaoyuan on 2017/3/8.
 */
public class QueueTest {

    @Test
    public void testQueue() throws Exception {
        Queue queue = new Queue();
        String[] array = new String[]{"a","b","c"};
        for (String str : array){
            queue.enQueue(str);
        }
        int i = 0;
        while (!queue.isEmpty()){
            Assert.assertEquals(array[i],queue.deQueue());
            i ++;
        }
    }
}