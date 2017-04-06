package testcase;

import static org.junit.Assert.*;

import com.basic.ArrayList;
import com.basic.Iterator;

import org.junit.Test;

public class TestArrayList
{

	@Test
	public void testAdd() {
		ArrayList list = new ArrayList();
		try {
			list.add(3);
		} catch (ArrayIndexOutOfBoundsException e) {
			assertNull(e);
		}
		assertTrue(list.get(0).equals(3));
		try {
			for (int i = 0; i < 100; i++) {
				list.add(i);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			assertNotNull(e);
			// System.out.println(e.getMessage());
			assertEquals("100", e.getMessage());
		}

	}

	/*
	 * test add(index, o)
	 */
	@Test
	public void testIndexAdd() {
		ArrayList list = new ArrayList();
		try {
			for (int i = 0; i < 10; i++) {
				list.add(i);
			}
			list.add(3, 20);
			list.add(0, 30);

		} catch (ArrayIndexOutOfBoundsException e) {
			assertNull(e);
		}

		// Iterator it = list.iterator();
		// for ( ; it.hasNext(); ) {
		// System.out.print(it.next() + " ");
		// }
		// System.out.println();

		assertTrue(list.get(0).equals(30));
		assertTrue(list.get(4).equals(20));
		assertTrue(list.get(5).equals(3));

		try {
			for (int i = 0; i < 100; i++) {
				list.add(i, i);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			assertNotNull(e);
			// System.out.println(e.getMessage());
			assertEquals("100", e.getMessage());
		}
	}

	/*
	 * test get(index)
	 */
	@Test
	public void testGet() {
		ArrayList list = new ArrayList();
		try {
			for (int i = 0; i < 10; i++) {
				list.add(i);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			assertNull(e);
		}
		try {
			list.get(0);
			list.get(5);
		} catch (ArrayIndexOutOfBoundsException e) {
			assertNull(e);
		}
		try {
			list.get(10);
		} catch (ArrayIndexOutOfBoundsException e) {
			assertNotNull(e);
			// System.out.println(e.getMessage());
			// System.out.println(list.size());
			String errorInfo = "Index to get: 10 is invalid, current range: 0 - "
					+ (list.size() - 1);
			assertEquals(errorInfo, e.getMessage());
		}
	}

	/*
	 * test remove(index) and size
	 */
	@Test
	public void testRemove() {
		ArrayList list = new ArrayList();
		try {
			for (int i = 0; i < 10; i++) {
				list.add(i);
			}
			assertTrue(list.size() == 10);
			list.remove(3);
			assertTrue(list.size() == 9);
		} catch (ArrayIndexOutOfBoundsException e) {
			assertNull(e);
		}
		assertFalse(list.get(3).equals(3));
		assertTrue(list.get(3).equals(4));

		try {
			list.remove(-3);
		} catch (ArrayIndexOutOfBoundsException e) {
			assertNotNull(e);
			// System.out.println(e.getMessage());
		}

		try {
			list.remove(20);
		} catch (ArrayIndexOutOfBoundsException e) {
			assertNotNull(e);
			// System.out.println(e.getMessage());
		}
	}

	@Test
	public void testInterator() {
		ArrayList list = new ArrayList();
		try {
			for (int i = 0; i < 10; i++) {
				list.add(i);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			assertNull(e);
		}

		Iterator it = list.iterator();
		assertTrue(it.hasNext());
		assertTrue(it.next().equals(0));
		assertTrue(it.next().equals(1));
		assertTrue(it.next().equals(2));
	}

}
