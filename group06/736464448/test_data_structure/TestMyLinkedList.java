package test_data_structure;



import java.util.Iterator;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import data_structure.MyLinkedList;

public class TestMyLinkedList {
	MyLinkedList link=null;

	@Before
	public void setUp() throws Exception {
		link=new MyLinkedList();
		System.out.println("测试开始");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("测试结束");
	}

	@Test
	public void testAddObject() {
		link.add(1);
		Assert.assertEquals(1,link.get(0));
	}

	@Test
	public void testGet() {
		link.add(1);
		Assert.assertEquals(1,link.get(0));
	}

	@Test
	public void testRemove() {
		link.add(1);
		Assert.assertEquals(1,link.remove(0));
	}

	@Test
	public void testSize() {
		link.add(1);
		Assert.assertEquals(1,link.size());
	}

	@Test
	public void testAddFirst() {
		link.add(1);
		link.add(1);
		link.add(1);
		link.addFirst(2);
		Assert.assertEquals(2,link.get(0));
	}

	@Test
	public void testAddLast() {
		link.add(1);
		link.add(1);
		link.add(1);
		link.addLast(2);
		Assert.assertEquals(2,link.get(link.size()-1));
	}

	@Test
	public void testRemoveFirst() {
		link.add(1);
		link.add(1);
		link.add(1);
		link.addFirst(2);
		Assert.assertEquals(2,link.removeFirst());
	}

	@Test
	public void testRemoveLast() {
		link.add(1);
		link.add(1);
		link.add(1);
		link.addLast(2);
		Assert.assertEquals(2,link.removeLast());
	}

	@Test
	public void testIterator() {
		link.add(1);
		link.add(2);
		link.add(3);
		Iterator<Object> itr=link.iterator();
		while(itr.hasNext()){
			System.out.println(itr.next());
		}
		
	}

	@Test
	public void testAddIntObject() {
		link.add(1);
		link.add(1);
		link.add(1);
		link.add(2, 3);
		Assert.assertEquals(3,link.get(2));
	}

	

}
