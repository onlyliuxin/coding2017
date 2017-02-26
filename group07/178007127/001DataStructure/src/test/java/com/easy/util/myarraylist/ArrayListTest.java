package com.easy.util.myarraylist;

import org.junit.Assert;
import org.junit.Test;

import com.easy.util.myarraylist.ArrayList;

public class ArrayListTest {
	
	@Test
	public void test_add(){
		ArrayList list=new ArrayList();
		list.add("aa");
		list.add("bb");
		Assert.assertEquals("[aa,bb]", list.toString());
	}
	
	@Test
	public void test_add_object(){
		ArrayList list=new ArrayList();
		list.add("aa");
		list.add("bb");
		list.add(1, "aabb");
		Assert.assertEquals("[aa,aabb,bb]", list.toString());
	}
	
	@Test
	public void test_get(){
		ArrayList list=new ArrayList();
		list.add("aa");
		list.add("bb");
		Object element =list.get(1);
		Assert.assertEquals("bb", element);
	}
	
	@Test
	public void test_remove_int(){
		ArrayList list=new ArrayList();
		list.add("aa");
		list.add("bb");
		list.add(1, "aabb");
		list.remove(1);
		Assert.assertEquals("[aa,bb]", list.toString());
	}
	
	@Test
	public void test_size(){
		ArrayList list=new ArrayList();
		list.add("aa");
		list.add("bb");
		list.add(1, "aabb");
		list.remove(1);
		Assert.assertEquals(2, list.size());
	}
	
	
}
