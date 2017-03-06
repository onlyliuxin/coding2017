package dataStructure;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by LvZhenxing on 2017/2/22.
 */
public class LinkedListTest {

	LinkedList list = null;

	@Before
	public void setUp() throws Exception {
		list = new LinkedList();
		for (int i = 0; i < 6; i++) {
			list.add(i);
		}
		System.out.println("=============================before==============================");
		for (int i = 0; i < list.size(); i++) {
			try {
				System.out.println("index=" + i + ",data=" + list.get(i) + ",next=" + list.get(i + 1));
			} catch (Exception e) {
				System.out.println("index=" + i + ",data=" + list.get(i) + ",next=null");
			} finally {

			}
		}
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("=============================after==============================");
		for (int i = 0; i < list.size(); i++) {
			try {
				System.out.println("index=" + i + ",data=" + list.get(i) + ",next=" + list.get(i + 1));
			} catch (Exception e) {
				System.out.println("index=" + i + ",data=" + list.get(i) + ",next=null");
			} finally {

			}

		}
	}

	@Test
	public void testAdd() throws Exception {
		list.add(300);
	}

	@Test
	public void testAdd1() throws Exception {
		list.add(2, 100);
	}

	@Test
	public void testGet() throws Exception {

	}

	@Test
	public void testRemove() throws Exception {
		list.remove(3);
	}

	@Test
	public void testSize() throws Exception {

	}

	@Test
	public void testAddFirst() throws Exception {
		list.addFirst(66);
	}

	@Test
	public void testAddLast() throws Exception {
		list.addLast(77);
	}

	@Test
	public void testRemoveFirst() throws Exception {
		list.removeFirst();
	}

	@Test
	public void testRemoveLast() throws Exception {
		list.removeLast();
	}

	@Test
	public void testIterator() throws Exception {
		Iterator iterator = list.iterator();
		while (iterator.hasNext()) {
			System.out.print(iterator.next() + ",");
		}

		System.out.println();
		System.out.println("---------------------------");
	}
}