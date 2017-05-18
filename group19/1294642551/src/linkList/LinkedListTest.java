package linkList;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LinkedListTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testReverse() {
		LinkedList l = new LinkedList();
		
		Assert.assertEquals("[]", l.toString());
		
		l.add(1);
		l.reverse();
		Assert.assertEquals("[1]", l.toString());
		
		l.add(2);
		l.add(3);
		l.add(4);
		
		l.reverse();
		Assert.assertEquals("[4,3,2,1]", l.toString());
	}

	@Test
	public void testRemoveFirstHalf() {
		{
			LinkedList linkedList = new LinkedList();
			linkedList.add(1);
			linkedList.add(2);
			linkedList.add(3);
			linkedList.add(4);
			linkedList.removeFirstHalf();
			Assert.assertEquals("[3,4]", linkedList.toString());
		}
		{
			LinkedList linkedList = new LinkedList();
			linkedList.add(1);
			linkedList.add(2);
			linkedList.add(3);
			linkedList.add(4);
			linkedList.add(5);
			linkedList.removeFirstHalf();
			Assert.assertEquals("[3,4,5]", linkedList.toString());
		}
	}
	


	@Test
	public void testRemoveIntInt() {
		
		{
			LinkedList linkedList = new LinkedList();
			linkedList.add(1);
			linkedList.add(2);
			linkedList.add(3);
			linkedList.add(4);
			linkedList.remove(0, 2);		
			Assert.assertEquals("[3,4]", linkedList.toString());
		}
		{
			LinkedList linkedList = new LinkedList();
			linkedList.add(1);
			linkedList.add(2);
			linkedList.add(3);
			linkedList.add(4);
			linkedList.remove(3, 2);		
			Assert.assertEquals("[1,2,3]", linkedList.toString());
		}
		{
			LinkedList linkedList = new LinkedList();
			linkedList.add(1);
			linkedList.add(2);
			linkedList.add(3);
			linkedList.add(4);
			linkedList.remove(2, 2);		
			Assert.assertEquals("[1,2]", linkedList.toString());
		}
		
	}
	@Test
	public void testGetElements() {
		LinkedList linkedList = new LinkedList();
		linkedList.add(11);
		linkedList.add(101);
		linkedList.add(201);
		linkedList.add(301);
		linkedList.add(401);
		linkedList.add(501);
		linkedList.add(601);
		linkedList.add(701);
		LinkedList list = new LinkedList();
		list.add(1);
		list.add(3);
		list.add(4);
		list.add(6);
		Assert.assertArrayEquals(new int[]{101,301,401,601}, linkedList.getElements(list));
	}
	
	@Test
	public void testSubtract() {
		LinkedList list1 = new LinkedList();		
		list1.add(101);
		list1.add(201);
		list1.add(301);
		list1.add(401);
		list1.add(501);
		list1.add(601);
		list1.add(701);
		
		LinkedList list2 = new LinkedList();	
		
		list2.add(101);
		list2.add(201);
		list2.add(301);
		list2.add(401);
		list2.add(501);
		
		list1.subtract(list2);
		
		Assert.assertEquals("[601,701]", list1.toString());
	}
	
	
	@Test
	public void testRemoveDuplicateValues() {
		LinkedList list = new LinkedList();
		list.add(1);
		list.add(1);
		list.add(2);
		list.add(2);
		list.add(3);
		list.add(5);
		list.add(5);
		list.add(6);
		list.removeDuplicateValues();
		Assert.assertEquals("[1,2,3,5,6]", list.toString());
	}
	
	
	@Test
	public void testRemoveRange() {
		
		{
			LinkedList linkedList = new LinkedList();
			
			linkedList.add(11);
			linkedList.add(12);
			linkedList.add(13);
			linkedList.add(14);
			linkedList.add(16);
			linkedList.add(16);
			linkedList.add(19);
			
			linkedList.removeRange(10, 19);
			Assert.assertEquals("[19]", linkedList.toString());
		}
		
		{
			LinkedList linkedList = new LinkedList();
			
			linkedList.add(11);
			linkedList.add(12);
			linkedList.add(13);
			linkedList.add(14);
			linkedList.add(16);
			linkedList.add(16);
			linkedList.add(19);
			
			linkedList.removeRange(10, 14);
			Assert.assertEquals("[14,16,16,19]", linkedList.toString());
		}
	}
	@Test
	public void testIntersection() {
		LinkedList list1 = new LinkedList();
		list1.add(1);
		list1.add(6);
		list1.add(7);
		
		LinkedList list2 = new LinkedList();
		list2.add(2);
		list2.add(5);
		list2.add(6);
		
		LinkedList newList = list1.intersection(list2);
		Assert.assertEquals("[6]", newList.toString());
	}
	
}
