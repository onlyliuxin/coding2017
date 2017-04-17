/**
 * 
 */
package com.coderising.array;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Administrator
 *
 */
public class ArrayListTest {

	private ArrayList al;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		System.out.println("SetUp");
		al= new ArrayList();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		System.out.println("TearDown");
		al = null;
	}

	/**
	 * Test method for {@link com.coderising.array.ArrayList#add(java.lang.Object)}.
	 */
	@Test
	public final void testAddObject() {
		al.add("aaa");
		al.add("bbb");
		al.add("ccc");
		assertEquals("aaa",al.get(0));
		assertEquals("bbb",al.get(1));
		assertEquals("ccc",al.get(2));
		assertEquals(3,al.size());
	}

	/**
	 * Test method for {@link com.coderising.array.ArrayList#add(int, java.lang.Object)}.
	 */
	@Test
	public final void testAddIntObject() {
		al.add("aaa");
		al.add(0,"bbb");
		al.add(1,"ccc");
		assertEquals("bbb",al.get(0));
		assertEquals("ccc",al.get(1));
		assertEquals("aaa",al.get(2));
		assertEquals(3,al.size());
	}
	/**
	 * Test method for {@link com.coderising.array.ArrayList#add(int, java.lang.Object)}.
	 */
	@Test(expected=IndexOutOfBoundsException.class)
	public final void testAddIntObjectWithException1() {
		al.add(-1, "aaa");
	}
	/**
	 * Test method for {@link com.coderising.array.ArrayList#add(int, java.lang.Object)}.
	 */
	@Test(expected=IndexOutOfBoundsException.class)
	public final void testAddIntObjectWithException2() {
		al.add("aaa");
		al.add(1,"bbb");
	}

	/**
	 * Test method for {@link com.coderising.array.ArrayList#get(int)}.
	 */
	@Test
	public final void testGet() {
		fail("Not yet implemented"); // TODO
	}
	/**
	 * Test method for {@link com.coderising.array.ArrayList#get(int)}.
	 */
	@Test
	public final void testGetWithException1() {
		fail("Not yet implemented"); // TODO
	}
	/**
	 * Test method for {@link com.coderising.array.ArrayList#get(int)}.
	 */
	@Test
	public final void testGetWithException2() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link com.coderising.array.ArrayList#remove(int)}.
	 */
	@Test
	public final void testRemove() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link com.coderising.array.ArrayList#size()}.
	 */
	@Test
	public final void testSize() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link com.coderising.array.ArrayList#iterator()}.
	 */
	@Test
	public final void testIterator() {
		fail("Not yet implemented"); // TODO
	}

}
