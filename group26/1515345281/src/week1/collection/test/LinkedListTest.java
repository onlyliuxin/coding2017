package week1.collection.test;

import static org.junit.Assert.*;

import org.junit.Test;

import week1.collection.Iterator;
import week1.collection.LinkedList;

public class LinkedListTest {

	private LinkedList list=new LinkedList();
	
	@Test
	public void testAdd(){
		list.add("1");
		list.add("2");
		list.add("3");
		assertEquals("1",list.get(0));
		assertEquals("2",list.get(1));
		assertEquals(3,list.size());
	}
	
	@Test
	public void testAddByIndex(){
		list.add(2);
		list.add(4);
		list.add(6);
		list.add(0,0);
		list.add(3,3);
		list.add(5,7);
		assertEquals(0, list.get(0));
		assertEquals(3, list.get(3));
		assertEquals(7, list.get(5));
		try{
			list.add(-1,0);
			fail("-1 not a correctly index");
		}catch(Exception ex){
			
		}
	}
	
	@Test
	public void testGet(){
		list.add(0);
		list.add(1);
		list.add(2);
		assertEquals(0,list.get(0));
	}
	
	@Test
	public void testRemove(){
		list.add(0);
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		assertEquals(0,list.remove(0));
		assertEquals(4,list.remove(3));
		assertEquals(2,list.remove(1));
	}
	
	@Test
	public void testSize(){
		list.add(0);
		list.addLast(0);
		list.addFirst(0);
		list.remove(0);
		list.removeLast();
		list.removeFirst();
		assertEquals(0,list.size());
	}
	
	@Test
	public void testOther(){
		list.add(1);
		list.add(1);
		list.add(1);
		list.add(1);
		list.addFirst(0);
		list.addLast(2);
		list.removeFirst();
		list.removeLast();
		Iterator it=list.iterator();
		while(it.hasNext()){
			System.out.print(it.next()+"　");
		}
	}
	
}
