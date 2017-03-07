package com.easy.util.myarraylist;

import org.junit.Assert;
import org.junit.Test;

import com.easy.util.myarraylist.ArrayList;
import com.easy.util.myiterator.Iterator;

public class TestArrayList {
	
	@Test
	public void test_add(){
		ArrayList list=new ArrayList();
		list.add("aa");
		list.add("bb");
		Assert.assertEquals("[aa,bb]", list.toString());
		Assert.assertEquals(2, list.size());
	}
	
	@Test
	public void test_add_object(){
		ArrayList list=new ArrayList();
		list.add("aa");
		list.add("cc");
		list.add(1, "bb");
		Assert.assertEquals("[aa,bb,cc]", list.toString());
		Assert.assertEquals(3, list.size());
	}
	
	@Test
	public void test_get(){
		ArrayList list=new ArrayList();
		list.add("aa");
		list.add("cc");
		list.add(1, "bb");
		Assert.assertEquals("aa", list.get(0));
		Assert.assertEquals("bb", list.get(1));
		Assert.assertEquals("cc", list.get(2));
	}
	
	@Test
	public void test_remove_int(){
		ArrayList list=new ArrayList();
		list.add("aa");
		list.add("cc");
		list.add(1, "bb");
		Object removeObj=list.remove(1);
		Assert.assertEquals("bb", removeObj);
		Assert.assertEquals("[aa,cc]", list.toString());
		Assert.assertEquals(2, list.size());
	}
	
	@Test
	public void test_remove_object(){
		ArrayList list=new ArrayList();
		list.add("aa");
		list.add("cc");
		list.add(1, "bb");
		boolean b=list.remove("bb");
		Assert.assertEquals(true,b);
		Assert.assertEquals("[aa,cc]", list.toString());
		Assert.assertEquals(2, list.size());
	}
	
	@Test
	public void test_iterator(){
		ArrayList list=new ArrayList();
		list.add("aa");
		list.add("cc");
		list.add(1, "bb");
		
		Iterator iterator = list.iterator();
		
		/*while(iterator.hasNext()){
			System.out.println(iterator.next());
		}*/
		
		Assert.assertEquals("aa", iterator.next());
		Assert.assertEquals("bb", iterator.next());
		Assert.assertEquals("cc", iterator.next());
	}
}
