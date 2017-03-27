package test.com.coding.basic.linklist;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.coding.basic.Iterator;
import com.coding.basic.linklist.LinkedList;

public class LinkedListTest {

	LinkedList ls ;
	@Before
    public void setup() {
        ls = new LinkedList();       
    }
	
	/**
	 * 测试一个参数的add方法
	 * ArrayList当数据超过10时进行第一次扩容
	 */
	@Test
	public void add(){
		
		ls.add(3);
		ls.add("a");
		for (int i = 0; i < 10; i++) {
			ls.add(i);
		}
		Assert.assertEquals(ls.size(), 12);
		Assert.assertEquals(ls.get(1), "a");
	}

	/**
	 *  两个参数的add方法
	 */
	@Test//(expected = IndexOutOfBoundsException.class)
	public void add4ToPramter(){
		
		ls.add(0, 0);
		ls.add(1,1);
		ls.add(2, 2);
		ls.add(3,3);
		for (int i = 0; i < 10; i++) {
			ls.add(3,i);
		}
		Assert.assertEquals(ls.size(), 14);
		Assert.assertEquals(ls.get(3), 9);
		Assert.assertEquals(ls.get(13), 3);
		//打开下面操作抛出异常
		//ls.add(15, "a");
	}

	/**
	 * get(i)
	 */
	@Test//(expected = IndexOutOfBoundsException.class)
	public void get(){
		
		for (int i = 0; i < 10; i++) {
			ls.add(i);
		}
		
		Assert.assertEquals(ls.get(9), 9);	
		//打开下面操作抛出异常
		//ls.get(12);
	}
	
	@Test
	public void remove(){
		
		for (int i = 0; i < 10; i++) {
			ls.add(i);
		}	
		Assert.assertEquals(ls.remove(5),5);
		Assert.assertEquals(ls.size(),9);
		Assert.assertEquals(ls.remove(8),9);
		Assert.assertEquals(ls.size(),8);
	}
	
	@Test
	public void size(){
		
		Assert.assertEquals(ls.size(),0);
		ls.add("a");
		Assert.assertEquals(ls.size(),1);
		ls.add(0,0);
		Assert.assertEquals(ls.size(),2);
		ls.remove(0);
		Assert.assertEquals(ls.size(),1);
		
	}
	
	@Test//(expected = NoSuchElementException.class)
	public void iterator(){
		
		for (int i = 0; i < 10; i++) {
			ls.add(i);
		}
		Iterator it = ls.iterator();
		Assert.assertEquals(it.hasNext(),true);
		for (int i = 0; i < 10; i++) {
			it.next();
		}		
		Assert.assertEquals(it.hasNext(),false);
		//打开下面操作抛出异常
		//it.next();
	}
	
	@Test
	public void testReverse(){
		
		ls.add(3);
		ls.add(7);
		ls.add(10);
		ls.add(8);
		ls.reverse();
		int[] expected = {8,10,7,3};
		for (int i = 0; i < ls.size(); i++) {
			Assert.assertEquals(expected[i], ls.get(i));			
		}
	}
	
	public void testRemoveFirstHalf(){
		
		ls.add(2);
		ls.add(5);
		ls.add(7);
		ls.add(8);
		ls.add(10);
		int[] expected = {7,8,10};
		ls.removeFirstHalf();
		for (int i = 0; i < ls.size(); i++) {
			Assert.assertEquals(ls.get(i), expected[i]);
		}
		
	}
	
	@Test
	public void testRemove(){
		ls.add(2);
		ls.add(5);
		ls.add(7);
		ls.add(8);
		ls.add(10);
		
		ls.remove(0, 1);
		int[] expected = {5,7,8,10};
		exceptResult(ls,expected);
		
		ls.remove(3,1);
		int[] expected1 = {5,7,8};		
		exceptResult(ls,expected1);
		
		ls.add(9);
		ls.remove(2,8);
		int[] expected2 = {5,7};
		exceptResult(ls,expected2);
		
		ls.remove(0,9);
		int[] expected3 = {};
		exceptResult(ls,expected3);
		
	}
	
	@Test
	public void testGetElements(){
		ls.add(11);ls.add(101);
		ls.add(201);ls.add(301);
		ls.add(401);ls.add(501);
		ls.add(601);ls.add(701);
		LinkedList list = new LinkedList();
		list.add(1);list.add(3);
		list.add(4);list.add(6);
		int[] exceptArr = {101,301,401,601};
		int[] actual= ls.getElements(list);
		for (int i = 0; i < actual.length; i++) {
			Assert.assertEquals(exceptArr[i],actual[i]);
		}
		Assert.assertEquals(exceptArr.length,actual.length);
	}
	
	@Test
	public void testSubtract(){
		
		ls.add(2);
		ls.add(5);
		ls.add(7);
		ls.add(8);
		ls.add(10);
		
		LinkedList list = new LinkedList();
		list.add(2);
		list.add(5);
		
		int[] exceptArr = {7,8,10};
		ls.subtract(list);
		exceptResult(ls,exceptArr);
		
	}
	
	@Test
	public void testRemoveDuplicateValues(){
		
		ls.add(2);
		ls.add(5);
		ls.add(5);
		ls.add(5);
		ls.add(8);
		ls.add(8);
		ls.removeDuplicateValues();
		int[] exceptArr = {2,5,8};
		exceptResult(ls,exceptArr);
	}
	
	@Test
	public void testRemoveRange(){
		
		ls.add(2);
		ls.add(5);
		ls.add(7);
		ls.add(8);
		ls.add(10);
		
		ls.removeRange(0, 7);
		int[] exceptArr = {7,8,10};
		exceptResult(ls,exceptArr);
	}
	
	@Test
	public void testIntersection(){
		
		ls.add(-2);
		ls.add(-1);
		ls.add(0);
		ls.add(3);
		ls.add(5);
		
		LinkedList list = new LinkedList();
		list.add(-1);
		list.add(0);
		list.add(5);
		list.add(9);
		
		LinkedList newList = ls.intersection(list);
		
		int[] exceptArr = {-1,0,5};
		exceptResult(newList,exceptArr);
	}
	
    private void exceptResult(LinkedList ls,int[] exceptArr){
		
		Assert.assertEquals(ls.size(), exceptArr.length);
		for (int i = 0; i < exceptArr.length; i++) {
			Assert.assertEquals(exceptArr[i],ls.get(i));
		}
	}
}
