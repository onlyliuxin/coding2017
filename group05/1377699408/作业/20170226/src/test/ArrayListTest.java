package test;

import junit.framework.TestCase;
import list.ArrayList;

public class ArrayListTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testArrayList() {
		ArrayList<Integer> a = new ArrayList<Integer>();
		assertEquals(a.size(), 0);
	}

	public void testArrayListInt() {
		ArrayList<Integer> a = new ArrayList<Integer>(9);
		assertEquals(a.size(), 9);
	}

	public void testSize() {
		ArrayList<Integer> a = new ArrayList<Integer>(9);
		assertEquals(a.size(), 9);
		ArrayList<Integer> a2 = new ArrayList<Integer>(100);
		assertEquals(a2.size(), 100);
	}

	public void testAdd() {
		ArrayList<Integer> a = new ArrayList<Integer>();
		for (int i = 0; i < 1000; i++) {
			a.add(5);
			assertEquals(a.size(), i+1);
			assertEquals(a.get(i), new Integer(5));
		}
	}

	public void testGet() {
		ArrayList<Integer> a = new ArrayList<Integer>();
		a.add(6);
		assertEquals(a.get(0), new Integer(6));
		
	}

	public void testSet() {
		ArrayList<Integer> a = new ArrayList<Integer>();
		for (int i = 0; i < 100; i++) {
			a.add(56);
		}
		a.set(5, 66);
		assertEquals(a.get(5), new Integer(66));
		assertEquals(a.get(7), new Integer(56));
	}

}
