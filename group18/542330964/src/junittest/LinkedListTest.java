package junittest;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import basicstruct.LinkedList;

public class LinkedListTest {

	LinkedList l = new LinkedList();
	@Before
	public void setUp() throws Exception {
		l.add(1);
		l.add("2");
		l.add(new Date());
		l.add(22);
		l.add(33);
		l.add(1, 3);
	}

	@Test
	public void testAddObject() {
		System.out.println(l.get(1));
		System.out.println(l.get(3));
		System.out.println(l.get(200));
	}

	@Test
	public void testRemove() {
		l.remove(1);
		System.out.println(l.get(1));
	}

	@Test
	public void testSize() {
		System.out.println(l.size());
	}

	@Test
	public void testAddFirst() {
		l.addFirst(0);
		System.out.println(l.get(0));
	}

	@Test
	public void testAddLast() {
		l.addLast(999);
		System.out.println(l.get(l.size()-1));
	}

	@Test
	public void testRemoveFirst() {
		l.removeFirst();
		System.out.println(l.get(0));
	}

	@Test
	public void testRemoveLast() {
		l.removeLast();
		System.out.println(l.get(l.size()-1));
	}

}
