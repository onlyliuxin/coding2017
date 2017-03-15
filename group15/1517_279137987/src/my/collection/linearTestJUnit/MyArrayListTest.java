package my.collection.linearTestJUnit;

import my.collection.linear.MyArrayList;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MyArrayListTest {

	MyArrayList mal = new MyArrayList(5);
	
	@Before
	public void setUp() throws Exception {
		for(int i=0; i<mal.size(); i++){
			mal.remove(i);
		}
		//Assert.assertEquals(0,mal.size());
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddObject() {
		mal.add(new String("aaa"));
		mal.add(new String("sss"));
		mal.add(new String("ddd"));
		mal.add(new String("fff"));
		mal.add(new String("ggg"));
		mal.add(new String("hhh"));
		Assert.assertEquals("aaa", mal.get(0));
		Assert.assertEquals("sss", mal.get(1));
		Assert.assertEquals("ddd", mal.get(2));
		Assert.assertEquals("fff", mal.get(3));
		Assert.assertEquals("ggg", mal.get(4));
		Assert.assertEquals("hhh", mal.get(5));
	}
	
	@Test
	public void testAddIntObject() {
		Assert.assertEquals("toString():", mal.toString());
		mal.add(0, new Double(8.0));
		mal.add(1, new Double(5.0));
		Assert.assertEquals(8.0, mal.get(0));
		Assert.assertEquals(5.0, mal.get(1));
		Assert.assertEquals("toString():8.0	5.0	", mal.toString());
	}

	@Test
	public void testRemove() {
		Assert.assertEquals("toString():", mal.toString());
		mal.add(1);
		mal.add(2);
		mal.add(3);
		mal.remove(1);
		Assert.assertEquals("toString():1	3	", mal.toString());
	}

	@Test
	public void testGet() {
		mal.add(new String("aaa"));
		mal.add(new String("sss"));
		Assert.assertEquals("sss",mal.get(1));
	}

	@Test
	public void testSize() {
		Assert.assertEquals(0,mal.size());
		mal.add("aaa");
		mal.add(new String("sss"));
		mal.add(new String("ddd"));
		mal.add(1, new Double(5.0));
		Assert.assertEquals(4,mal.size());
	}
	
	@Test
	public void testToString() {
		mal.add("aaa");
		mal.add(new String("sss"));
		mal.add(new String("ddd"));
		mal.add(1, new Double(5.0));
		Assert.assertEquals("toString():aaa	5.0	sss	ddd	", mal.toString());
	}
	
	@Test
	public void testMyIterator(){
		Assert.assertEquals(0, mal.size());
		Assert.assertEquals(false, mal.myIterator().hasNext());
		mal.add(new Double(2.5));
		Assert.assertEquals(true, mal.myIterator().hasNext());
		mal.add(new Double(4.5));
		mal.add(new Double(6.5));
		
		String str = "";
		for(int i=0; i<mal.size(); i++){
			str += String.valueOf(mal.get(i)) + ",";
		}
		Assert.assertEquals("2.5,4.5,6.5,", str);
		
		//mal.myIterator();		内部非静态类实现接口中未定义的方法，不能被调用
 
	}

}
