package my.collection.linear;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LinkedListTest {
	
	LinkedList list = new LinkedList();
	@Before
	public void setUp() throws Exception {
		for(int i=0; i<list.size(); i++){
			list.remove(i);
		}
		Assert.assertEquals("\n" + "toString():", list.toString());
	}

	@After
	public void tearDown() throws Exception {
	}
	
	
	/*@Test
	    public void testReverseByNode(){
		list.add("a1");
		list.add("a2");
		list.add("a3");
		list.add("a4");
		list.add("a5");
		
		//list.reverseByNode();
		Assert.assertEquals("\n" + "toString():a5	a4	a3	a2	a1	", list.toString());
	}*/
	
	@Test
	public void testReverse(){
		list.add("a1");
		list.add("a2");
		list.add("a3");
		list.add("a4");
		list.add("a5");
		
		list.reverse();
		Assert.assertEquals("\n" + "toString():a5	a4	a3	a2	a1	", list.toString());
	}

	@Test
	public void testRemoveFirstHalf() {
		list.add("a1");
		list.add("a2");
		list.add("a3");
		list.add("a4");
		list.add("a5");
		list.add("a6");

		list.removeFirstHalf();
		Assert.assertEquals("\n" + "toString():a4	a5	a6	", list.toString());
		
		list.removeFirstHalf();
		Assert.assertEquals("\n" + "toString():a5	a6	", list.toString());

	}

	@Test
	public void testRemoveIntInt() {
		list.add("a1");
		list.add("a2");
		list.add("a3");
		list.add("a4");
		list.add("a5");
		list.add("a6");
			
		list.remove(2,3);
		Assert.assertEquals("\n" + "toString():a1	a2	a6	", list.toString());
	}
	
	@Test
	public void testGetElement(){
		list.add(11);
		list.add(22);
		list.add(33);
		list.add(44);
		list.add(55);
		list.add(66);
		
		LinkedList list2 = new LinkedList();
		list2.add(0);
		list2.add(2);
		list2.add(5);
		int[] des = {11,33,66};
		Assert.assertArrayEquals(des, list.getElements(list2));
	}

	@Test
	public void testSubtract(){
		list.add(11);
		list.add(22);
		list.add(33);
		list.add(44);
		list.add(55);
		list.add(66);
		
		LinkedList list2 = new LinkedList();
		list2.add(33);
		list2.add(55);
		
		list.subtract(list2);
		Assert.assertEquals("\n" + "toString():11	22	44	66	",list.toString());
	}
	
	@Test
	public void testRemoveDuplicateValues(){
		list.add(11);
		list.add(22);
		list.add(33);
		list.add(33);
		list.add(55);
		list.add(66);
		list.add(66);
		list.add(66);
		list.add(66);
		list.add(77);
		
		list.removeDuplicateValues();
		Assert.assertEquals("\n" + "toString():11	22	33	55	66	77	", list.toString());
	}
	
	@Test
	public void testRemoveRange(){
		list.add(11);
		list.add(22);
		list.add(33);
		list.add(44);
		list.add(55);
		list.add(66);
		
		list.removeRange(25, 50);
		Assert.assertEquals("\n" + "toString():11	22	55	66	",list.toString());
	}
	
	@Test
	public void testIntersection(){
		list.add(11);
		list.add(22);
		list.add(33);
		list.add(44);
		list.add(55);
		list.add(66);
		
		LinkedList list2 = new LinkedList();
		list2.add(33);
		list2.add(44);
		
		Assert.assertEquals("\n" + "toString():33	44	", list.intersection(list2).toString());
	}
}
