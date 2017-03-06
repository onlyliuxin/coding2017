package test_data_structure;



import java.util.Iterator;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import data_structure.MyArrayList;

public class TestMyArrayList {
	MyArrayList list;

	@Before
	public void setUp() throws Exception {
		list=new MyArrayList();
		System.out.println("begin");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("end");
	}

	@Test
	public void testMyArrayList() {
	
		Assert.assertEquals(0, list.Capacity());
	}

	@Test
	public void testAddObject() {
		list.add(new Integer(10));
		Assert.assertEquals(10, list.get(0));
	}

	@Test
	public void testAddIntObject() {
		list.add(1);
		list.add(1);
		list.add(1);
		list.add(1,2);
		Assert.assertEquals(2, list.get(1));
		
		
	}

	@Test
	public void testGet() {
		list.add(1);
		list.add(2);
		Assert.assertEquals(2, list.get(1));
	}

	@Test
	public void testRemove() {
		list.add(1);
		list.add(2);
		list.add(3);
		Assert.assertEquals(2, list.remove(1));
		
	}

	@Test
	public void testSize() {
		list.add(1);
		list.add(2);
		list.add(3);
		Assert.assertEquals(3, list.size());
	}

	@Test
	public void testCapacity() {
		list=new MyArrayList(5);
		Assert.assertEquals(5, list.Capacity());
		
	}
	@Test
	public void testIterator(){
		list.add(1);
		list.add(2);
		list.add(3);
		Iterator<Object> itr=list.iterator();
		while(itr.hasNext()){
			System.out.println(itr.next());
		}
		
	}

}
