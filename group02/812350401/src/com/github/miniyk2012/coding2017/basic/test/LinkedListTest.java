package com.github.miniyk2012.coding2017.basic.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.miniyk2012.coding2017.basic.Iterator;
import com.github.miniyk2012.coding2017.basic.LinkedList;


public class LinkedListTest extends ListTest{

	private LinkedList aLinkedList;
	
	@Before
	public void setUpLinkedList() {
		aList = new LinkedList();
		aLinkedList = new LinkedList();
	}

	/**
	 * 测试iterator不因为各种操作而失效
	 */
	@After
	public void testIterator2() {
		java.util.LinkedList aStandardList = new java.util.LinkedList();
		for (int i=0; i<aLinkedList.size(); i++) {
			aStandardList.add(aLinkedList.get(i));
		}
		Iterator it = aLinkedList.iterator();
		int index = 0;
		while(it.hasNext()) {
			assertEquals(aStandardList.get(index), it.next());
			index++;
		}
	}
	
	@Test
	public void testAddFirst() {
		aLinkedList.addFirst(5);
		assertEquals(5, aLinkedList.get(0));
		
		aLinkedList.addFirst(6);
		assertEquals(6, aLinkedList.get(0));
		assertEquals(5, aLinkedList.get(1));
		assertEquals(2, aLinkedList.size());
	}
	
	@Test
	public void testAddLast() {
		aLinkedList.addLast("hello");
		assertEquals("hello", aLinkedList.get(0));
		
		aLinkedList.addLast("world");
		assertEquals("hello", aLinkedList.get(0));
		assertEquals("world", aLinkedList.get(1));
		assertEquals(2, aLinkedList.size());
	}
	
	@Test
	public void testRemoveFirst() {
		aLinkedList.addLast("hello");
		aLinkedList.addLast("world");
		
		aLinkedList.removeFirst();
		assertEquals("world", aLinkedList.get(0));
		assertEquals(1, aLinkedList.size());
		
		aLinkedList.removeFirst();
		assertEquals(0, aLinkedList.size());
	}
	
	@Test
	public void testRemoveLast() {
		aLinkedList.addFirst("world");
		aLinkedList.addFirst("hello");
		
		aLinkedList.removeLast();
		assertEquals("hello", aLinkedList.get(0));
		assertEquals(1, aLinkedList.size());
		
		aLinkedList.removeLast();
		assertEquals(0, aLinkedList.size());
	}
	
	@Test
	public void testLinkedListFunctional() {
		for (int i=1; i<4; i++) {
			aLinkedList.add(i);  // [1,2,3]
		}
		aLinkedList.remove(1);  // [1,3]
		
		aLinkedList.add(1, 0);  // [1,0,3]
		for (int i=4; i<6; i++) {
			aLinkedList.addFirst(i);  // [5, 4, 1, 0, 3]
		}
		assertEquals(5, aLinkedList.size());
		assertEquals(5, aLinkedList.get(0));
		assertEquals(1, aLinkedList.get(2));
		assertEquals(0, aLinkedList.get(3));
		
		aLinkedList.remove(3);  // [5, 4, 1, 3]
		assertEquals(3, aLinkedList.get(aLinkedList.size()-1));
		aLinkedList.removeLast();  // [5, 4, 1]
		assertEquals(1, aLinkedList.get(aLinkedList.size()-1));
		aLinkedList.removeFirst();   // [4,1]
		
		assertEquals(4, aLinkedList.get(0));
		assertEquals(1, aLinkedList.get(1));
		assertEquals(2, aLinkedList.size());
	}
	
	@Test
	public void testReverse() {
		// 测试当aLinkedList为空时的情况
		aLinkedList.reverse();
		assertEquals(0, aLinkedList.size());
		
		// 测试当aLinkedList长度为1时的情况
		aLinkedList.add(4);
		aLinkedList.reverse();
		assertEquals(1, aLinkedList.size());
		assertEquals(4, aLinkedList.get(0));
		 
		for (int i=1; i<4; i++) {
			aLinkedList.add(i);  // [4,1,2,3]
		}
		aLinkedList.reverse();
		assertEquals(4, aLinkedList.size());
		assertEquals(3, aLinkedList.get(0));
		assertEquals(2, aLinkedList.get(1));
		assertEquals(1, aLinkedList.get(2));
		assertEquals(4, aLinkedList.get(3));
		
	}

	/**
	 * 删除一个单链表的前半部分
	 * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
	 * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10

	 */
	@Test
	public void testRemoveFirstHalf() {
		aLinkedList.removeFirstHalf();
		assertEquals(0, aLinkedList.size());
		
		aLinkedList.add(2);
		aLinkedList.add(5);
		aLinkedList.add(7);
		aLinkedList.add(8);  // [2,5,7,8]
		
		aLinkedList.removeFirstHalf();  // [7,8]
		assertEquals(2, aLinkedList.size());
		assertEquals(7, aLinkedList.get(0));
		assertEquals(8, aLinkedList.get(1));
	}

	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * @param i
	 * @param length
	 */
	@Test
	public void testRemoveIntInt() {
		
		for (int i=0; i<4; i++) {
			aLinkedList.add(i);  // [0,1,2,3]
		}
		
		aLinkedList.remove(0, 2);  // [2,3]
		assertEquals(2, aLinkedList.get(0));
		assertEquals(3, aLinkedList.get(1));
		assertEquals(2, aLinkedList.size());
		
		aLinkedList.remove(1, 0);
		aLinkedList.remove(0, 0);
		assertEquals(2, aLinkedList.size());
		
		aLinkedList.remove(1, 1);  // [2]
		assertEquals(1, aLinkedList.size());
		assertEquals(2, aLinkedList.get(0));
		
		aLinkedList.remove(0, 1);  // []
		assertEquals(0, aLinkedList.size());
		
		expectedEx.expect(Exception.class);
		aLinkedList.remove(1, 3);
	}

	/**
	 * 假定当前链表和list均包含已升序排列的整数
	 * 从当前链表中取出那些list所指定的元素
	 * 例如当前链表 = 11->101->201->301->401->501->601->701
	 * listB = 1->3->4->6
	 * 返回的结果应该是[101,301,401,601]  
	 * @param list
	 */
	@Test
	public void testGetElements() {
		for (int i=0; i<4; i=i*i) {
			aLinkedList.add(i);  // [0,1,4,9]
		}
		
		LinkedList bLinkedList = new LinkedList();
		int[] z1 = aLinkedList.getElements(bLinkedList); // []
		assertEquals(new int[0], z1);
		
		bLinkedList.add(1);
		bLinkedList.add(3);  // [1, 3]
		
		z1 = aLinkedList.getElements(bLinkedList);  // [1, 9]
		assertArrayEquals(new int[] {1,9}, z1);
		
		bLinkedList.add(1, 2);  // bLinkedList = [1, 2, 3]
		z1 = aLinkedList.getElements(bLinkedList);  // [1, 4, 9]
		assertArrayEquals(new int[] {1,4,9}, z1);
		
		bLinkedList.add(0, 0);  // bLinkedList = [0, 1, 2, 3]
		z1 = aLinkedList.getElements(bLinkedList);  // [0, 1, 4, 9]
		assertArrayEquals(new int[] {0,1,4,9}, z1);
		
		// aLinkedList不应该变化
		assertEquals(4, aLinkedList.size());
		for (int i=0; i<4; i++) {
			assertEquals(i*i, aLinkedList.get(i));  // [0,1,4,9]
		}
		
		// Exception
		bLinkedList.add(5);  // bLinkedList = [0, 1, 2, 3, 5]
		expectedEx.expect(Exception.class);
		z1 = aLinkedList.getElements(bLinkedList); 
	}
	
	@Test
	public void testSubtract() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveDuplicateValues() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveRange() {
		fail("Not yet implemented");
	}

	@Test
	public void testIntersection() {
		fail("Not yet implemented");
	}
}
