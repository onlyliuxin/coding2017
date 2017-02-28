package week01.BasicDataStructureTest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import week01.BasicDataStructure.Iterator;
import week01.BasicDataStructure.LinkedList;

public class LinkedListTest {
	
	private LinkedList linkedList = new LinkedList();

	@Before
	public void setUp() throws Exception {
		for(int i=0;i<100;i++){
			linkedList.add(i);
		}
	}

	@Test
	public void testAddObject() {
		for(int i=0;i<200;i++){
			linkedList.add(i);
		}
		for(int i=0;i<100;i++){
			Assert.assertEquals(linkedList.get(i), i);
		}
		for(int i=100;i<300;i++){
			Assert.assertEquals(linkedList.get(i), i-100);
		}
	}

	@Test
	public void testAddIntObject() {
		linkedList.add(0, 10);
		Assert.assertEquals(linkedList.get(0), 10);
		linkedList.add(5,60);
		Assert.assertEquals(linkedList.get(5), 60);
		Assert.assertEquals(linkedList.get(101), 99);
	}

	@Test
	public void testGet() {
		for(int i =0;i<100;i++){
		Assert.assertEquals(linkedList.get(i), i);
		}
	}

	@Test
	public void testRemove() {
		Assert.assertEquals(linkedList.remove(0), 0);
		Assert.assertEquals(linkedList.remove(0), 1);
		Assert.assertEquals(linkedList.size(), 98);
		linkedList.remove(97);
		Assert.assertEquals(linkedList.get(96), 98);
	}

	@Test
	public void testSize() {
		linkedList.add(0);
		Assert.assertEquals(linkedList.size(), 101);
		linkedList.add(0, 10);
		Assert.assertEquals(linkedList.size(), 102);
		linkedList.remove(0);
		Assert.assertEquals(linkedList.size(), 101);
	}

	@Test
	public void testAddFirst() {
		linkedList.addFirst(22);
		Assert.assertEquals(linkedList.get(0), 22);
		linkedList.addFirst(44);
		Assert.assertEquals(linkedList.get(0), 44);
		Assert.assertEquals(linkedList.size(), 102);
	}

	@Test
	public void testAddLast() {
		linkedList.addLast(22);
		Assert.assertEquals(linkedList.get(100), 22);
		linkedList.addLast(44);
		Assert.assertEquals(linkedList.get(101), 44);
	}

	@Test
	public void testRemoveFirst() {
		Assert.assertEquals(linkedList.removeFirst(), 0);
		Assert.assertEquals(linkedList.removeFirst(), 1);
	}

	@Test
	public void testRemoveLast() {
		Assert.assertEquals(linkedList.removeLast(),99 );
		Assert.assertEquals(linkedList.removeLast(), 98);
	}

	@Test
	public void testIterator() {
		Iterator iterator = linkedList.iterator();
		for(int i = 0;iterator.hasNext();i++){
			Assert.assertEquals(iterator.next(), i);
		}
	}

}
