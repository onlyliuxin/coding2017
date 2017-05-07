package com.github.chaoswang.learning.java.linkedlist;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import com.github.chaoswang.learning.java.linkedlist.LinkedList;

public class LinkedListTest {

	@Test
	public void testAdd(){
		LinkedList<String> myList = new LinkedList<String>();
		myList.add("1");
		myList.add("2");
		myList.add("3");
		System.out.println(myList);
		Assert.assertEquals(3, myList.size());
		myList.add("4");
		System.out.println(myList);
		Assert.assertEquals(4, myList.size());
		String str = myList.get(2);
		Assert.assertEquals("3", str);
		System.out.println(myList);
	}
	
	@Test
	public void testInsert(){
		LinkedList<String> myList = new LinkedList<String>();
		myList.add("2");
		myList.add("4");
		System.out.println(myList);
		myList.add(0,"1");
		System.out.println(myList);
		String str = myList.get(2);
		Assert.assertEquals("4", str);
		myList.add(2,"3");
		str = myList.get(2);
		System.out.println(myList);
		Assert.assertEquals("3", str);
	}
	
	@Test
	public void testAddFirst(){
		LinkedList<String> myList = new LinkedList<String>();
		myList.add("2");
		myList.add("3");
		myList.add("4");
		System.out.println(myList);
		Assert.assertEquals("2", myList.get(0));
		myList.addFirst("1");
		Assert.assertEquals("1", myList.get(0));
		System.out.println(myList);
	}
	
	@Test
	public void testRemoveFirst(){
		LinkedList<String> myList = new LinkedList<String>();
		myList.add("1");
		myList.add("2");
		myList.add("3");
		myList.add("4");
		String str = myList.removeFirst();
		Assert.assertEquals("1", str);
		Assert.assertEquals("2", myList.get(0));
		System.out.println(myList);
	}
	
	@Test
	public void testRemove(){
		LinkedList<String> myList = new LinkedList<String>();
		myList.add("1");
		myList.add("2");
		myList.add("3");
		myList.add("4");
		String str = myList.remove(2);
		System.out.println(myList);
		Assert.assertEquals("3", str);
		str = myList.get(2);
		Assert.assertEquals("4", str);
		Assert.assertEquals(3, myList.size());
		System.out.println(myList);
	}
	
	@Test
	public void testRemoveAll(){
		LinkedList<String> myList = new LinkedList<String>();
		myList.add("1");
		myList.add("2");
		System.out.println(myList);
		String str = myList.removeFirst();
		System.out.println(myList);
		Assert.assertEquals("1", str);
		str = myList.removeFirst();
		Assert.assertEquals("2", str);
		Assert.assertEquals(0, myList.size());
		System.out.println(myList);
	}
	
	/**
	 * 把该链表逆置
	 * 例如链表为 3->7->10 , 逆置后变为  10->7->3
	 */
	@Test
	public void testReverse(){
		LinkedList<String> myList = new LinkedList<String>();
		myList.add("1");
		myList.add("3");
		myList.add("5");
		myList.add("7");
		myList.add("9");
		System.out.println(myList);
		myList.reverse();
		System.out.println(myList);
	}
	
	/**
	 * 删除一个单链表的前半部分
	 * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
	 * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10
	 */
	@Test
	public void testRemoveFirstHalf(){
		LinkedList<String> myList = new LinkedList<String>();
		myList.add("2");
		myList.add("5");
		myList.add("7");
		myList.add("8");
		myList.add("10");
		System.out.println(myList);
		myList.removeFirstHalf();
		System.out.println(myList);
	}
	
	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * 如果list = 2->5->7->8->10 ,remove(2,2)以后的值为2->5->10
	 * @param i
	 * @param length
	 */
	@Test
	public void testRemoveBySpecificLength(){
		LinkedList<String> myList = new LinkedList<String>();
		myList.add("2");
		myList.add("5");
		myList.add("7");
		myList.add("8");
		myList.add("10");
		System.out.println(myList);
		myList.remove(2,2);
		System.out.println(myList);
	}
	
	/**
	 * 假定当前链表和listB均包含已升序排列的整数
	 * 从当前链表中取出那些listB所指定的元素
	 * 例如当前链表 = 11->101->201->301->401->501->601->701
	 * listB = 1->3->4->6
	 * 返回的结果应该是[101,301,401,601]  
	 * @param list
	 */
	@Test
	public void testGetElements(){
		LinkedList<String> myList = new LinkedList<String>();
		myList.add("11");
		myList.add("101");
		myList.add("201");
		myList.add("301");
		myList.add("401");
		myList.add("501");
		myList.add("601");
		myList.add("701");
		System.out.println(myList);
		LinkedList testList = new LinkedList();
		testList.add(1);
		testList.add(3);
		testList.add(4);
		testList.add(6);
		System.out.println(Arrays.toString(myList.getElements(testList)));
	}
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 从当前链表中中删除在listB中出现的元素 
	 * 例如当前链表 = 11->101->201->301->401->501->601->701
	 * listB = [11,201,501,701]
	 * 返回的结果应该是[101,301,401,601]  
	 * @param list
	 */
	@Test
	public void subtract(){
		LinkedList<String> myList = new LinkedList<String>();
		myList.add("11");
		myList.add("101");
		myList.add("201");
		myList.add("301");
		myList.add("401");
		myList.add("501");
		myList.add("601");
		myList.add("701");
		System.out.println(myList);
		LinkedList testList = new LinkedList();
		testList.add(11);
		testList.add(201);
		testList.add(501);
		testList.add(701);
		myList.subtract(testList);
		System.out.println(myList);
	}
	
	/**
	 * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
	 * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
	 * 例如当前链表 = 11->101->201->301->401->501->601->701
	 * listB = [11,201,801,901]
	 * 返回的结果应该是[11,201]  
	 * @param list
	 */
	@Test
	public void intersection(){
		LinkedList<String> myList = new LinkedList<String>();
		myList.add("11");
		myList.add("101");
		myList.add("201");
		myList.add("301");
		myList.add("401");
		myList.add("501");
		myList.add("601");
		myList.add("701");
		System.out.println(myList);
		LinkedList testList = new LinkedList();
		testList.add(11);
		testList.add(201);
		testList.add(801);
		testList.add(901);
		System.out.println(myList.intersection(testList));
	}
}
