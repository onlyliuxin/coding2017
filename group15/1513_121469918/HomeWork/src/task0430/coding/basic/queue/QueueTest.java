package task0430.coding.basic.queue;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class QueueTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		QueueWithTwoStacks<Integer> qwts = new QueueWithTwoStacks<>();
		qwts.enQueue(1);
		qwts.enQueue(2);
		qwts.enQueue(3);
		qwts.deQueue();
		Assert.assertEquals("[2, 3]",qwts.toString());
		qwts.deQueue();
		Assert.assertEquals("[3]",qwts.toString());
		qwts.enQueue(1);
		Assert.assertEquals("[3, 1]",qwts.toString());
		qwts.deQueue();
		qwts.deQueue();
		Assert.assertEquals("[]",qwts.toString());
	}

}
