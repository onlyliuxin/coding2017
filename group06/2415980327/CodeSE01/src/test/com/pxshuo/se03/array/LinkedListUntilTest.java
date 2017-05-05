package test.com.pxshuo.se03.array;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.pxshuo.se03.basic.LinkedList;

public class LinkedListUntilTest {
	private LinkedList obj;
	
	@Before
	public void init() {
		obj = new LinkedList();
	}
	
	@After
	public void clear() {
		obj = null;
	}
	
	@Test
	public void reverseTest() {
		obj.add("3");
		obj.add("7");
		obj.add("10");
		obj.reverse();
		Assert.assertEquals("10,7,3", obj.getResult());
	}
	
	@Test
	public void removeFirstHalfTest() {
		obj.add("2");
		obj.add("5");
		obj.add("7");
		obj.add("8");
		obj.add("10");
		obj.removeFirstHalf();
		Assert.assertEquals("7,8,10", obj.getResult());
		Assert.assertEquals(3, obj.size());
	}
	
	@Test
	public void removeLengthTest() {
		obj.add("2");
		obj.add("5");
		obj.add("7");
		obj.add("8");
		obj.add("10");
		obj.remove(1,3);
		Assert.assertEquals("2,10", obj.getResult());
		Assert.assertEquals(2, obj.size());
	}
	
	@Test
	public void getElementsTest() {
		obj.add("11");
		obj.add("101");
		obj.add("201");
		obj.add("301");
		obj.add("401");
		obj.add("501");
		obj.add("601");
		obj.add("701");
		LinkedList getList = new LinkedList();
		getList.add("1");
		getList.add("3");
		getList.add("4");
		getList.add("6");
		Assert.assertArrayEquals(new int[]{101,301,401,601}, obj.getElements(getList));
	}
	
	@Test
	public void subtractTest() {
		obj.add("11");
		obj.add("101");
		obj.add("201");
		obj.add("301");
		obj.add("401");
		obj.add("501");
		obj.add("601");
		obj.add("701");
		LinkedList getList = new LinkedList();
		getList.add("101");
		getList.add("301");
		getList.add("401");
		getList.add("601");
		obj.subtract(getList);
		Assert.assertEquals("11,201,501,701", obj.getResult());
	}
	
	@Test
	public void removeDuplicateValuesTest() {
		obj.add("11");
		obj.add("101");
		obj.add("101");
		obj.add("301");
		obj.add("401");
		obj.add("401");
		obj.add("601");
		obj.add("601");
		obj.removeDuplicateValues();
		Assert.assertEquals("11,101,301,401,601", obj.getResult());
	}
	
	@Test
	public void removeRangeTest() {
		obj.add("11");
		obj.add("101");
		obj.add("201");
		obj.add("301");
		obj.add("401");
		obj.add("501");
		obj.add("601");
		obj.add("701");
		obj.removeRange(200, 600);	
		Assert.assertEquals("11,101,601,701", obj.getResult());
		Assert.assertEquals(4, obj.size());
		
	}
	
	@Test
	public void intersectionTest() {
		obj.add("11");
		obj.add("101");
		obj.add("201");
		obj.add("301");
		obj.add("401");
		obj.add("501");
		obj.add("601");
		obj.add("701");
		
		LinkedList getList = new LinkedList();
		getList.add("10");
		getList.add("101");
		getList.add("301");
		getList.add("402");
		getList.add("601");
			
		Assert.assertEquals("101,301,601", obj.intersection(getList).getResult());
		
	}
}
