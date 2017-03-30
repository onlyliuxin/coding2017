package test.collection;

import static util.Print.*;
import static util.TestUtil.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import collection.concrete.LinkedList;
import junit.framework.TestCase;

public class LinkedListTest2 extends TestCase {

	LinkedList<Integer> myLL;

	@Override
	@Before
	public void setUp() throws Exception {
		myLL = new LinkedList<Integer>();
		assertEquals(0, myLL.size());
	}

	@Override
	@After
	public void tearDown() throws Exception {
		myLL = null;
	}

	@Test
	public void testReverse() {
		addIntWithNatureOrder(myLL, 5);
		myLL.reverse();
		for (int i = 0; i < 5; i++) {
			int acutal = myLL.get(i);
			assertEquals(4 - i, acutal);
		}
	}

	@Test
	public void testRemoveFirstHalf() {
		myLL = new LinkedList<Integer>();
		addIntWithNatureOrder(myLL, 5);
		myLL.removeFirstHalf();
		assertEquals(3, myLL.size());
		assertEquals(2, (int) myLL.get(0));
		assertEquals(3, (int) myLL.get(1));
		assertEquals(4, (int) myLL.get(2));

		myLL = new LinkedList<Integer>();
		myLL.removeFirstHalf();
		assertEquals(0, myLL.size());

	}

	@Test
	public void testRemove2() {
		addIntWithNatureOrder(myLL, 5);
		myLL.remove(1, 2);
		assertEquals(3, myLL.size());
		assertEquals(0, (int) myLL.get(0));
		assertEquals(3, (int) myLL.get(1));
		assertEquals(4, (int) myLL.get(2));

		myLL = new LinkedList<Integer>();
		try {
			myLL.remove(1, 2);
		} catch (IndexOutOfBoundsException e) {

		}

		assertEquals(0, myLL.size());

	}

	@Test
	public void testGetElements() {
		addIntWithNatureOrder(myLL, 10);
		LinkedList<Integer> list = new LinkedList<Integer>();
		list.add(0);
		list.add(2);
		list.add(7);
		int[] result = myLL.getElements(list);
		for (int i = 0; i < result.length; i++) {
			int expected = list.get(i);
			int actual = result[i];
			assertEquals(expected, actual);
		}

		myLL = new LinkedList<Integer>();
		result = myLL.getElements(list);
		assertEquals(0, myLL.size());

	}

	@Test
	public void testSubstract() {
		LinkedList<Integer> myLL = new LinkedList<Integer>();
		addIntWithNatureOrder(myLL, 10);
		myLL.add(10);
		myLL.add(10);
		myLL.add(12);
		LinkedList<Integer> list = new LinkedList<Integer>();
		list.add(0);
		list.add(0);
		addIntWithNatureOrder(list, 10);
		list.add(10);
		list.add(12);
		list.add(22);
		myLL.subtract(list);
		assertEquals(0, myLL.size());

		myLL = new LinkedList<Integer>();
		list = new LinkedList<Integer>();
		myLL.subtract(list);
		assertEquals(0, myLL.size());

	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void testSubstract2() {
		LinkedList<Integer> myLL = new LinkedList<Integer>();
		addIntWithNatureOrder(myLL, 10);
		myLL.add(10);
		myLL.add(10);
		myLL.add(12);
		LinkedList<Integer> list = new LinkedList<Integer>();
		list.add(0);
		list.add(0);
		addIntWithNatureOrder(list, 10);
		list.add(10);
		list.add(12);
		list.add(22);

		myLL.subtract2(list);
		assertEquals(0, myLL.size());

		myLL = new LinkedList<Integer>();
		LinkedList list1 = new LinkedList();
		myLL.subtract2(list);
		assertEquals(0, myLL.size());
		
		addIntWithNatureOrder(myLL, 10);
		myLL.add(-3);
		println(myLL);
		list1.add(10);
		list1.add(3);
		list1.add("dd");
		list1.add(null);
		list1.add(-3);
		list1.add(9);
		list1.add(0);
		myLL.subtract2(list1);

		assertEquals("[1, 2, 4, 5, 6, 7, 8]",myLL.toString());

	}
	
	

	@Test
	public void testIntersection() {

		LinkedList<Integer> list = new LinkedList<Integer>();
		LinkedList<Integer> result = myLL.intersection(list);
		assertEquals(0, result.size());

		addIntWithNatureOrder(myLL, 10);
		myLL.add(10);
		myLL.add(12);
		myLL.add(13);
		myLL.add(24);

		list.add(0);
		list.add(5);
		list.add(10);
		result = myLL.intersection(list);
		assertEquals(0, (int) result.get(0));
		assertEquals(5, (int) result.get(1));
		assertEquals(10, (int) result.get(2));

		myLL = new LinkedList<Integer>();
		result = new LinkedList<Integer>();
		myLL.intersection(list);
		assertEquals(0, result.size());

	}

	@Test
	public void testRemoveDuplicateValues() {

		myLL.add(0);
		myLL.add(0);
		myLL.add(1);
		myLL.add(1);
		myLL.add(10);
		myLL.removeDuplicateValues();
		assertEquals(3, myLL.size());
		assertEquals(0, (int) myLL.get(0));
		assertEquals(1, (int) myLL.get(1));
		assertEquals(10, (int) myLL.get(2));

		myLL = new LinkedList<Integer>();
		myLL.removeDuplicateValues();
		assertEquals(0, myLL.size());

	}

	@Test
	public void testRemoveRange() {

		myLL.add(0);
		addIntWithNatureOrder(myLL, 10);
		myLL.add(9);
		myLL.add(10);
		myLL.add(12);
		myLL.add(13);
		myLL.add(24);

		myLL.removeRange(-5, 3);
		assertEquals(3, (int) myLL.get(0));

		myLL.removeRange(3, 4);
		assertEquals(3, (int) myLL.get(0));

		myLL.removeRange(3, 5);
		assertEquals(3, (int) myLL.get(0));
		assertEquals(5, (int) myLL.get(1));

		myLL.removeRange(-3, 11);
		assertEquals(12, (int) myLL.get(0));

		myLL = new LinkedList<Integer>();
		myLL.removeRange(-1, 10);
		assertEquals(0, myLL.size());

	}
	
	
	

}
