package test.com.pxshuo.basic.impl;

import org.junit.Assert;
import org.junit.Test;

import com.pxshuo.basic.impl.ArrayList;

public class ArrayListTest {
	ArrayList object = new ArrayList();
	
	@Test
	public void addTest() {
		object.add("String");
		Assert.assertEquals("String", object.get(0));
	}
	
	@Test
	public void addIndexTest(){
		object.add(3,"Hello");
		Assert.assertEquals("Hello", object.get(3) );
	}
	
	@Test
	public void removeTest() {
		object.add("Hello");
		object.add("Hello");
		object.add("Hello");
		object.add(3,"Hello");
		Assert.assertNotNull(object.get(3));
		object.remove(3);
		Assert.assertNull(object.get(3));
	}
	
	@Test
	public void sizeTest(){
		object.add("new");
		object.add("hi");
		object.add(1,"new");
		object.remove(2);
		Assert.assertEquals(2, object.size());
	}
}
