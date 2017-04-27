package com.coding.basic;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class LinkedListTest {

	private LinkedList testList;

    private int initTestSize = 5;

    @Before
    public void init() {
// 		初始化的方法：
    	testList = new LinkedList();

        for (int i = 0; i < initTestSize; i++) {
        	testList.add((Object)i);
        }
        testList.printLinkedList();
    	
    }


	
	@Test
	public void testLinkedListIntArray() {
		int[] arr = {1,3,5,8,100};
		LinkedList testList = new LinkedList(arr); //错误的原因是构造函数里面的head没有使用this
		String expectedList = "1->3->5->8->100";
		Assert.assertEquals(expectedList, testList.printLinkedList());
	}

	@Test
	public void testPrintLinkedList() {
		//?????????????
//		int[] arr = {};
//		LinkedList testList = new LinkedList(arr);
//		Assert.assertNull(testList.printLinkedList());
		
		int[] arr2 = {20};
		LinkedList testList2 = new LinkedList(arr2);
		String expectedList2 = "20";
		Assert.assertEquals(expectedList2, testList2.printLinkedList());
	}

	@Test
	public void testAddObject() {
		testList.add(6);
		testList.add(8);
		String expectedList = "0->1->2->3->4->6->8";
		Assert.assertEquals(expectedList, testList.printLinkedList());
	}

	@Test
	public void testAddIntObject() {
		//测试ADD在尾部
		testList.add(5, 100);
		String expectedList = "0->1->2->3->4->100";
		Assert.assertEquals(expectedList, testList.printLinkedList());
		//测试ADD在头部
		testList.add(0, 200);
		String expectedList2 = "200->0->1->2->3->4->100";
		Assert.assertEquals(expectedList2, testList.printLinkedList());
		
		//测试ADD在中间
		testList.add(3, 300);
		String expectedList3 = "200->0->1->300->2->3->4->100";
		Assert.assertEquals(expectedList3, testList.printLinkedList());
		
		//测试Add节点》链表size
		Assert.assertFalse(testList.add(10, 1000));
	}

	@Test
	public void testGet() {
		Assert.assertNull(testList.get(7));
		Assert.assertEquals("0", testList.get(0).toString());
		Assert.assertEquals("3", testList.get(3).toString());
		Assert.assertEquals("4", testList.get(4).toString());
	}

	@Test
	public void testRemoveInt() {
		//测试删除头结点
		testList.remove(0);
		String expectedList1 = "1->2->3->4";
		Assert.assertEquals(expectedList1, testList.printLinkedList());
		//测试删除尾节点
		testList.remove(3);
		String expectedList2 = "1->2->3";
		Assert.assertEquals(expectedList2, testList.printLinkedList());
		//测试删除中间节点
		testList.remove(1);
		String expectedList3 = "1->3";
		Assert.assertEquals(expectedList3, testList.printLinkedList());
		//测试删除的节点>链表SIZE
		Assert.assertFalse("Error!Index larger than the size of Linkedlist", testList.remove(2));
	}

	@Test
	public void testSize() {
		//测试正常
		Assert.assertEquals(5, testList.size());
		//测试空链表
		LinkedList newList = new LinkedList(); //调用没有参数的构造函数
		Assert.assertEquals(0, newList.size());
	}

	@Test
	public void testAddFirst() {
		//测试正常
		testList.addFirst(100);
		String expectedList1 = "100->0->1->2->3->4";
		Assert.assertEquals(expectedList1, testList.printLinkedList());
		//测试空链表
		LinkedList nullList = new LinkedList();
		nullList.addFirst(100);
		String expectedList2 = "100";
		Assert.assertEquals(expectedList2,nullList.printLinkedList());
		
	}

	@Test
	public void testAddLast() {
		//测试正常
		testList.addLast(100);
		String expectedList1 = "0->1->2->3->4->100";
		Assert.assertEquals(expectedList1, testList.printLinkedList());
		//测试空链表
		LinkedList nullList = new LinkedList();
		nullList.addLast(100);
		String expectedList2 = "100";
		Assert.assertEquals(expectedList2,nullList.printLinkedList());
		
	}

	@Test
	public void testRemoveFirst() {
		//测试删除空链表
		LinkedList nullList = new LinkedList();
		Assert.assertFalse(nullList.removeFirst());
		//测试删除只有一个节点的链表
		LinkedList newList = new LinkedList();
		newList.add(100);
		Assert.assertTrue(newList.removeFirst());
		//测试删除正常链表		
		Assert.assertTrue(testList.removeFirst());
		String expectedResult = "1->2->3->4";
		Assert.assertEquals(expectedResult, testList.printLinkedList());
	}

	@Test
	public void testRemoveLast() {
		//测试删除空链表
		LinkedList nullList = new LinkedList();
		Assert.assertFalse(nullList.removeLast());
		//测试删除只有一个节点的链表
		LinkedList newList = new LinkedList();
		newList.add(100);
		Assert.assertTrue(newList.removeLast());
		//测试删除正常链表
		Assert.assertTrue(testList.removeLast());
		String expectedResult = "0->1->2->3";
		Assert.assertEquals(expectedResult, testList.printLinkedList());
	}

//	@Test
//	public void testIterator() {
//		fail("Not yet implemented");
//	}

	@Test
	public void testReverse() {
		//测空链表
		LinkedList nullList = new LinkedList();
		nullList.reverse();
		Assert.assertEquals("", nullList.printLinkedList());
		//测只有一个节点的链表
		LinkedList list1 = new LinkedList();
		list1.add(100);
		list1.reverse();
		Assert.assertEquals("100", list1.printLinkedList());
		//测正常链表
		testList.reverse();
		Assert.assertEquals("4->3->2->1->0", testList.printLinkedList());
	}

	@Test
	public void testRemoveFirstHalf() {
		int[] arr1 = {2,5,7,8};
		int[] arr2 = {2,5,7,8,10};
		String expected1 = "7->8";
		String expected2 = "7->8->10";
		LinkedList list1 = new LinkedList(arr1);
		LinkedList list2 = new LinkedList(arr2);
		list1.removeFirstHalf();
		list2.removeFirstHalf();
		Assert.assertEquals(expected1, list1.printLinkedList());
		Assert.assertEquals(expected2, list2.printLinkedList());
		
	}

	@Test
	public void testRemoveIntInt() {
		//index+length>size
		LinkedList nullList = new LinkedList();
		nullList.remove(3, 0);
		Assert.assertEquals("", nullList.printLinkedList());
		//测试index大于等于size,且index+length又不大于size的
		testList.remove(5,0);
		Assert.assertEquals("0->1->2->3->4", testList.printLinkedList());
		//remove后接起来的
		testList.remove(1, 2);
		Assert.assertEquals("0->3->4", testList.printLinkedList());
		//remove后直接到尾部的
		testList.remove(1,2);
		Assert.assertEquals("0", testList.printLinkedList());
		//只剩一个节点，但remove(0,0),即不删
		testList.remove(0,0);
		Assert.assertEquals("0", testList.printLinkedList());
		//只剩一个节点，remove(1,0),出错
		testList.remove(1,0);
		Assert.assertEquals("0", testList.printLinkedList());
		//只剩一个节点，remove(0,1),删
		testList.remove(0,1);
		Assert.assertEquals("", testList.printLinkedList());
	}

	@Test
	public void testGetElements() {
		//测试溢出
		int[] arr1 = {11,101,201,301,401,501,601,701};
		LinkedList primeList = new LinkedList(arr1);
		int[] arr2 = {0,3,6,8};
		LinkedList overflowList = new LinkedList(arr2);
		Assert.assertNull(primeList.getElements(overflowList));
		//测试正常
		int[] arr3 = {0,3,6,7};
		LinkedList indexList = new LinkedList(arr3);
		String expected = "11->301->601->701";
		int[] results = primeList.getElements(indexList);
		//Junit是否提供两个数组直接作比较的函数呢？
		String str_results = "";
		for(int i=0;i<results.length;i++){
			if(i==results.length-1){
				str_results = str_results + results[i];
				break;
			}
			str_results = str_results + results[i] + "->";
			
		}
		Assert.assertEquals(expected, str_results);
	}

	@Test
	public void testSubtract() {
		int[] arr1 = {11,101,201,301,401,501,601,701};
		LinkedList primeList = new LinkedList(arr1);
		int[] testlist = {0,1,2,3,11,15,16,18,201,300,301,500,600,601,602,700};
		LinkedList subList = new LinkedList(testlist);
		primeList.subtract(subList);
		Assert.assertEquals("101->401->501->701",primeList.printLinkedList());
	}

	@Test
	public void testRemoveDuplicateValues() {
		int[] arr1 = {11,11,201,301,301,501,601,701,701};
		LinkedList primeList = new LinkedList(arr1);
		primeList.removeDuplicateValues();
		Assert.assertEquals("11->201->301->501->601->701", primeList.printLinkedList());
	}

	@Test
	public void testRemoveRange() {
		int[] testlist = {0,1,2,3,11,15,16,18,201,300,301,500,600,601,602,700};
		LinkedList primeList = new LinkedList(testlist);
		primeList.removeRange(10,600);
		Assert.assertEquals("0->1->2->3->601->602->700", primeList.printLinkedList());
	}

	@Test
	public void testIntersection() {
		int[] list1 = {0,1,3,5};
		LinkedList aList = new LinkedList(list1);
		int[] list2 = {0,2,3,4,5,6};
		LinkedList bList = new LinkedList(list2);
		LinkedList cList = aList.intersection(bList);
		Assert.assertEquals("0->1->2->3->4->5->6", cList.printLinkedList());
	}

}
