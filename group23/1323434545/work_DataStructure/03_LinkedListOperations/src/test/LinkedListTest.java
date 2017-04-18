package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import main.LinkedList;

public class LinkedListTest {
	private LinkedList list;
	@Before
	public void setUp() throws Exception {
		list = new LinkedList();
	}

	@Test
	public void testReverse() {
		list.add(3);
		list.add(2);
		list.add(1);
		list.add(0);
		int[] expected = {0,1,2,3};
		list.reverse();
		for(int i=0;i<list.size();i++){
			assertEquals(expected[i], list.get(i));
		}
	}

	@Test
	public void testRemoveFirstHalf() {
		list.add(3);
		list.add(2);
		list.add(1);
		list.add(0);
		int[] expected = {1,0};
		list.removeFirstHalf();
		for(int i=0;i<list.size();i++){
			assertEquals(expected[i], list.get(i));
		}
		
		list = new LinkedList();
		list.add(4);
		list.add(3);
		list.add(2);
		list.add(1);
		list.add(0);
		int[] expected1 = {2,1,0};
		list.removeFirstHalf();
		for(int i=0;i<list.size();i++){
			assertEquals(expected1[i], list.get(i));
		}
		
	}

	@Test
	public void testRemoveIntInt() {
		list.add(3);
		list.add(2);
		list.add(1);
		list.add(0);
		int[] expected = {3,0};
		list.remove(1,2);
		for(int i=0;i<list.size();i++){
			assertEquals(expected[i], list.get(i));
		}
		
		list = new LinkedList();
		list.add(4);
		list.add(3);
		list.add(2);
		list.add(1);
		list.add(0);
		list.remove(0,8);
		int[] expected1 = {4,3,2,1,0};
		for(int i=0;i<list.size();i++){
			assertEquals(expected1[i], list.get(i));
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
		LinkedList listB = new LinkedList();
		listB.add(1);
		listB.add(3);
		listB.add(4);
		listB.add(6);
		int[] expected = {101,301,401,601};
		int[] array = list.getElements(listB);
		for(int i=0;i<array.length;i++){
			assertEquals(expected[i], array[i]);
		}
		
		list = new LinkedList();
		list.add(11);
		list.add(101);
		list.add(201);
		list.add(301);
		list.add(401);
		list.add(501);
		list.add(601);
		list.add(701);
		listB = new LinkedList();
		listB.add(1);
		listB.add(3);
		listB.add(100);
		listB.add(101);
		int[] expected1 = {101,301};
		int[] array1 = list.getElements(listB);
		for(int i=0;i<array1.length;i++){
			assertEquals(expected1[i], array1[i]);
		}
		
		list = new LinkedList();
		list.add(11);
		list.add(101);
		list.add(201);
		list.add(301);
		list.add(401);
		list.add(501);
		list.add(601);
		list.add(701);
		listB = new LinkedList();
		int[] array2 = list.getElements(listB);
		assertEquals(0, array2.length);
		
		
	}

	@Test
	public void testSubtract() {
		list.add(11);
		list.add(101);
		list.add(201);
		list.add(301);
		list.add(401);
		list.add(501);
		list.add(601);
		list.add(701);
		LinkedList listB = new LinkedList();
		listB.add(11);
		listB.add(201);
		listB.add(401);
		listB.add(801);
		int[] expected = {101,301,501,601,701};
		list.subtract(listB);
		for(int i=0;i<list.size();i++){
			assertEquals(expected[i], list.get(i));
		}
		
		list = new LinkedList();
		list.add(101);
		list.add(201);
		list.add(301);
		listB = new LinkedList();
		int[] expected1 = {101,201,301};
		list.subtract(listB);
		for(int i=0;i<list.size();i++){
			assertEquals(expected1[i], list.get(i));
		}
	}

	@Test
	public void testRemoveDuplicateValues() {
		list.add(1);
		list.add(1);
		list.add(2);
		list.add(2);
		list.add(3);
		list.add(3);
		list.add(4);
		list.add(4);
		int[] expected = {1,2,3,4};
		list.removeDuplicateValues();
		for(int i=0;i<list.size();i++){
			assertEquals(expected[i], list.get(i));
		}
		
		list = new LinkedList();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		int[] expected1 = {1,2,3,4};
		list.removeDuplicateValues();
		for(int i=0;i<list.size();i++){
			assertEquals(expected1[i], list.get(i));
		}
	}

	@Test
	public void testRemoveRange() {
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(6);
		list.add(7);
		int[] expected = {1,7};
		list.removeRange(1, 7);
		for(int i=0;i<list.size();i++){
			assertEquals(expected[i], list.get(i));
		}
		
		list = new LinkedList();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(6);
		list.add(7);
		int[] expected1 = {1,2,3,4,5,6,7};
		list.removeRange(0,1);
		for(int i=0;i<list.size();i++){
			assertEquals(expected1[i], list.get(i));
		}
	}

	@Test
	public void testIntersection() {
		list.add(11);
		list.add(101);
		list.add(201);
		list.add(301);
		list.add(401);
		list.add(501);
		list.add(601);
		list.add(701);
		LinkedList listB = new LinkedList();
		listB.add(11);
		listB.add(33);
		listB.add(201);
		listB.add(401);
		listB.add(801);
		int[] expected = {11,201,401};
		LinkedList result=list.intersection(listB);
		for(int i=0;i<result.size();i++){
			assertEquals(expected[i], result.get(i));
		}
	}

}
