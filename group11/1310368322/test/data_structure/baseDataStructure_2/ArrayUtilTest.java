package day_2017_2_26_SecondHomework;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ArrayUtilTest {
	ArrayUtil arrayUtil;

	@Before
	public void setUp() throws Exception {
		arrayUtil = new ArrayUtil();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testReverseArray_a() {
		int [] actuals =  {};
		int [] expected = {};
		arrayUtil.reverseArray(actuals);
		Assert.assertArrayEquals(expected, actuals);
	}
	@Test
	public void testReverseArray_b() {
		int [] actuals =  null;
		int [] expected = null;
		arrayUtil.reverseArray(actuals);
		Assert.assertArrayEquals(expected, actuals);
	}
	@Test
	public void testReverseArray_c() {
		int [] actuals =  {1,2,3,4};
		int [] expected = {4,3,2,1};
		arrayUtil.reverseArray(actuals);
		Assert.assertArrayEquals(expected, actuals);
	}
	
	
	@Test
	public void testRemoveZero_1(){
		int [] actuals = null;
		int [] expected = null;
		int [] actual = arrayUtil.removeZero(actuals);
		Assert.assertArrayEquals(expected, actual);
	}
	@Test
	public void testRemoveZero_2(){
		int [] actuals = {};
		int [] expected = {};
		int [] actual = arrayUtil.removeZero(actuals);
		Assert.assertArrayEquals(expected, actual);
	}
	@Test
	public void testRemoveZero_3(){
		int [] actuals = {0,0,0,0,0,0};
		int [] expected = {};
		int [] actual = arrayUtil.removeZero(actuals);
		Assert.assertArrayEquals(expected, actual);
	}
	@Test
	public void testRemoveZero_4(){
		int [] actuals = {1,2,3,4,5,6};
		int [] expected = {1,2,3,4,5,6};
		int [] actual = arrayUtil.removeZero(actuals);
		Assert.assertArrayEquals(expected, actual);
	}
	@Test
	public void testRemoveZero_5(){
		int [] actuals = {1,2,0,0,5,6};
		int [] expected = {1,2,5,6};
		int [] actual = arrayUtil.removeZero(actuals);
		Assert.assertArrayEquals(expected, actual);
	}
	@Test
	public void testRemoveZero_6(){
		int [] actuals = {0,0,4,2};
		int [] expected = {4,2};
		int [] actual = arrayUtil.removeZero(actuals);
		Assert.assertArrayEquals(expected, actual);
	}@Test
	public void testRemoveZero_7(){
		int [] actuals = {4,2,0,0,0};
		int [] expected = {4,2};
		int [] actual = arrayUtil.removeZero(actuals);
		Assert.assertArrayEquals(expected, actual);
	}
	public void testRemoveZero_8(){
		int [] actuals = {0,0,4,0,0,2,0,0,0};
		int [] expected = {4,2};
		int [] actual = arrayUtil.removeZero(actuals);
		Assert.assertArrayEquals(expected, actual);
	}


}
