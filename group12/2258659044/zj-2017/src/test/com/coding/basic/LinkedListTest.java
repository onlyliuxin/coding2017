package test.com.coding.basic;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.coding.basic.Iterator;
import com.coding.basic.LinkedList;

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
}
