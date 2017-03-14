package dataStructure;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by LvZhenxing on 2017/2/22.
 */
public class QueueTest {

	Queue list = null;

	@Before
	public void setUp() throws Exception {
		list = new Queue();
		for (int i = 0; i < 5; i++) {
			list.enQueue(i);
		}
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("----------------tearDown------------------");
		int count=list.size();
		for (int i = 0; i < count; i++) {
			System.out.println("list.deQueue():"+list.deQueue());
		}
	}

	@Test
	public void testEnQueue() throws Exception {
		list.enQueue(11);
	}

	@Test
	public void testDeQueue() throws Exception {
		System.out.println("list.deQueue():"+list.deQueue());
		System.out.println("list.deQueue():"+list.deQueue());
	}

	@Test
	public void testIsEmpty() throws Exception {
		System.out.println("testIsEmpty:"+list.isEmpty());
	}

	@Test
	public void testSize() throws Exception {
		System.out.println("testSize:"+list.size());
	}
}