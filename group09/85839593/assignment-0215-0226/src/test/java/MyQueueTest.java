import org.junit.*;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: guohairui
 * Date: 17-2-26
 * Time: 下午7:33
 * To change this template use File | Settings | File Templates.
 */
public class MyQueueTest {
    @org.junit.Test
    public void testEnQueue() throws Exception {
        MyQueue myQueue=new MyQueue();
        myQueue.enQueue("abc1");
        myQueue.enQueue("abc2");
        myQueue.enQueue("abc3");
        System.out.println(myQueue);
        myQueue.enQueue("abc4");
        System.out.println(myQueue);
        myQueue.deQueue();
        System.out.println(myQueue);
    }

    @Test
    public void testDeQueue() throws Exception {

    }
}
