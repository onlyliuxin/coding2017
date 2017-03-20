package com.coderising.dataStructure;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by LvZhenxing on 2017/2/22.
 */
public class LinkedListTest {

	LinkedList list = null;

	@Before
	public void setUp() throws Exception {
		list = new LinkedList();
<<<<<<< HEAD
		for (int i = 16; i > 10; i--) {
=======
		for (int i = 1; i < 6; i++) {
>>>>>>> 3b68226a35b37a691f64bcd4a1e219efba00a479
			list.add(i);
		}
		System.out.println("=============================before==============================");
		for (int i = 0; i < list.size(); i++) {
			try {
				System.out.println("index=" + i + ",data=" + list.get(i) + ",next=" + list.get(i + 1));
			} catch (Exception e) {
				System.out.println("index=" + i + ",data=" + list.get(i) + ",next=null");
			} finally {

			}
		}
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("=============================after==============================");
		for (int i = 0; i < list.size(); i++) {
			try {
				System.out.println("index=" + i + ",data=" + list.get(i) + ",next=" + list.get(i + 1));
			} catch (Exception e) {
				System.out.println("index=" + i + ",data=" + list.get(i) + ",next=null");
			} finally {

			}

		}
	}

	@Test
	public void testAdd() throws Exception {
		list.add(300);
	}

	@Test
	public void testAdd1() throws Exception {
		list.add(2, 100);
	}

	@Test
	public void testGet() throws Exception {

	}

	@Test
	public void testRemove() throws Exception {
		list.remove(3);
	}

	@Test
	public void testSize() throws Exception {

	}

	@Test
	public void testAddFirst() throws Exception {
		list.addFirst(66);
	}

	@Test
	public void testAddLast() throws Exception {
		list.addLast(77);
	}

	@Test
	public void testRemoveFirst() throws Exception {
		list.removeFirst();
	}

	@Test
	public void testRemoveLast() throws Exception {
		list.removeLast();
	}

	@Test
	public void testIterator() throws Exception {
		Iterator iterator = list.iterator();
		while (iterator.hasNext()) {
			System.out.print(iterator.next() + ",");
		}

		System.out.println();
		System.out.println("---------------------------");
	}

	@Test
	public void testRemoveFirstHalf() throws Exception {
		list.removeFirstHalf();
	}

	@Test
	public void testRemove1() throws Exception {
		list.remove(1,2);
	}

	@Test
	public void testRemoveDuplicateValues() throws Exception{
		list.add(6);
		list.add(6);
		list.add(6);
		list.add(7);
		list.add(7);
		list.add(8);
		for (int i = 0; i < list.size(); i++) {
			try {
				System.out.println("index=" + i + ",data=" + list.get(i) + ",next=" + list.get(i + 1));
			} catch (Exception e) {
				System.out.println("index=" + i + ",data=" + list.get(i) + ",next=null");
			} finally {

			}
		}
		list.removeDuplicateValues();

	}

	@Test
	public void testRemoveRange() throws Exception{
<<<<<<< HEAD
		list.add(11);
		list.add(10);
		list.add(9);
=======
		list.add(5);
		list.add(6);
		list.add(6);
>>>>>>> 3b68226a35b37a691f64bcd4a1e219efba00a479
		System.out.println("=============================ing==============================");
		for (int i = 0; i < list.size(); i++) {
			try {
				System.out.println("index=" + i + ",data=" + list.get(i) + ",next=" + list.get(i + 1));
			} catch (Exception e) {
				System.out.println("index=" + i + ",data=" + list.get(i) + ",next=null");
			} finally {

			}
		}
		list.removeRange(10,12);

	}

	@Test
	public void testGetElements() throws Exception{
		LinkedList list2 = new LinkedList();
<<<<<<< HEAD
		list2.add(2);
		list2.add(3);
=======
		list2.add(5);
		list2.add(6);
>>>>>>> 3b68226a35b37a691f64bcd4a1e219efba00a479
		System.out.println("=============================ing==============================");
		int[] result=list.getElements(list2);
		System.out.println(Arrays.toString(result));

	}

	@Test
	public void testReverse() throws Exception{
		list.reverse();
	}

<<<<<<< HEAD
=======
	@Test
	public void testSubtract() throws Exception{
		list.add(5);
		LinkedList list2 = new LinkedList();
		list2.add(1);
		list2.add(5);
		list.subtract(list2);
	}

	@Test
	public void testIntersection() throws Exception{
		LinkedList list2 = new LinkedList();
		list2.add(2);
		list2.add(4);
		list2.add(7);

		System.out.println("=============================ing==============================");
		LinkedList result=list.intersection(list2);
		for (int i = 0; i < result.size(); i++) {
			try {
				System.out.println("index=" + i + ",data=" + result.get(i) + ",next=" + result.get(i + 1));
			} catch (Exception e) {
				System.out.println("index=" + i + ",data=" + result.get(i) + ",next=null");
			} finally {

			}
		}
	}
>>>>>>> 3b68226a35b37a691f64bcd4a1e219efba00a479

}