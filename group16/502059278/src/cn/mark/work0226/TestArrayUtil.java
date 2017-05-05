package cn.mark.work0226;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestArrayUtil {
	ArrayUtil arrayUtil = null;
	
	@Before
	public void setUp() throws Exception {
		arrayUtil = new ArrayUtil();
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void reverseArray() {
		int[] origin = new int[]{1,2,8,31};
		arrayUtil.reverseArray(origin);
	}
	
	@Test
	public void removeZero() {
		int[] origin = new int[]{0,1,2,0,3,0,4,7,0};
		Assert.assertArrayEquals(new int[]{1, 2, 3, 4, 7}, arrayUtil.removeZero(origin));
	}

}
