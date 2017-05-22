package com.coding.basic;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LinkedListTest {

	@Test
	public void testLinkedList() {
		System.out
				.println("-----------------------这是华丽的分割线------------------------");
		System.out.println("test linkedList Contructor");
		LinkedList linkedList = new LinkedList();
	}

	@Test
	public void testAddObject() {
		System.out
				.println("-----------------------这是华丽的分割线------------------------");
		System.out.println("test addObject(T o)");
		LinkedList linkedList = new LinkedList();
		linkedList.add("1");
		System.out.println(linkedList.get(0));
	}

	@Test
	public void testAddIntObject() {
		System.out
				.println("-----------------------这是华丽的分割线------------------------");
		System.out.println("test addObject(int index,T o)");
		LinkedList linkedList = new LinkedList();
		linkedList.add(0, "2");
		System.out.println(linkedList.get(0));
	}

	@Test
	public void testGet() {
		System.out
				.println("-----------------------这是华丽的分割线------------------------");
		System.out.println("test get(int index)");
		LinkedList linkedList = new LinkedList();
		linkedList.add(0, "2");
		System.out.println(linkedList.get(0));
	}

	@Test
	public void testRemove() {
		System.out
				.println("-----------------------这是华丽的分割线------------------------");
		System.out.println("test remove(int index)");
		LinkedList linkedList = new LinkedList();
		linkedList.add(0, "2");
		System.out.println("size before removing" + linkedList.size());
		linkedList.remove(0);
		System.out.println("size after removing" + linkedList.size());
	}

	@Test
	public void testSize() {
		System.out
				.println("-----------------------这是华丽的分割线------------------------");
		System.out.println("test size()");
		LinkedList linkedList = new LinkedList();
		System.out.println(linkedList.size());
		linkedList.add(0, "2");
		linkedList.add(0, "2");
		linkedList.add(0, "2");
		linkedList.add(0, "2");
		linkedList.add(0, "2");
		linkedList.add(0, "2");
		linkedList.add(0, "2");
		linkedList.add(0, "2");
		System.out.println(linkedList.size());
		linkedList.remove(0);
		System.out.println(linkedList.size());
	}

	@Test
	public void testAddFirst() {
		System.out
				.println("-----------------------这是华丽的分割线------------------------");
		System.out.println("test addFirst()");
		LinkedList linkedList = new LinkedList();
		linkedList.add(0, "1");
		linkedList.add(1, "2");
		linkedList.addFirst("3");
		System.out.println(linkedList.get(0));
		System.out.println(linkedList.get(1));
		System.out.println(linkedList.get(2));
	}

	@Test
	public void testAddLast() {
		System.out
				.println("-----------------------这是华丽的分割线------------------------");
		System.out.println("test addLast()");
		LinkedList linkedList = new LinkedList();
		linkedList.add(0, "1");
		linkedList.add(1, "2");
		linkedList.addLast("3");
		System.out.println(linkedList.get(0));
		System.out.println(linkedList.get(1));
		System.out.println(linkedList.get(2));

	}

	@Test
	public void testRemoveFirst() {
		System.out
				.println("-----------------------这是华丽的分割线------------------------");
		System.out.println("test removeFirst()");
		LinkedList linkedList = new LinkedList();
		linkedList.add(0, "1");
		linkedList.add(1, "2");
		linkedList.addLast("3");
		System.out.println(linkedList.get(0));
		System.out.println(linkedList.get(1));
		System.out.println(linkedList.get(2));
		linkedList.removeFirst();
		System.out.println(linkedList.get(0));
		System.out.println(linkedList.get(1));
	}

	@Test
	public void testRemoveLast() {
		System.out
				.println("-----------------------这是华丽的分割线------------------------");
		System.out.println("test removeLast()");
		LinkedList linkedList = new LinkedList();
		linkedList.add(0, "1");
		linkedList.add(1, "2");
		linkedList.addLast("3");
		System.out.println(linkedList.get(0));
		System.out.println(linkedList.get(1));
		System.out.println(linkedList.get(2));
		linkedList.removeLast();
		System.out.println(linkedList.get(0));
		System.out.println(linkedList.get(1));
	}

	@Test
	public void testIterator() {
		System.out
				.println("-----------------------这是华丽的分割线------------------------");
		System.out.println("test iterator()");
		LinkedList<String> linkedList = new LinkedList<>();
		linkedList.add(0, "1");
		linkedList.add(1, "2");
		linkedList.add(2, "3");
		linkedList.add(3, "4");
		linkedList.add(4, "5");
		linkedList.add(5, "6");
		linkedList.add(6, "7");
		linkedList.add(7, "8");
		Iterator iterator = linkedList.iterator();
		while (iterator.hasNext()) {
			System.out.print(iterator.next());
		}
	}

	@Test
	public void testReverse() {
		System.out
				.println("-----------------------这是华丽的分割线------------------------");
		System.out.println("test reverse()");
		LinkedList<Integer> linkedList = new LinkedList<>();
		linkedList.add(01);
		linkedList.add(2);
		linkedList.add(3);
		linkedList.add(4);
		linkedList.add(5);
		linkedList.add(6);
		linkedList.add(7);
		linkedList.add(8);
		linkedList.reverse();
		Iterator iterator = linkedList.iterator();
		while (iterator.hasNext()) {
			System.out.print(iterator.next() + " ");
		}
	}

	@Test
	public void testRemoveFirstHalf() {
		System.out
				.println("-----------------------这是华丽的分割线------------------------");
		System.out.println("test removeFirstHalf()");
		LinkedList<Integer> linkedList = new LinkedList<>();
		linkedList.add(1);
		linkedList.add(2);
		linkedList.add(3);
		linkedList.add(4);
		linkedList.add(5);
		linkedList.add(6);
		linkedList.add(7);
		linkedList.add(8);
		linkedList.add(9);
		System.out.println(linkedList.size());
		Iterator iterator = linkedList.iterator();
		System.out.print("before removeFirstHalf():");
		while (iterator.hasNext()) {
			System.out.print(iterator.next() + "->");
		}
		System.out.println();
		linkedList.removeFirstHalf();
		iterator = linkedList.iterator();
		System.out.print("after removeFirstHalf():");
		while (iterator.hasNext()) {
			System.out.print(iterator.next() + "->");
		}
	}

	@Test
	public void testRemoveIntInt() {
		System.out
				.println("-----------------------这是华丽的分割线------------------------");
		System.out.println("test remove(int index,int length)");
		LinkedList<Integer> linkedList = new LinkedList<>();
		linkedList.add(0, 1);
		linkedList.add(1, 2);
		linkedList.add(2, 3);
		linkedList.add(3, 4);
		linkedList.add(4, 5);
		linkedList.add(5, 6);
		linkedList.add(6, 7);
		linkedList.add(7, 8);
		linkedList.add(8, 9);
		Iterator iterator = linkedList.iterator();
		int index = 1;
		int len = 16;
		System.out.print("before remove:");
		while (iterator.hasNext()) {
			System.out.print(iterator.next() + "->");
		}
		System.out.println("size:"+linkedList.size());
		System.out.println();
		linkedList.remove(index, len);
		iterator = linkedList.iterator();
		System.out.print("after remove(" + index + "," + len + ")");
		while (iterator.hasNext()) {
			System.out.print(iterator.next() + "->");
		}
		System.out.println("size:"+linkedList.size());

	}

	@Test
	public void testGetElements() {
		System.out
				.println("-----------------------这是华丽的分割线------------------------");
		System.out.println("test getElements(LinkedList<Integer> list)");
		LinkedList<Integer> linkedList = new LinkedList<>();
		linkedList.add(0, 1);
		linkedList.add(1, 2);
		linkedList.add(2, 3);
		linkedList.add(3, 4);
		linkedList.add(4, 5);
		linkedList.add(5, 6);
		linkedList.add(6, 7);
		linkedList.add(7, 8);
		linkedList.add(8, 9);
		LinkedList<Integer> list = new LinkedList<>();
		list.add(1);
		list.add(3);
		list.add(5);
		list.add(7);
		list.add(9);
		System.out.print("before testing:");
		Iterator iterator = linkedList.iterator();
		while (iterator.hasNext()) {
			System.out.print(iterator.next() + "->");
		}
		System.out.println();
		iterator = list.iterator();
		String listvalue = "";
		while (iterator.hasNext()) {
			listvalue += iterator.next() + ",";
		}
		System.out.print("get elements in indexs: " + "[" + listvalue + "]"
				+ Arrays.toString(linkedList.getElements(list)));

	}

	@Test
	public void testSubtract() {
		System.out
				.println("-----------------------这是华丽的分割线------------------------");
		System.out.println("test subtract(LinkedList<Integer> list)");
		LinkedList<Integer> linkedList = new LinkedList<>();
		linkedList.add(0, 1);
		linkedList.add(1, 2);
		linkedList.add(2, 3);
		linkedList.add(3, 4);
		linkedList.add(4, 5);
		linkedList.add(5, 6);
		linkedList.add(6, 7);
		linkedList.add(7, 8);
		linkedList.add(8, 9);
		LinkedList<Integer> list = new LinkedList<>();
		list.add(1);
		list.add(3);
		list.add(5);
		list.add(7);
		list.add(9);
		list.add(12);
		list.add(13);
		System.out.print("before testing:");
		Iterator iterator = linkedList.iterator();
		while (iterator.hasNext()) {
			System.out.print(iterator.next() + "->");
		}
		System.out.println();
		iterator = list.iterator();
		String listvalue = "";
		while (iterator.hasNext()) {
			listvalue += iterator.next() + "->";
		}
		System.out.println("after subtract Linkedlist:" + listvalue);
		linkedList.subtract(list);
		iterator = linkedList.iterator();
		while (iterator.hasNext()) {
			System.out.print(iterator.next() + "->");
		}
	}

	@Test
	public void testRemoveDuplicateValues() {
		System.out
				.println("-----------------------这是华丽的分割线------------------------");
		System.out.println("test removeDuplicateValues()");
		LinkedList<Integer> linkedList = new LinkedList<>();
		linkedList.add(1);
		linkedList.add(2);
		linkedList.add(2);
		linkedList.add(3);
		linkedList.add(4);
		linkedList.add(4);
		linkedList.add(5);
		linkedList.add(6);
		linkedList.add(6);
		linkedList.add(7);
		linkedList.add(8);
		linkedList.add(8);
		linkedList.add(9);
		System.out.print("before testing: ");
		Iterator iterator = linkedList.iterator();
		while (iterator.hasNext()) {
			System.out.print(iterator.next() + "->");
		}
		System.out.println();
		System.out.println("after test: ");
		iterator = linkedList.iterator();
		linkedList.removeDuplicateValues();
		while (iterator.hasNext()) {
			System.out.print(iterator.next() + "->");
		}
	}

	@Test
	public void testRemoveRange() {
		System.out
				.println("-----------------------这是华丽的分割线------------------------");
		System.out.println("test removeRange(int min,int max)");
		LinkedList<Integer> linkedList = new LinkedList<>();
		linkedList.add(1);
		linkedList.add(2);
		linkedList.add(3);
		linkedList.add(4);
		linkedList.add(5);
		linkedList.add(6);
		linkedList.add(7);
		linkedList.add(8);
		linkedList.add(9);
		linkedList.add(10);
		linkedList.add(11);
		linkedList.add(12);
		linkedList.add(13);
		System.out.print("before testing: ");
		Iterator iterator = linkedList.iterator();
		while (iterator.hasNext()) {
			System.out.print(iterator.next() + "->");
		}
		int min = 1;
		int max = 4;
		linkedList.remove(min, max);
		System.out.println();
		System.out.println("after removing index form " + min + " to " + max);
		iterator = linkedList.iterator();
		while (iterator.hasNext()) {
			System.out.print(iterator.next() + "->");
		}

	}

	@Test
	public void testIntersection() {
		System.out
				.println("-----------------------这是华丽的分割线------------------------");
		System.out.println("test intersection(LinkedList<Integer> list)");
		LinkedList<Integer> linkedList = new LinkedList<>();
		linkedList.add(1);
		linkedList.add(2);
		linkedList.add(3);
		linkedList.add(4);
		linkedList.add(5);
		linkedList.add(7);
		linkedList.add(9);
		linkedList.add(10);
		LinkedList<Integer> list = new LinkedList<>();
		list.add(1);
		list.add(2);
		list.add(4);
		list.add(5);
		list.add(6);
		list.add(7);
		list.add(10);
		System.out.println("before testing:");
		System.out.print("linkedList:");
		Iterator iterator = linkedList.iterator();
		while (iterator.hasNext()) {
			System.out.print(iterator.next() + "->");
		}
		System.out.println();
		System.out.print("list:");
		iterator = list.iterator();
		while (iterator.hasNext()) {
			System.out.print(iterator.next() + "->");
		}
		System.out.println();
		linkedList=linkedList.intersection(list);
		System.out.println("after linkedList.intersection(list):");
		iterator = linkedList.iterator();
		while (iterator.hasNext()) {
			System.out.print(iterator.next() + "->");
		}

	}

}
