package com.coding.basic.ut;

import static org.junit.Assert.*;

import java.lang.reflect.Field;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.coding.basic.ArrayList;

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

	@After
	public void tearDown() throws Exception {
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
		//fail("Not yet implemented");
	}

	@Test
	public void testRemove() {
		//fail("Not yet implemented");
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
		//fail("Not yet implemented");
	}

}
