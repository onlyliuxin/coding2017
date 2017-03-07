package test.collection;

import static util.Print.*;
import static util.TestUtil.*;

import java.util.Arrays;
import java.util.Date;
import java.util.NoSuchElementException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import collection.Iterator;
import collection.List;
import collection.concrete.LinkedList;
import junit.framework.TestCase;


public class LinkedListTest2 extends TestCase {

	LinkedList<Integer> myLL;
	
	@Before
	public void setUp() throws Exception {
		myLL = new LinkedList<Integer>();
		assertEquals(0,myLL.size());
	}

	@After
	public void tearDown() throws Exception {
		myLL = null;
	}
	

	
	@Test
	public void testReverse() {
		addIntWithNatureOrder(myLL,5);
		myLL.reverse();
		for (int i = 0; i < 5; i++) {
			int acutal = myLL.get(i);
			assertEquals(4-i,acutal);
		}
	}
	
	
	@Test
	public void testRemoveFirstHalf() {
		addIntWithNatureOrder(myLL,5);
		myLL.removeFirstHalf();
		assertEquals(3,myLL.size());
		assertEquals(2,(int)myLL.get(0));
		assertEquals(3,(int)myLL.get(1));
		assertEquals(4,(int)myLL.get(2));
	}
	
	@Test
	public void testRemove2() {
		addIntWithNatureOrder(myLL,5);
		myLL.remove(1,2);
		assertEquals(3,myLL.size());
		assertEquals(0,(int)myLL.get(0));
		assertEquals(3,(int)myLL.get(1));
		assertEquals(4,(int)myLL.get(2));
	}
	
	@Test
	public void testGetElements() {
		addIntWithNatureOrder(myLL,10);
		LinkedList<Integer> list = new LinkedList<Integer>();
		list.add(0);
		list.add(2);
		list.add(7);
		int[] result =  myLL.getElements(list);	
		for (int i = 0; i < result.length; i++) {
			int expected = list.get(i);
			int actual = result[i];
			assertEquals(expected,actual);
		}

	}
	
	@Test
	public void testSubstract() {
		LinkedList<Integer> myLL = new LinkedList<Integer>();
		addIntWithNatureOrder(myLL,10);
		myLL.add(10);
		myLL.add(10);
		myLL.add(12);
		LinkedList<Integer> list = new LinkedList<Integer>();
		list.add(0);
		list.add(0);
		addIntWithNatureOrder(list,10);
		list.add(10);
		list.add(12);
		list.add(22);
		myLL.subtract(list);
		assertEquals(0,myLL.size());
	}
	
	@Test
	public void testIntersection() {

		addIntWithNatureOrder(myLL,10);
		myLL.add(10);
		myLL.add(12);
		myLL.add(13);
		myLL.add(24);
		LinkedList<Integer> list = new LinkedList<Integer>();
		list.add(0);
		list.add(5);
		list.add(10);
		LinkedList<Integer> result = myLL.intersection(list);
		assertEquals(0,(int)result.get(0));
		assertEquals(5,(int)result.get(1));
		assertEquals(10,(int)result.get(2));

	}
	
	
	@Test
	public void testRemoveDuplicateValues() {

		myLL.add(0);
		myLL.add(0);
		myLL.add(1);
		myLL.add(1);
		myLL.add(10);
		myLL.removeDuplicateValues();
		assertEquals(3,myLL.size());
		assertEquals(0,(int)myLL.get(0));
		assertEquals(1,(int)myLL.get(1));
		assertEquals(10,(int)myLL.get(2));
	}

	
	
	
}
