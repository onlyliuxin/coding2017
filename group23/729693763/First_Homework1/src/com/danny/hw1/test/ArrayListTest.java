package com.danny.hw1.test;

import static org.junit.Assert.*;

import java.lang.reflect.Array;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.Collection;

import javax.print.attribute.standard.RequestingUserName;
import javax.xml.crypto.Data;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.danny.hw1.ArrayList;
import com.danny.hw1.Iterator;
import com.danny.hw1.test_al;

import junit.extensions.TestSetup;


public class ArrayListTest {

	static Object[] Data = new Object[]{1,2,3,4,5,6,7,8};
	ArrayList test;
	@Before
	public void setUp() throws Exception{
		test = new ArrayList();
		for(Object data: Data){
			test.add(data);
		}
	}
	
	@Test
	public void testAddObject() {
		int len = test.size();
		test.add(10);
		assertEquals(len, test.size()-1);
	}
	
	@Test
	public void testAddIntObject() {
		int len = test.size();
		test.add(len, 10);
		
		assertEquals(len, test.size()-1);
	}

	@Test
	public void testGet() {
		assertEquals(Data[3], test.get(3));
	}

	@Test
	public void testRemove() {
		assertEquals(Data[4], test.remove(4));
		assertEquals(Data.length-1, test.size());
	}

	@Test
	public void testSize() {
		assertEquals(Data.length, test.size());
	}

	@Test
	public void testIterator() {
		Iterator iterator =test.iterator();
		for(Object i:Data){
			if(iterator.hasNext()){
				assertEquals(i,iterator.next());
			}
		}
	}
	

}
