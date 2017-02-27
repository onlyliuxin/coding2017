package com.coding.basic.ut;

import static org.junit.Assert.*;

import java.lang.reflect.Field;

import org.junit.Before;
import org.junit.Test;

import com.coding.basic.ArrayList;
import com.coding.basic.Iterator;

public class ArrayListTest {

	ArrayList target;
	Object[] elementDataField;
	@Before
	public void setUp() throws Exception {
		target = new ArrayList();
		/*
		 * NOTE: getField only returns PUBLIC fields, getDeclaredField get all fields
			Field[] allFields = target.getClass().getFields();
			System.out.println("Get all fields");
			for(Field tmpf : allFields){
				System.out.println(tmpf.getName());
			}
			Field[] allDeclFields = target.getClass().getDeclaredFields();
			System.out.println("Get all declared fields");
			for(Field tmpf : allDeclFields){
				System.out.println(tmpf.getName());
			}
		 * 
		 */
		try {
			Field f = target.getClass().getDeclaredField("elementData");

			f.setAccessible(true);
			if(f.getType().isArray()){
				elementDataField = (Object[]) f.get(target);
			}			
		} catch (NoSuchFieldException e) {
			fail(e.getMessage());
		} catch (SecurityException e) {
			fail(e.getMessage());
		} catch (IllegalArgumentException e) {
			fail(e.getMessage());
		} catch (IllegalAccessException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testAddObject() {
		Object item = new String("s1");
		target.add(item);
		assertEquals(item, elementDataField[0]);
	}

	@Test
	public void testAddIndexObject() {
		//fail("Not yet implemented");
		Object item0 = new String("s0");
		target.add(0, item0);
		
		Object item1 = new String("s1");
		target.add(0, item1);
		
		assertEquals(item1, elementDataField[0]);
		assertEquals(item0, elementDataField[1]);
	}

	@Test
	public void testGet() {
		target.add("0");
		Object o = target.get(0);
		assertEquals("0", o);
		assertNotEquals("2", 0);
	}

	@Test
	public void testRemove() {
		String[] items = new String[]{"0","1","2"};
		for(int i = 0; i<items.length; i++){
			target.add(items[i]);
		}
		Object o = target.remove(1);
		assertEquals(o,"1");
		o = target.remove(1);
		assertEquals(o, "2");
		o = target.remove(0);
		assertEquals(o, "0");
		assertEquals(0, target.size());
	}

	@Test
	public void testSize() {
		for(int i = 0; i<10; i++){
			Object item = String.valueOf(i);
			target.add(item);
		}
		assertEquals(10, target.size());
	}

	@Test
	public void testIterator() {
		ArrayList al = new ArrayList();
		String[] items = new String[]{"0","1","2","3","4"};
		for(int i = 0 ; i< items.length; i++){
			al.add(items[i] );
		}
		int count = 0;
		Iterator itr = al.iterator();
		while(itr.hasNext()){
			Object o = itr.next();
			assertEquals(items[count], o);
			count++;
		}
		
	}

}
