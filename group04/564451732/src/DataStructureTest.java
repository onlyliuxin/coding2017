import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import hw1.ArrayListImpl;
import hw1.BinaryTreeNode;
import hw1.LinkedList;
import hw1.QueueImpl;
import hw1.StackImpl;

public class DataStructureTest {

	@Test
	public void ArrayListTest() {
		ArrayListImpl al = new ArrayListImpl();
		Object o1 = new Object();
		Object o2 = new Object();
		Object o3 = new Object();
		al.add(new Object());
		al.add(new Object());
		assertTrue(al.size() == 2);
		al.add(5);
		assertFalse(al.size() == 2);
		assertTrue(al.size() == 3);
		al.add(2, 3);
		//System.out.println((int)al.get(2));
		assertTrue((int)al.get(2) == 3);
		assertTrue((int)al.get(3) == 5);
	}
	
	@Test
	public void LinkedListTest() {
		LinkedList ll = new LinkedList();
		Object o1 = new Object();
		Object o2 = new Object();
		Object o3 = new Object();
		Object o4 = new Object();
		Object o5 = new Object();
		Object o6 = new Object();
		ll.add(o1);
		ll.add(o2);
		//System.out.println(ll.size());
		assertTrue(ll.size() == 2);
		ll.add(o3);
		ll.add(1,1);
		assertTrue((int)ll.get(1) == 1);
		assertTrue(ll.size() == 4);
		ll.addFirst(2);
		ll.addLast(4);
		assertTrue((int)ll.get(0) == 2);
		assertTrue((int)ll.get(ll.size() - 1) == 4);
		assertTrue((int)ll.get(2) == 1);
		ll.remove(2);
		assertTrue(ll.get(2) == o2);
		//System.out.print((int)ll.remove(2));
		//System.out.println((int)ll.get(2));
		//assertFalse((int)ll.get(2) == 1);
		assertTrue(ll.size() == 5);
		assertTrue((int)ll.removeFirst() == 2);
		assertTrue(ll.size() == 4);
		assertTrue((int)ll.removeLast() == 4);
		assertTrue(ll.size() == 3);
	}
	
	@Test
	public void QueueTest(){
		QueueImpl qi = new QueueImpl();
		assertTrue(qi.isEmpty());
		qi.enQueue(1);
		qi.enQueue(2);
		qi.enQueue(3);
		assertTrue(qi.size() == 3);
		assertTrue((int)qi.deQueue() == 1);
		assertTrue(qi.size() == 2);
		assertFalse(qi.isEmpty());
	}
	
	@Test
	public void StackTest() {
		StackImpl stack = new StackImpl();
		assertTrue(stack.isEmpty());
		stack.push(1);
		stack.push(2);
		stack.push(3);
		assertTrue(stack.size() == 3);
		assertTrue((int)stack.pop() == 3);
		assertTrue(stack.size() == 2);
		assertTrue((int)stack.peek() == 2);
		assertFalse(stack.isEmpty());
	}
	
	@Test
	public void BinaryTreeTest() {
		BinaryTreeNode bt = new BinaryTreeNode();
		Integer i1 = 1;
		Integer i2 = 3;
		Integer i3 = 4;
		Integer i4 = 6;
		Integer i5 = -1;
		bt.insert(i3);
		bt.insert(i1);
		bt.insert(i4);
		bt.insert(i2);
		bt.insert(i5);
		assertTrue((int)bt.getRoot().getData() == 4);
		assertTrue((int)bt.getRoot().getLeft().getData() == 1);
		assertTrue((int)bt.getRoot().getRight().getData() == 6);
		assertTrue((int)bt.getRoot().getLeft().getLeft().getData() == -1);
		assertTrue((int)bt.getRoot().getLeft().getRight().getData() == 3);
		
	}

}
