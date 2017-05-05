package com.LinkedList;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LinkedListUtilTest {
	LinkedListUtil<Integer> llu;
	@Before
	public void setUp() throws Exception {
		 llu = new LinkedListUtil<Integer>();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testReverse() {
		llu.reverse();
		assertArrayEquals(null, llu.toIntArray());
		llu.add(3);
		llu.add(7);
		llu.add(10);
		llu.reverse();
		assertArrayEquals(new int[]{10,7,3}, llu.toIntArray());
	}

	@Test
	public void testRemoveFirstHalf() {
		llu.removeFirstHalf();
		assertArrayEquals(null, llu.toIntArray());
		llu.add(2);
		llu.add(5);
		llu.add(7);
		llu.add(8);
		llu.removeFirstHalf();
		assertArrayEquals(new int[]{7,8}, llu.toIntArray());
		llu = new LinkedListUtil<Integer>();
		llu.add(2);
		llu.add(5);
		llu.add(7);
		llu.add(8);
		llu.add(10);
		llu.removeFirstHalf();
		assertArrayEquals(new int[]{7,8,10}, llu.toIntArray());
		
	}

	@Test
	public void testRemoveIntInt() {
		llu.add(2);
		llu.add(5);
		llu.add(7);
		llu.add(8);
		llu.remove(1, 2);
		assertArrayEquals(new int[]{2,8}, llu.toIntArray());
	}

	@Test
	public void testGetElements() {
		llu.add(11);
		llu.add(101);
		llu.add(201);
		llu.add(301);
		llu.add(401);
		llu.add(501);
		llu.add(601);
		llu.add(701);
		llu.add(801);
		LinkedList<Integer> l = new LinkedList<Integer>();
		l.add(1);
		l.add(3);
		l.add(4);
		l.add(6);
		assertArrayEquals(new int[]{101,301,401,601}, llu.getElements(l));
	}

	@Test
	public void testSubtract() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveDuplicateValues() {
		llu.add(1);
		llu.add(1);
		llu.add(2);
		llu.add(3);
		llu.add(3);
		llu.add(5);
		llu.add(6);
		llu.add(6);
		llu.add(8);
		llu.removeDuplicateValues();
		assertArrayEquals(new int[]{1,2,3,5,6,8}, llu.toIntArray());
		
	}

	@Test
	public void testRemoveRange() {
		llu.add(1);
		llu.add(1);
		llu.add(2);
		llu.add(3);
		llu.add(4);
		llu.add(5);
		llu.add(6);
		llu.add(6);
		llu.add(8);
		llu.removeRange(2, 5);
		assertArrayEquals(new int[]{1,1,2,5,6,6,8}, llu.toIntArray());
		llu = new LinkedListUtil<Integer>();
		llu.add(2);
		llu.add(2);
		llu.add(2);
		llu.add(3);
		llu.add(4);
		llu.add(5);
		llu.add(6);
		llu.add(6);
		llu.add(8);
		llu.removeRange(1, 5);
		assertArrayEquals(new int[]{5,6,6,8}, llu.toIntArray());
		
	}

	@Test
	public void testIntersection() {
		fail("Not yet implemented");
	}

}
