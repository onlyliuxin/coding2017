import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;

public class QueueTest {
    @Rule
    public final SystemOutRule log = new SystemOutRule().enableLog();

    @Test
    public void testEnqueue() {
        log.clearLog();
        Queue queue = new Queue();
        queue.enQueue(10);
        System.out.print(queue);
        Assert.assertEquals("Queue: [ 10 ]", log.getLog());
    }

    @Test
    public void testDequeue() {
        log.clearLog();
        Queue queue = new Queue();
        queue.enQueue(10);
        queue.deQueue();
        System.out.print(queue);
        Assert.assertEquals("Queue: [ ]", log.getLog());
    }

    @Test
    public void testIsEmpty() {
        Queue queue = new Queue();
        queue.enQueue(10);
        Assert.assertEquals(false, queue.isEmpty());
        queue.deQueue();
        Assert.assertEquals(true, queue.isEmpty());
    }

    @Test
    public void testSize() {
        Queue queue = new Queue();
        queue.enQueue(10);
        Assert.assertEquals(1, queue.size());
    }
}
