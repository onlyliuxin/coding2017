package week1.collection.test;

import static org.junit.Assert.*;

import org.junit.Test;

import week1.collection.ArrayList;
import week1.collection.Iterator;

public class ArrayListTest {

	private ArrayList list=new ArrayList();
	
	@Test
	public void testAddObject(){
		list.add(1);
		assertEquals(1 , list.get(0));
	}
	
	@Test
	public void testAddIndexObject(){
		list.add("aa");
		list.add("bb");
		list.add(0,"cc");
		assertEquals("cc",list.get(0));
		try{
			list.add(-1,"pp");
			fail("- can't be index");
			
			list.add(list.size()+100,"bb");
			fail("index should <= size");
			
		}catch(Exception ex){
			
		}
	}
	
	@Test
	public void testGetObject(){
		list.add(1);
		assertEquals(1,list.get(0));
	}
	
	@Test
	public void testRemoveObject(){
		list.add(1);
		list.add(2);
		list.add(3);
		list.remove(0);
		list.remove(2);
		assertEquals(2,list.get(0));
	}
	
	@Test
	public void testSize(){
		assertEquals(0,list.size());
	}
	
	@Test
	public void testIterator(){
		list.add(1);
		list.add(2);
		list.add(3);
		Iterator it=list.iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
	}
}
