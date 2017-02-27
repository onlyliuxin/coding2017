package week01.test;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import week01.basic.MyLinkedList;

public class MyLinkedListTest {

	private MyLinkedList list = null;
	
	@Before
	public void setUp() throws Exception {
		list = new MyLinkedList();
		
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
		list.add(3,10);
		Assert.assertEquals("MyLinkedList: { size=6, elementData=[1,2,3,10,4,5] }",list.toString());
	}
	
	@Test 
	public void testAddFirst(){
		list.addFirst(100);
		Assert.assertEquals("MyLinkedList: { size=6, elementData=[100,1,2,3,4,5] }",list.toString());
	}
	
	@Test
	public void testAddLast(){
		list.addLast(100);
		Assert.assertEquals("MyLinkedList: { size=6, elementData=[1,2,3,4,5,100] }",list.toString());
	}
	
	@Test
	public void testGet(){
		Assert.assertEquals((Object)new Integer(5), list.get(4));
	}
	
	@Test
	public void testRemove(){
		list.remove(3);
		Assert.assertEquals("MyLinkedList: { size=4, elementData=[1,2,3,5] }",list.toString());
	}
	
	@Test
	public void testRemoveFirst(){
		list.removeFirst();
		Assert.assertEquals("MyLinkedList: { size=4, elementData=[2,3,4,5] }",list.toString());
	}
	
	@Test
	public void testRemoveLast(){
		list.removeLast();
		Assert.assertEquals("MyLinkedList: { size=4, elementData=[1,2,3,4] }",list.toString());
	}
	
	@Test
	public void testSize(){
		Assert.assertEquals((Object)new Integer(5), list.size());
	}

}
