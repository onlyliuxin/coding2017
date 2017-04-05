package main.week01.data_structure;

import static org.junit.Assert.*;
import main.week01.data_structure.ArrayList.ArrayListIterator;

import org.junit.Before;
import org.junit.Test;

public class ArrayListTest {

	public static ArrayList list; 
	
	@Before
	public void setUp() throws Exception {
		list = new ArrayList();
	}

	@Test
	public void testAddObject() {
		list.add(1);
		list.add(2);
		list.add(2);
		assertEquals(3,list.size());
	}

	@Test
	public void testAddIntObject() {
		list.add(0,1);
		list.add(1,2);
		list.add(2,2);
		list.add(0,2);
		assertEquals(2,list.get(0));
		try{
			list.add(-1 , "test");
			fail("-1 can't be index");
			list.add(1000, "test");
			fail("out of range");
		}catch (Exception e){
			
		}
	}

	@Test
	public void testGet() {
		list.add("songhao");
		assertEquals("songhao", list.get(0));
	}

	@Test
	public void testRemove() {
		list.add("songhao");
		assertEquals("songhao", list.remove(0));
	}

	@Test
	public void testSize(){
		list.add(0,1);
		list.add(1,2);
		list.add(2,2);
		list.add(0,2);
		assertEquals(4,list.size());
	}
	
	@Test
	public void testIterator() {
		list.add(0,1);
		list.add(1,2);
		list.add(2,3);
		list.add(0,4);
		ArrayListIterator iter = list.iterator();
		assertTrue(iter.hasNext());
		assertEquals(4, iter.next());
		assertTrue(iter.hasNext());
		assertEquals(1, iter.next());
		assertTrue(iter.hasNext());
		assertEquals(2, iter.next());
  		assertTrue(iter.hasNext());
  		assertEquals(3, iter.next());
  		assertFalse(iter.hasNext());
  		
	}

}
