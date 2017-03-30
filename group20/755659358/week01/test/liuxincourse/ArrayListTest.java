package liuxincourse;

import static org.junit.Assert.*;

import org.junit.Test;

public class ArrayListTest {

	
	@Test
	public void testAdd(){
		ArrayList list=new ArrayList();
		list.add(12);
		list.add(2);
		list.add(8);
		assertEquals(8, list.get(2));
	}
	
	@Test
	public void testAddIndex(){
		ArrayList list=new ArrayList();
		list.add(12);
		list.add(2);
		list.add(8);
		list.add(1,33);
		list.add(6);
		list.add(7);
		assertEquals(8, list.get(3));
	}
	
	@Test
	public void testRemove(){
		ArrayList list=new ArrayList();
		list.add(12);
		list.add(2);
		list.add(8);
		list.add(1,33);
		list.remove(1);
		assertEquals(2, list.get(1));
	}
	
	@Test
	public void testSize(){
		ArrayList list=new ArrayList();
		list.add(12);
		list.add(2);
		list.add(8);
		list.add(9);
		list.add(77);
		assertEquals(5, list.size());
	}

}
