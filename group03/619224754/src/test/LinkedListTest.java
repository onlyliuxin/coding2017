package test;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import com.coding.basic.Iterator;
import com.coding.basic.LinkedList;

public class LinkedListTest {

	private LinkedList list = new LinkedList();
	
	@Test
	public void testAddObject() {
		list.add(1);
		list.add(2);
		Assert.assertEquals("Test", 1, list.get(0));
		Assert.assertEquals("Test", 2, list.get(1));
	}

	@Test
	public void testAddIntObject() {
		list.add(1);
		list.add(2);
		list.add(1, 2);
		list.add(1, 4);
		Assert.assertEquals("Test", 4, list.get(1));
		Assert.assertEquals("Test", 2, list.get(2));
	}

	@Test
	public void testGet() {
		list.add(1);
		list.add(2);
		Assert.assertEquals("Test", 1, list.get(0));
		Assert.assertEquals("Test", 2, list.get(1));
	}

	@Test
	public void testRemove() {
		list.add(1);
		list.add(2);
		list.remove(0);
		Assert.assertEquals("Test", 2, list.get(0));
	}

	@Test
	public void testSize() {
		list.add(1);
		list.add(2);
		list.remove(0);
		Assert.assertEquals("Test", 1, list.size());
	}

	@Test
	public void testAddFirst() {
		list.add(1);
		list.add(2);
		list.addFirst(3);
		Assert.assertEquals("Test", 3, list.get(0));
	}

	@Test
	public void testAddLast() {
		list.add(1);
		list.add(2);
		list.addLast(3);
		Assert.assertEquals("Test", 3, list.get(2));
	}

	@Test
	public void testRemoveFirst() {
		list.add(1);
		list.add(2);
		list.removeFirst();
		Assert.assertEquals("Test", 2, list.get(0));
	}

	@Test
	public void testRemoveLast() {
		list.add(1);
		list.add(2);
		list.removeLast();
		Assert.assertEquals("Test", 1, list.size());
	}

	@Test
	public void testIterator() {
		int i = 0;
		for (i = 0; i < 3; i++) {
			list.add(i);
		}
		
		Iterator iterator = list.iterator();
		i = 0;
		while(iterator.hasNext()) {
			Assert.assertEquals("Shoule be the same", i, iterator.next());
		}
	}

}
