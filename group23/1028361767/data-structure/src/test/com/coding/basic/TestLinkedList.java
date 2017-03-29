package test.com.coding.basic;

import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Test;

import com.coding.basic.linklist.LinkedList;

public class TestLinkedList {
	
	@Test
	public void testReverse(){
		LinkedList list = new LinkedList();
		list.add(3);
		list.add(7);
		list.add(10);
		list.reverse();
		Assert.assertEquals("10,7,3", list.toString());
		Assert.assertEquals("10", list.get(0).toString());
	}
	
	@Test
	public void testRemoveFirstHalf(){
		LinkedList list = new LinkedList();
		list.add(3);
		list.add(7);
		list.add(10);
		list.add(12);
		list.add(15);
		list.removeFirstHalf();
		Assert.assertEquals("10", list.get(0).toString());
		Assert.assertEquals("10,12,15", list.toString());
		Assert.assertEquals(3, list.size());
	}
	
	@Test
	public void testRemoveByLength(){
		LinkedList list = new LinkedList();
		list.add(3);
		list.add(7);
		list.add(10);
		list.add(12);
		list.add(15);
		list.remove(0, 2);
		Assert.assertEquals("10", list.get(0).toString());
		Assert.assertEquals("10,12,15", list.toString());
		Assert.assertEquals(3, list.size());
		
		list.remove(1, 1);
		Assert.assertEquals("10", list.get(0).toString());
		Assert.assertEquals("10,15", list.toString());
		Assert.assertEquals(2, list.size());
	}
	
	@Test
	public void testGetElements(){
		LinkedList list = new LinkedList();
		list.add(0);
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(6);
		
		LinkedList indices = new LinkedList();
		indices.add(1);
		indices.add(3);
		indices.add(4);
		indices.add(6);
		int[] elements = list.getElements(indices);
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<elements.length;i++){
			sb.append(elements[i]).append(",");
		}
		String elementsStr = sb.length() == 0?"" : sb.substring(0, sb.length() - 1);
		Assert.assertEquals("1,3,4,6", elementsStr);
		try{
			indices.add(7);
			list.getElements(indices);
			fail("下标越界，没抛出异常");
		}catch(IndexOutOfBoundsException e){
			
		}
	}
	
	@Test
	public void testSubtract(){
		LinkedList list = new LinkedList();
		list.add(0);
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(6);
		
		LinkedList indices = new LinkedList();
		indices.add(1);
		indices.add(3);
		indices.add(4);
		indices.add(6);
		
		list.subtract(indices);
		Assert.assertEquals("0,2,5", list.toString());
		Assert.assertEquals(3, list.size());
	}
	
	@Test
	public void testRemoveDuplicateValues(){
		LinkedList list = new LinkedList();
		list.add(0);
		list.add(0);
		list.add(1);
		list.add(2);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(4);
		list.add(4);
		list.add(5);
		list.add(6);
		list.add(6);
		list.removeDuplicateValues();
		Assert.assertEquals("0,1,2,3,4,5,6", list.toString());
		Assert.assertEquals(7, list.size());
	}
	
	@Test
	public void testRemoveRange(){
		LinkedList list = new LinkedList();
		for(int i=0;i<10000;i++){
			list.add(i);
		}
		list.removeRange(200, 900);//0-200=201,901-9999=9099
		
//		Assert.assertEquals("0,1,2,3,6", list.toString());
		Assert.assertEquals(9300, list.size());
	}
	
	@Test
	public void testRemoveRange1(){
		LinkedList list = new LinkedList();
		for(int i=0;i<10000;i++){
			list.add(i);
		}
//		list.removeRange(200, 900);//0-200=201,901-9999=9099
		for(int i=201;i<901;i++){
			list.remove(i);
		}
		
		Assert.assertEquals(9300, list.size());
	}
	
	@Test
	public void testIntersection(){
		LinkedList list1 = new LinkedList();
		list1.add(0);
		list1.add(1);
		list1.add(2);
		list1.add(3);
		list1.add(5);
		list1.add(6);
		
		LinkedList list2 = new LinkedList();
		list2.add(1);
		list2.add(2);
		list2.add(4);
		list2.add(5);
		
		LinkedList list3 = list1.intersection(list2);
		
		Assert.assertEquals("1,2,5", list3.toString());
	}
}
