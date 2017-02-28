package week01.test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import week01.basic.MyArrayList;



public class MyArrayListTest {

	private MyArrayList list = null;
	
	@Before
	public void setUp() throws Exception {
		list = new MyArrayList();
		
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
	}

	@After
	public void tearDown() throws Exception {
		list = null;
	}

	@Test
	public void testAdd(){
		list.add(4, 10);
		Assert.assertEquals("MyArrayList: { size=6, elementData=[1,2,3,4,10,5] }", list.toString());
	}
	
	@Test
	public void testGet(){
		Assert.assertEquals((Object)new Integer(3), list.get(2));
	}
	
	@Test
	public void testRemove(){
		list.remove(2);
		Assert.assertEquals("MyArrayList: { size=4, elementData=[1,2,4,5] }", list.toString());
	}
	
	@Test
	public void testSize(){
		Assert.assertEquals((Object)new Integer(5), list.size());
	}
}
