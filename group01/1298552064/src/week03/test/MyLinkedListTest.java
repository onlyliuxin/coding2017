package week03.test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import week03.basic.MyLinkedList;

public class MyLinkedListTest {

	MyLinkedList list = null;
	MyLinkedList list1 = null;

	@Before
	public void setUp() {
		list = new MyLinkedList();
		list1 = new MyLinkedList();
	}

	@After
	public void tearDown() {
		list = null;
		list1 = null;
	}

	@Test
	public void testReverse() {
		list.add(3);
		list.add(7);
		list.add(10);
		
		list.reverse();
		
		int[] rs = new int[]{10,7,3};
		for (int i = 0; i < rs.length; i++) {
			Assert.assertEquals(rs[i], list.get(i));
		}
	}

	@Test
	public void testRemoveFirstHalf() {
		
		list.add(2);
		list.add(5);
		list.add(7);
		list.add(8);
		list.removeFirstHalf();
		int []rs = new int[]{7,8};
		for (int i = 0; i < rs.length; i++) {
			Assert.assertEquals(rs[i], list.get(i));
		}
		
		list1.add(2);
		list1.add(5);
		list1.add(7);
		list1.add(8);
		list1.add(10);
		list1.removeFirstHalf();
		rs = new int[]{7,8,10};
		for (int i = 0; i < rs.length; i++) {
			Assert.assertEquals(rs[i], list1.get(i));
		}
		
	}

	@Test
	public void testRemove() {
		list.add(2);
		list.add(5);
		list.add(7);
		list.add(8);
		
		list.remove(1, 2);
		int []rs = new int[]{2,8};
		for (int i = 0; i < rs.length; i++) {
			Assert.assertEquals(rs[i], list.get(i));
		}
		
	}

	@Test
	public void testGetElements() {
		list.add(11);
		list.add(101);
		list.add(201);
		list.add(301);
		list.add(401);
		list.add(501);
		list.add(601);
		list.add(701);

		list1.add(1);
		list1.add(3);
		list1.add(4);
		list1.add(6);
		
		int[] actualRs = new int[]{101,301,401,601};
		int[] rs = list.getElements(list1);
		Assert.assertArrayEquals(actualRs, rs);
	}

	@Test
	public void testSubtract() {

		list.add(2);
		list.add(3);
		list.add(5);
		list.add(7);
		list.add(9);

		list1.add(3);
		list1.add(5);

		list.subtract(list1);

		int[] rs = new int[] { 2, 7, 9 };
		for (int i = 0; i < rs.length; i++) {
			Assert.assertEquals(rs[i], list.get(i));
		}
	}

	@Test
	public void testRemoveDuplicateValues() {
		list.add(2);
		list.add(3);
		list.add(5);
		list.add(5);
		list.add(6);
		list.add(6);
		list.add(8);

		list.removeDuplicateValues();

		int[] rs = new int[] { 2, 3, 5, 6, 8 };
		for (int i = 0; i < rs.length; i++) {
			Assert.assertEquals(rs[i], list.get(i));
		}
	}

	@Test
	public void testRemoveRange() {
		list.add(2);
		list.add(3);
		list.add(5);
		list.add(7);
		list.add(9);

		list.removeRange(3, 8);

		int[] rs = new int[] { 2, 3, 9 };
		for (int i = 0; i < rs.length; i++) {
			Assert.assertEquals(rs[i], list.get(i));
		}
	}

	@Test
	public void testIntersection() {
		list.add(2);
		list.add(3);
		list.add(5);
		list.add(7);
		list.add(8);
		list.add(9);
		list.add(13);

		list1.add(3);
		list1.add(7);
		list1.add(9);
		list1.add(14);

		MyLinkedList rsList = list.intersection(list1);
		int[] rs = new int[] { 3, 7, 9 };
		for (int i = 0; i < rs.length; i++) {
			Assert.assertEquals(rs[i], rsList.get(i));
		}
	}

}
