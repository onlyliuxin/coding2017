package structure.week3;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LinkedListTest {
	LinkedList list = null;
	@Before
	public void setUp() throws Exception {
		list = new LinkedList();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testReverse() {
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        LinkedList test = new LinkedList();
        test.add(4);
        test.add(3);
        test.add(2);
        test.add(1);
        list.reverse();
        Assert.assertEquals(test.get(0), list.get(0));
        Assert.assertEquals(test.get(1), list.get(1));
        Assert.assertEquals(test.get(2), list.get(2));
        Assert.assertEquals(test.get(3), list.get(3));
	}

	@Test
	public void testRemoveFirstHalf() {        
		list.add(1);
	    list.add(2);
	    list.add(3);
	    list.add(4);
	    list.add(5);
	    list.add(6);
	    list.add(7);
	    LinkedList test = new LinkedList();
	    test.add(4);
	    test.add(5);
	    test.add(6);
	    test.add(7);
	    list.removeFirstHalf();
	    Assert.assertEquals(test.get(0), list.get(0));
	    Assert.assertEquals(test.get(1), list.get(1));
	    Assert.assertEquals(test.get(2), list.get(2));
	    Assert.assertEquals(test.get(3), list.get(3));
	    list.removeFirstHalf();
	    Assert.assertEquals(test.get(2), list.get(0));
	    Assert.assertEquals(test.get(3), list.get(1));
	    list.removeFirstHalf();
	    Assert.assertEquals(test.get(3), list.get(0));
	    list.removeFirstHalf();
	    Assert.assertEquals(test.get(3), list.get(0));
	}
    @Test
    public void testRemove(){
		list.add(1);
	    list.add(2);
	    list.add(3);
	    list.add(4);
	    list.add(5);
	    list.add(6);
	    list.add(7);
	    LinkedList test = new LinkedList();
	    test.add(1);
	    test.add(4);
	    test.add(5);
	    test.add(6);
	    test.add(7);
	    list.remove(1, 2);
	    Assert.assertEquals(test.get(0), list.get(0));
	    Assert.assertEquals(test.get(1), list.get(1));
	    Assert.assertEquals(test.get(2), list.get(2));
	    Assert.assertEquals(test.get(3), list.get(3));
	    Assert.assertEquals(test.get(4), list.get(4));
	    list.remove(1, 0);
	    Assert.assertEquals(test.get(0), list.get(0));
	    Assert.assertEquals(test.get(1), list.get(1));
	    Assert.assertEquals(test.get(2), list.get(2));
	    Assert.assertEquals(test.get(3), list.get(3));
	    Assert.assertEquals(test.get(4), list.get(4));
	    list.remove(0, 2);
	    Assert.assertEquals(test.get(2), list.get(0));
	    Assert.assertEquals(test.get(3), list.get(1));
	    Assert.assertEquals(test.get(4), list.get(2));
    }
    @Test
    public void testGetElements(){
		list.add(1);
	    list.add(2);
	    list.add(3);
	    list.add(4);
	    list.add(5);
	    list.add(6);
	    list.add(7);
	    LinkedList sel = new LinkedList();
	    sel.add(2);
	    sel.add(4);
	    sel.add(6);
	    int[] res = list.getElements(sel);
	    Assert.assertEquals(res[0], 3);
	    Assert.assertEquals(res[1], 5);
	    Assert.assertEquals(res[2], 7);
    }
	@Test
	public void testSubtract() {
		list.add(1);
		list.add(1);
	    list.add(2);
	    list.add(2);
	    list.add(3);
	    list.add(4);
	    list.add(5);
	    list.add(6);
	    list.add(7);
	    list.add(8);
	    list.add(8);
	    LinkedList sub =new LinkedList();
		sub.add(1);
	    sub.add(7);
	    sub.add(8);
	    LinkedList test =new LinkedList();
	    test.add(2);
	    test.add(2);
	    test.add(3);
	    test.add(4);
	    test.add(5);
	    test.add(6);
	    list.subtract(sub);
	    Assert.assertEquals(test.get(0), list.get(0));
	    Assert.assertEquals(test.get(1), list.get(1));
	    Assert.assertEquals(test.get(2), list.get(2));
	    Assert.assertEquals(test.get(3), list.get(3));
	    Assert.assertEquals(test.get(4), list.get(4));
	    Assert.assertEquals(test.get(5), list.get(5));
	}

	@Test
	public void testRemoveDuplicateValues() {
		list.add(1);
		list.add(1);
	    list.add(2);
	    list.add(3);
	    list.add(4);
	    list.add(4);
	    list.add(5);
	    list.add(6);
	    list.add(6);
	    list.add(7);
	    list.add(7);
	    LinkedList test =new LinkedList();
		test.add(1);
	    test.add(2);
	    test.add(3);
	    test.add(4);
	    test.add(5);
	    test.add(6);
	    test.add(7);
	    list.removeDuplicateValues();
	    Assert.assertEquals(test.get(0), list.get(0));
	    Assert.assertEquals(test.get(1), list.get(1));
	    Assert.assertEquals(test.get(2), list.get(2));
	    Assert.assertEquals(test.get(3), list.get(3));
	    Assert.assertEquals(test.get(4), list.get(4));
	    Assert.assertEquals(test.get(5), list.get(5));
	    Assert.assertEquals(test.get(6), list.get(6));
	}

	@Test
	public void testRemoveRange() {
		list.add(1);
		list.add(1);
	    list.add(2);
	    list.add(3);
	    list.add(4);
	    list.add(4);
	    list.add(5);
	    list.add(6);
	    list.add(6);
	    list.add(7);
	    list.add(7);
	    LinkedList test =new LinkedList();
		test.add(1);
		test.add(1);
	    test.add(2);
	    test.add(6);
	    test.add(6);
	    test.add(7);
	    test.add(7);
	    list.removeRange(2, 6);
	    Assert.assertEquals(test.get(0), list.get(0));
	    Assert.assertEquals(test.get(1), list.get(1));
	    Assert.assertEquals(test.get(2), list.get(2));
	    Assert.assertEquals(test.get(3), list.get(3));
	    Assert.assertEquals(test.get(4), list.get(4));
	    Assert.assertEquals(test.get(5), list.get(5));
	    Assert.assertEquals(test.get(6), list.get(6));
	}
    @Test
    public void testIntersection(){
		list.add(1);
	    list.add(2);
	    list.add(3);
	    list.add(4);
	    list.add(5);
	    list.add(6);
	    list.add(7);
		list.add(11);
	    list.add(12);
	    list.add(13);
	    list.add(14);
	    list.add(15);
	    list.add(16);
	    list.add(17);
	    LinkedList l2 = new LinkedList();
	    l2.add(1);
	    l2.add(8);
	    l2.add(8);
	    l2.add(9);
	    l2.add(9);
	    l2.add(10);
	    l2.add(12);
	    l2.add(17);
	    LinkedList test = new LinkedList();
	    test.add(1);
	    test.add(12);
	    test.add(17);
	    list = list.intersection(l2);
	    Assert.assertEquals(test.get(0), list.get(0));
	    Assert.assertEquals(test.get(1), list.get(1));
	    Assert.assertEquals(test.get(2), list.get(2));
    }
}
