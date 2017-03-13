package com.A_DataStructure;


import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class myLinkedListTest {
	myLinkedList testAdd;
	 
	@Before
	public void setUp() throws Exception {

 		testAdd = new myLinkedList(); 
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddObject() { 
		
//		testAdd.add(1);
//		testAdd.add(2);
//		testAdd.createFirstNode("我是第3个插入的，头结点 ");
//		int sizeTest = testAdd.size();
//		assertEquals(3, sizeTest);
//		 assertEquals(2, testAdd.getLast().data);
//		 assertEquals("我是第3个插入的，头结点 ",testAdd.get(0));
		 ////测试通过
	}
 
 	@Test
 	public void testAddIntObject() {
// 		testAdd.add("我是第1个");
//		testAdd.add("我是第2个"); 
//		testAdd.add( "我是第3个 ");
//		testAdd.add(2, "我是第4个，但是插在第3个的前面");
//		testAdd.printlnLinked();
//		int sizeTest = testAdd.size();
//		assertEquals(4, sizeTest); 
//		 System.out.println("尾节点："+testAdd.getLast().data); 
//		assertEquals("我是第4个，但是插在第3个的前面",testAdd.get(2)); //因为插在2前面
//		////测试通过
 	
 	}
 
	@Test
	public void testGet() {
//		testAdd.add("我是第1个");
//		testAdd.add("我是第2个");
//		testAdd.add("我是第3个");
//		testAdd.add(1, "我是第3个，但是插在第2个的前面"); 
//		testAdd.printlnLinked();
// 		int sizeTest = testAdd.size();
// 		
// 		assertEquals(4, sizeTest); 
// 		assertEquals( "我是第3个，但是插在第2个的前面",  testAdd.get(1)); 
// 		System.out.println(testAdd.get(0));
// 		System.out.println(testAdd.get(2));
// 		////测试通过
	}

	@Test
	public void testRemoveInt() {
//		testAdd.add("我是第1个");
//		testAdd.add("我是第2个"); 
//		testAdd.add( "我是第3个 ");
//		testAdd.add( "我是第4个 ");
//		testAdd.add(2, "我是第5个，但是插在第3个的前面");
//		testAdd.printlnLinked();
//		 System.out.println("尾节点："+testAdd.getLast().data); 
//		int sizeTest = testAdd.size();
//		assertEquals(5, sizeTest); 
//		//testAdd.remove(1);
//		testAdd.remove(4);
//		testAdd.printlnLinked();
//		System.out.println("删除下标3后尾节点："+testAdd.getLast().data);
//		//System.out.println("删除下标1后尾节点："+testAdd.getLast().data); 
//		////测试通过
	}

//	@Test
//	public void testSize() {
//		 
//	}
//
	@Test
	public void testAddFirst() {
//		testAdd.createFirstNode("我是第1个插入的，头结点 ");
//		testAdd.createFirstNode("我是第2个插入的，新头结点"); 
//		testAdd.createFirstNode("我是第3个插入的，新新头结点"); 
//		testAdd.printlnLinked();
//		assertEquals( "我是第3个插入的，新新头结点",  testAdd.get(0));
//		assertEquals( "我是第2个插入的，新头结点",  testAdd.get(1));
		/////测试通过
	}

	@Test
	public void testAddLast() {
//		testAdd.creatLastNode("我是第1个插入的，尾结点 ");
//		testAdd.creatLastNode("我是第2个插入的，新尾头结点"); 
//		testAdd.creatLastNode("我是第3个插入的，新新尾头结点"); 
//		
//		testAdd.printlnLinked();
//		int sizeTest = testAdd.size();
//	    assertEquals(3, sizeTest); 
//	    assertEquals( "我是第2个插入的，新尾头结点",  testAdd.get(1));
	/////测试通过
	}

	@Test
	public void testRemoveFirst() {
//		testAdd.add("我是第1个");
// 		testAdd.add("我是第2个");  
// 		testAdd.add(0,"我是第3个");
// 		testAdd.add(1,"我是第4个"); 
// 		testAdd.printlnLinked();
// 		int sizeTest = testAdd.size();
// 	    assertEquals(4, sizeTest); 
// 		System.out.println("头节点："+testAdd.getHead().data); 
// 		
// 		testAdd.removeFirst();
// 		
// 		sizeTest = testAdd.size();
// 		assertEquals(3, sizeTest); 
// 		testAdd.printlnLinked();
// 		System.out.println("头节点："+testAdd.getHead().data); 
// 		////测试通过
	}

	@Test
	public void testRemoveLast() {
//		testAdd.add("我是第1个");
//		testAdd.add("我是第2个"); 
//		testAdd.add( "我是第3个 ");
//		testAdd.add( "我是第4个 ");
//		testAdd.add(2, "我是第5个，但是插在第3个的前面");
//		testAdd.printlnLinked();
//		testAdd.remove(4);
//		 System.out.println("尾节点："+testAdd.getLast().data); 
//		////测试通过
	}

	@Test
	public void testReverse() {
//		testAdd.add(3);
// 		testAdd.add(7); 
// 		testAdd.add(10); 
// 		testAdd.printlnLinked();
// 		
// 		 testAdd.reverse();
// 		 testAdd.printlnLinked();
// 		 System.out.println("头节点："+testAdd.getHead().data); 
// 		 System.out.println("尾节点："+testAdd.getLast().data); 
// 		 ////测试通过
	}

	@Test
	public void testRemoveFirstHalf() {
//		testAdd.add(2);
//		testAdd.add(5);
//		testAdd.add(7);
//		testAdd.add(8);
// 		 testAdd.printlnLinked();
////		 System.out.println("头节点："+testAdd.getHead().data); 
////		 System.out.println("尾节点："+testAdd.getLast().data); 
////		
////		int sizeTest = testAdd.size();
//// 		assertEquals(4, sizeTest); 
////		testAdd.removeFirstHalf();
////		
////		sizeTest = testAdd.size();
//// 		assertEquals(2, sizeTest); 
////		 testAdd.printlnLinked();
////		 System.out.println("头节点："+testAdd.getHead().data); 
////		 System.out.println("尾节点："+testAdd.getLast().data);
//
//		 System.out.println("再加一个10后---------------：");
//		 testAdd.add(10);
//		 testAdd.printlnLinked();
//		 testAdd.removeFirstHalf();
//		 testAdd.printlnLinked();
//		 System.out.println("头节点："+testAdd.getHead().data); 
//		 System.out.println("尾节点："+testAdd.getLast().data);
//		
//		 ////通过测试
	}

	@Test
	public void testRemoveIntInt() {
//		testAdd.add(0);
//		testAdd.add(1);
//		testAdd.add(2);
//		testAdd.add(3);
//		testAdd.add(4);
//		testAdd.add(5);
//		testAdd.printlnLinked();
//		testAdd.remove(2, 2);
//		System.out.println("----删除后----------：");
//		testAdd.printlnLinked();
// 	    int sizeTest = testAdd.size();
// 		assertEquals(4, sizeTest); 
//		////测试通过
		
	}

	@Test
	public void testGetElements() {
//		testAdd.add(11);
//		testAdd.add(101);
//		testAdd.add(201);
//		testAdd.add(301);
//		testAdd.add(401);
//		testAdd.add(501);
//		testAdd.add(601);
//		testAdd.add(701);
// 		System.out.println("----操作前----------：");
// 		testAdd.printlnLinked();
// 		
// 		myLinkedList list = new myLinkedList(); 
// 		//list.add(1);
// 		list.add(0);
// 		list.add(3);
// 		list.add(4);
// 		list.add(6);
// 		
// 		int[] result = testAdd.getElements(list);
// 		System.out.println("----操作后----------：");
// 		for(int i=0;i<result.length;i++){
// 			System.out.println("int数组的值是："+result[i]);
// 		}
	}

	@Test
	public void testSubtract() {
//		testAdd.add(0);
//		testAdd.add(1);
//		testAdd.add(2);
//		testAdd.add(3);
//		testAdd.add(4);
//		testAdd.add(5);
//		testAdd.add(6);
// 		System.out.println("----操作前----------：");
// 		testAdd.printlnLinked();
// 		myLinkedList list = new myLinkedList();  
// 		list.add(1);
// 		list.add(2);
// 		list.add(3); 
// 		testAdd.subtract(list);
// 		System.out.println("----操作后----------：");
// 		testAdd.printlnLinked();
// 		////测试通过
		
	}

	@Test
	public void testRemoveDuplicateValues() {
//		testAdd.add(0);
//		testAdd.add(0);
//		testAdd.add(0);
//		testAdd.add(1);
//		testAdd.add(2);
//		testAdd.add(3);
//		testAdd.add(3);
//		testAdd.add(4);
//		testAdd.add(4);
//		testAdd.add(5);
//		testAdd.add(5);
//		testAdd.add(0);
// 		System.out.println("----操作前----------：");
// 		testAdd.printlnLinked();
// 		 
// 		System.out.println("----操作后----------：");
// 		testAdd.removeDuplicateValues();
// 		testAdd.printlnLinked();
// 		////测试通过
	}

	@Test
	public void testRemoveRange() { 
//		testAdd.add(0);
//		testAdd.add(1);
//		testAdd.add(2);
//		testAdd.add(3); 
//		testAdd.add(4); 
//		testAdd.add(5);
//		testAdd.add(6);
// 		System.out.println("----操作前----------：");
// 		testAdd.printlnLinked();
// 		 
// 		System.out.println("----操作后----------：");
// 		testAdd.removeRange(2,5);
// 		testAdd.printlnLinked();
// 		////测试未通过
	}

	@Test
	public void testIntersection() {
		testAdd.add(0);
		testAdd.add(1);
		testAdd.add(2);
		testAdd.add(3); 
		testAdd.add(4); 
		testAdd.add(5);
		testAdd.add(6);
		myLinkedList list = new myLinkedList();
		myLinkedList newC;
		list.add(0);
		list.add(1);
		list.add(3);
		list.add(5);
		
		newC = testAdd.intersection(list);
		for(int i=0;i<newC.size();i++){
			System.out.println("newC的值："+newC.get(i));
		}
	}

}

