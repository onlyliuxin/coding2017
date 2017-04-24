package structure. week5;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LRUPageFrameTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAdd() {
        LRUPageFrame lru = new LRUPageFrame(5);
        lru.add(3);
        lru.add(7);
        lru.add(5);
        lru.add(8);
        lru.add(10);
        Assert.assertArrayEquals(new int[]{10,8,5,7,3}, lru.getAll());
        lru.add(5);
        lru.add(3);
        Assert.assertArrayEquals(new int[]{3,5,10,8,7}, lru.getAll());
        lru.add(8);
        lru.add(11);
        Assert.assertArrayEquals(new int[]{11,8,3,5,10}, lru.getAll());
	}
}
