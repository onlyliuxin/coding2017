package datastructure.queue;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;



public class JosephusTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testExecute() {
		
	    Assert.assertEquals("[0, 1, 2, 3, 4, 5, 6]", Josephus.execute(7, 1).toString());
		Assert.assertEquals("[1, 3, 5, 0, 4, 2, 6]", Josephus.execute(7, 2).toString());
		Assert.assertEquals("[2, 5, 1, 6, 4, 0, 3]", Josephus.execute(7, 3).toString());
		Assert.assertEquals("[3, 0, 5, 4, 6, 2, 1]", Josephus.execute(7, 4).toString());
		Assert.assertEquals("[6, 0, 2, 5, 1, 3, 4]", Josephus.execute(7, 7).toString());
		
	}

}
