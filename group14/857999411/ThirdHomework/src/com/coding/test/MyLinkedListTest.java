package com.coding.test;

import org.junit.Test;

import com.coding.basic.MyLinkedList;

public class MyLinkedListTest {

	@Test
	public void reverseTest() {
		MyLinkedList ml =new MyLinkedList();
		
		ml.add("3");
		ml.add("7");
		ml.add("10");
		ml.add("11");
		ml.add("12");
		ml.add("13");
		
		ml.reverse();
		
		for(int i=0;i<ml.size;i++){
			System.out.println(ml.get(i));
		}
	}
	
	@Test
	public void removeFirstHalfTest(){
		MyLinkedList ml =new MyLinkedList();
		
		ml.add("2");
		ml.add("5");
		ml.add("7");
		ml.add("8");
		ml.add("10");
		
		ml.removeFirstHalf();
		
		for(int i=0;i<ml.size;i++){
			System.out.println(ml.get(i));
		}
	}
	
	@Test
	public void removeTest(){
		MyLinkedList ml =new MyLinkedList();
		
		ml.add("5");
		ml.add("2");
		ml.add("7");
		ml.add("8");
		ml.add("10");
		
		ml.remove(1,4);
		
		for(int i=0;i<ml.size;i++){
			System.out.println(ml.get(i));
		}
	}
	
	@Test
		public void getElementsTest(){
			MyLinkedList ml= new MyLinkedList();
			//11->101->201->301->401->501->601->701
			ml.add(11);
			ml.add(101);
			ml.add(201);
			ml.add(301);
			ml.add(401);
			ml.add(501);
			ml.add(601);
			ml.add(701);
			
			MyLinkedList list=new MyLinkedList();
			//1->3->4->6
			list.add(1);
			list.add(3);
			list.add(4);
			list.add(6);
			
			int[] elements = ml.getElements(list);
			
			for (int i : elements) {
				System.out.println(i);
			}
		}
	@Test
	public void subtractTest(){
		MyLinkedList ml= new MyLinkedList();
		//11->101->201->301->401->501->601->701
		ml.add(11);
		ml.add(101);
		ml.add(201);
		ml.add(301);
		ml.add(401);
		ml.add(501);
		ml.add(601);
		ml.add(701);
		
		MyLinkedList list=new MyLinkedList();
		//1->3->4->6
		list.add(1);
		list.add(3);
		list.add(4);
		list.add(6);
		
		ml.subtract(list);
		for(int i=0;i<ml.size;i++){
			System.out.println(ml.get(i));
		}
	}
	@Test
	public void removeDuplicateValuesTest(){
		MyLinkedList ml= new MyLinkedList();
			ml.add(11);
			ml.add(101);
			ml.add(201);
			ml.add(201);
			ml.add(301);
			ml.add(401);
			ml.add(401);
			ml.add(501);
			ml.add(601);
			ml.add(601);
			ml.add(701);
			
			ml.removeDuplicateValues();
			
			for(int i=0;i<ml.size;i++){
				System.out.println(ml.get(i));
			}
		}
	@Test
	public void removeRangeTest(){
		MyLinkedList ml= new MyLinkedList();
			ml.add(11);
			ml.add(101);
			ml.add(201);
			ml.add(301);
			ml.add(401);
			ml.add(501);
			ml.add(601);
			ml.add(701);
			
			ml.removeRange(101, 701);
			
			for(int i=0;i<ml.size;i++){
				System.out.println(ml.get(i));
			}
	}
	@Test
	public void intersectionTest(){
		MyLinkedList ml= new MyLinkedList();
		ml.add(11);
		ml.add(101);
		ml.add(201);
		ml.add(301);
		ml.add(401);
		ml.add(501);
		ml.add(601);
		ml.add(701);
		
		MyLinkedList list=new MyLinkedList();
		list.add(11);
		list.add(201);
		list.add(301);
		list.add(401);
		list.add(601);
		
		MyLinkedList in = ml.intersection(list);
		
		for(int i=0;i<in.size;i++){
			System.out.println(in.get(i));
		}
	}
}

	