/**
 * 
 */
package com.coding.basic2;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Administrator
 *
 */
public class LinkedListTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	private LinkedList ll1;
	private LinkedList ll2;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		ll1 = new LinkedList();
		ll2 = new LinkedList();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		ll1 = null;
		ll2 = null;
	}

	/**
	 * Test method for {@link com.coding.basic2.LinkedList#add(java.lang.Object)}.
	 */
	@Test
	public void testAddObject() {
		assertEquals(0, ll1.size());
		ll1.add(1);
		assertEquals(1,ll1.size());
		assertEquals(1,(int)ll1.get(0));
	}

	/**
	 * Test method for {@link com.coding.basic2.LinkedList#add(int, java.lang.Object)}.
	 */
	@Test
	public void testAddIntObject() {
		assertEquals(0, ll1.size());
		ll1.add(1);
		ll1.add(2);
		ll1.add(1,3);
		assertEquals(3,ll1.size());
		assertEquals(1,(int)ll1.get(0));
		assertEquals(3,(int)ll1.get(1));
		assertEquals(2,(int)ll1.get(2));
	}

	/**
	 * Test method for {@link com.coding.basic2.LinkedList#get(int)}.
	 */
	@Test
	public void testGet() {
		assertEquals(0, ll1.size());
		ll1.add(1);
		ll1.add(2);
		
		assertEquals(1,(int)ll1.get(0));
		assertEquals(2,(int)ll1.get(1));
		assertEquals(2,ll1.size());
	}

	/**
	 * Test method for {@link com.coding.basic2.LinkedList#remove(int)}.
	 */
	@Test
	public void testRemoveInt() {
		assertEquals(0, ll1.size());
		ll1.add(1);
		ll1.add(2);
		assertEquals(2,ll1.size());
		assertEquals(1,(int)ll1.remove(0));
		assertEquals(2,(int)ll1.remove(0));
		assertEquals(0,ll1.size());
	}

	/**
	 * Test method for {@link com.coding.basic2.LinkedList#size()}.
	 */
	@Test
	public void testSize() {
		assertEquals(0, ll1.size());
		ll1.add(1);
		ll1.add(2);
		ll1.add(2);
		ll1.add(2);
		assertEquals(4,ll1.size());
		ll1.add(1,2);
		ll1.add(0,2);
		ll1.add(4,2);
		assertEquals(7,ll1.size());
	}

	/**
	 * Test method for {@link com.coding.basic2.LinkedList#addFirst(java.lang.Object)}.
	 */
	@Test
	public void testAddFirst() {
		assertEquals(0, ll1.size());
		ll1.add(1);
		ll1.add(2);
		ll1.addFirst(0);
		assertEquals(3,ll1.size());
		assertEquals(0,ll1.get(0));
	}

	/**
	 * Test method for {@link com.coding.basic2.LinkedList#addLast(java.lang.Object)}.
	 */
	@Test
	public void testAddLast() {
		assertEquals(0, ll1.size());
		ll1.add(1);
		ll1.add(2);
		ll1.addLast(3);
		assertEquals(3,ll1.size());
		assertEquals(3,ll1.get(2));
	}

	/**
	 * Test method for {@link com.coding.basic2.LinkedList#removeFirst()}.
	 */
	@Test
	public void testRemoveFirst() {
		assertEquals(0, ll1.size());
		ll1.add(1);
		ll1.add(2);
		ll1.add(3);
		assertEquals(3,ll1.size());
		assertEquals(1,ll1.removeFirst());
		assertEquals(2,ll1.size());
	}

	/**
	 * Test method for {@link com.coding.basic2.LinkedList#removeLast()}.
	 */
	@Test
	public void testRemoveLast() {
		assertEquals(0, ll1.size());
		ll1.add(1);
		ll1.add(2);
		ll1.add(3);
		assertEquals(3,ll1.size());
		assertEquals(3,ll1.removeLast());
		assertEquals(2,ll1.size());
	}

	/**
	 * Test method for {@link com.coding.basic2.LinkedList#iterator()}.
	 */
	@Test
	public void testIterator() {
		ll1.add(0);
		ll1.add(1);
		ll1.add(3);
		Iterator it = ll1.iterator();
		assertTrue(it.hasNext());
		assertEquals(0,(int)it.next());
		assertTrue(it.hasNext());
		assertEquals(1,(int)it.next());
		assertTrue(it.hasNext());
		assertEquals(3,(int)it.next());
		assertFalse(it.hasNext());
	}

	/**
	 * Test method for {@link com.coding.basic2.LinkedList#reverse()}.
	 */
	@Test
	public void testReverse() {
		assertEquals(0, ll1.size());
		ll1.add(1);
		ll1.add(2);
		ll1.add(3);
		assertEquals(3, ll1.size());
		ll1.reverse();
		assertEquals(3, ll1.size());
		assertEquals(3,ll1.get(0));
		assertEquals(2,ll1.get(1));
		assertEquals(1,ll1.get(2));
	}

	/**
	 * Test method for {@link com.coding.basic2.LinkedList#removeFirstHalf()}.
	 */
	@Test
	public void testRemoveFirstHalf() {
		assertEquals(0, ll1.size());
		ll1.add(1);
		ll1.add(2);
		ll1.add(3);
		assertEquals(3, ll1.size());
		ll1.removeFirstHalf();
		assertEquals(2, ll1.size());
		assertEquals(2,ll1.get(0));
		assertEquals(3,ll1.get(1));

	}

	/**
	 * Test method for {@link com.coding.basic2.LinkedList#remove(int, int)}.
	 */
	@Test
	public void testRemoveIntInt() {
		assertEquals(0, ll1.size());
		ll1.add(1);
		ll1.add(2);
		ll1.add(3);
		assertEquals(3, ll1.size());
		ll1.remove(0, 2);
		assertEquals(1, ll1.size());
		assertEquals(3,ll1.get(0));
	}

	/**
	 * Test method for {@link com.coding.basic2.LinkedList#getElements(com.coding.basic2.LinkedList)}.
	 */
	@Test
	public void testGetElements() {
		assertEquals(0, ll1.size());
		ll1.add(1);
		ll1.add(2);
		ll1.add(3);
		ll1.add(4);
		assertEquals(4, ll1.size());
		assertEquals(0, ll2.size());
		ll2.add(0);
		ll2.add(2);
		ll2.add(3);
		assertEquals(3, ll2.size());
		int[] array = new int[]{1,3,4};
		assertArrayEquals(array,ll1.getElements(ll2));
	}

	/**
	 * Test method for {@link com.coding.basic2.LinkedList#subtract(com.coding.basic2.LinkedList)}.
	 */
	@Test
	public void testSubtract() {
		assertEquals(0, ll1.size());
		ll1.add(1);
		ll1.add(2);
		ll1.add(3);
		ll1.add(4);
		assertEquals(4, ll1.size());
		assertEquals(0, ll2.size());
		ll2.add(0);
		ll2.add(2);
		ll2.add(3);
		assertEquals(3, ll2.size());
		ll1.subtract(ll2);
		//assertEquals(2, ll1.size());
		System.out.println(ll1);
		assertEquals(1,ll1.get(0));
		assertEquals(4,ll1.get(1));
	}

	/**
	 * Test method for {@link com.coding.basic2.LinkedList#removeDuplicateValues()}.
	 */
	@Test
	public void testRemoveDuplicateValues() {
		assertEquals(0, ll1.size());
		ll1.add(1);
		ll1.add(1);
		ll1.add(2);
		ll1.add(2);
		ll1.add(2);
		ll1.add(3);
		ll1.add(4);
		ll1.add(4);
		ll1.add(4);
		ll1.add(4);
		assertEquals(10, ll1.size());
		ll1.removeDuplicateValues();
		assertEquals(4, ll1.size());
		assertEquals(1,(int)ll1.get(0));
		assertEquals(2,(int)ll1.get(1));
		assertEquals(3,(int)ll1.get(2));
		assertEquals(4,(int)ll1.get(3));
	}

	/**
	 * Test method for {@link com.coding.basic2.LinkedList#removeRange(int, int)}.
	 */
	@Test
	public void testRemoveRange() {
		assertEquals(0, ll1.size());
		ll1.add(0);
		ll1.add(1);
		ll1.add(2);
		ll1.add(3);
		ll1.add(4);
		ll1.add(5);
		ll1.add(6);
		ll1.add(7);
		ll1.add(8);
		ll1.add(9);
		assertEquals(10, ll1.size());
		ll1.removeRange(1, 8);
		assertEquals(2, ll1.size());
		assertEquals(0,(int)ll1.get(0));
		assertEquals(9,(int)ll1.get(1));
	}

	/**
	 * Test method for {@link com.coding.basic2.LinkedList#intersection(com.coding.basic2.LinkedList)}.
	 */
	@Test
	public void testIntersection() {
		assertEquals(0, ll1.size());
		ll1.add(0);
		ll1.add(1);
		ll1.add(2);
		ll1.add(3);
		ll1.add(4);
		ll1.add(5);
		ll1.add(6);
		ll1.add(7);
		ll1.add(8);
		ll1.add(9);
		assertEquals(10, ll1.size());
		assertEquals(0, ll2.size());
		ll2.add(-1);
		ll2.add(1);
		ll2.add(3);
		ll2.add(5);
		ll2.add(10);
		assertEquals(5, ll2.size());
		LinkedList ll3 = ll1.intersection(ll2);
		assertEquals(3, ll3.size());
		assertEquals(1,(int)ll3.get(0));
		assertEquals(3,(int)ll3.get(1));
		assertEquals(5,(int)ll3.get(2));
	}

}
