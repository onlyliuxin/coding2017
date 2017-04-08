package main.week01.data_structure;

import static org.junit.Assert.*;

import java.util.Arrays;

import main.week01.data_structure.LinkedList.LinkedListIterator;

import org.junit.Before;
import org.junit.Test;

public class LinkedListTest {

	private LinkedList list;
	private LinkedList[] lists = new LinkedList[3];

	@Before
	public void setUp() throws Exception {
		list = new LinkedList();
		
		lists[0] = new LinkedList();
		lists[1] = new LinkedList();
		lists[2] = new LinkedList();

		lists[1].add("A");

		lists[2].add("A");
		lists[2].add("B");
		lists[2].add("C");
		lists[2].add("D");
		lists[2].add("E");

	}

	@Test
	public void testGet() {
		list.add("A");
		list.add("B");
		list.add(0, "C");
		assertEquals("C", list.get(0));
	}

	@Test
	public void testRemove() {
		list.add("A");
		list.add("B");
		list.add("C");
		list.add("D");
		list.add(0, "E");
		assertEquals("E", list.remove(0));
		assertEquals("D", list.remove(list.size() - 1));
		assertEquals(3, list.size());
	}

	@Test
	public void testIterator() {
		LinkedListIterator iter = list.iterator();
		list.add("A");
		list.add("B");
		list.add(0, "C");
		assertTrue(iter.hasNext());
		assertEquals("C", iter.next());
		assertTrue(iter.hasNext());
		assertEquals("A", iter.next());
		assertTrue(iter.hasNext());
		assertEquals("B", iter.next());
		assertFalse(iter.hasNext());
	}

	@Test
	public void testReverse() {
		LinkedList l = lists[2];
		l.reverse();
		// assertEquals("", sb.toString());
		// assertEquals("A", l.toString());
		assertEquals("E->D->C->B->A->null", l.toString());
		
	}

	@Test
	public void testRemoveFirstHalf() {
		LinkedList l = lists[0];
		l.removeFirstHalf();
		
		LinkedListIterator iter = l.iterator();
		StringBuilder sb = new StringBuilder();
		while (iter.hasNext()) {
			sb.append(iter.next());
			sb.append("->");
		}
		sb.append("null");
		
		assertEquals("null", sb.toString());
		//assertEquals("A->null", sb.toString());
		//assertEquals("C->D->E->null", sb.toString());
	}

	@Test
	public void testRemoveByIndex() {
		try{
			LinkedList l = lists[2];
			l.remove(0, 1);
			System.out.println(l.toString());
		}catch(Exception e){
			assertEquals(IndexOutOfBoundsException.class, e.getClass());
		}
	}

	@Test
	public void testGetElements() {
		list.add(11);
		list.add(22);
		list.add(33);
		list.add(44);
		list.add(55);
		list.add(66);
		list.add(77);
		list.add(88);
		list.add(99);
		
		LinkedList l = new LinkedList();
		l.add(1);
		l.add(3);
		l.add(4);
		l.add(6);
		try{
			int[] res = list.getElements(l);
			System.out.println(Arrays.toString(res));
		}catch(Exception e){
			assertEquals(e.getMessage(), "传入链表为空？");
		}
	}

	@Test
	public void testSubtract() {
		list.add(11);
		list.add(22);
		list.add(33);
		list.add(44);
		list.add(55);
		list.add(66);
		list.add(77);
		list.add(88);
		list.add(99);
		
		LinkedList l = new LinkedList();
		l.add(11);
		l.add(33);
		l.add(44);
		l.add(66);
		try{
			list.subtract(l);
			System.out.println(list.toString());
		}catch(Exception e){
			assertEquals(e.getMessage(), "传入链表为空？");
		}
	}

	@Test
	public void testRemoveDuplicateValues() {
		list.add(11);
		list.add(11);
		list.add(22);
		list.add(33);
		list.add(33);

		list.removeDuplicateValues();
		
		System.out.println(list.toString());
	}

	@Test
	public void testRemoveRange() throws Exception {
		list.add(11);
		list.add(22);
		list.add(33);
		list.add(44);
		list.add(55);
		list.add(66);
		list.add(77);
		list.add(88);
		list.add(99);
		System.out.println(list.toString());
		
		try{
			list.removeRange(50, 80);
			System.out.println(list.toString());
		}catch(Exception e){
			assertEquals(e.getMessage(), "输入有问题！");
		}
	}

	@Test
	public void testIntersection() {
		list.add(11);
//		list.add(22);
//		list.add(33);
//		list.add(44);
//		list.add(55);
//		list.add(66);
//		list.add(77);
//		list.add(88);
//		list.add(99);
		
		LinkedList l = new LinkedList();
		l.add(11);
		l.add(33);
//		l.add(44);
//		l.add(66);

		System.out.println(list.intersection(l).toString());
	}
}
