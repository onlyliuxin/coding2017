package com.easy.util.mylinkedlist;

import org.junit.Assert;
import org.junit.Test;
import com.easy.util.mylinkedlist.LinkedList;

public class TestLinkedList {

	@Test
	public void test_addFirst_object() {
		LinkedList list=new LinkedList();
		list.addFirst("aa");
		Assert.assertEquals("[aa]",list.toString());
		list.addFirst("bb");
		Assert.assertEquals("[bb,aa]",list.toString());
		list.addFirst("cc");
		Assert.assertEquals("[cc,bb,aa]", list.toString());
		Assert.assertEquals(3, list.size);
	}

	@Test
	public void test_addLast_object() {
		LinkedList list=new LinkedList();
		list.addLast("aa");
		Assert.assertEquals("[aa]",list.toString() );
		list.addLast("bb");
		Assert.assertEquals("[aa,bb]", list.toString());
		list.addLast("cc");
		Assert.assertEquals("[aa,bb,cc]", list.toString());
		Assert.assertEquals(3, list.size);
	}
	
	@Test
	public void test_add_object() {
		LinkedList list=new LinkedList();
		list.add("aa");
		Assert.assertEquals("[aa]",list.toString() );
		list.add("bb");
		Assert.assertEquals("[aa,bb]", list.toString());
		list.add("cc");
		Assert.assertEquals("[aa,bb,cc]", list.toString());
		Assert.assertEquals(3, list.size);
	}
	
	@Test
	public void test_add_int_object() {
		LinkedList list=new LinkedList();
		list.add("aa");
		list.add("cc");
		list.add(1,"bb");
		Assert.assertEquals("[aa,bb,cc]", list.toString());
	}
	

	@Test
	public void test_get_int() {
		LinkedList list=new LinkedList();
		list.add("aa");
		list.add("bb");
		list.add("cc");
		Assert.assertEquals("aa", list.get(0));
		Assert.assertEquals("bb", list.get(1));
		Assert.assertEquals("cc", list.get(2));
	}

	@Test
	public void test_remove_object() {
		LinkedList list=new LinkedList();
		list.add("aa");
		list.add("bb");
		list.add("cc");
		boolean b = list.remove("bb");
		Assert.assertEquals(true, b);
		Assert.assertEquals(2, list.size);
		Assert.assertEquals("aa", list.get(0));
		Assert.assertEquals("cc", list.get(1));
		Assert.assertEquals("[aa,cc]", list.toString());
	}

	@Test
	public void test_removeFirst() {
		LinkedList list=new LinkedList();
		list.add("aa");
		list.add("bb");
		list.add("cc");
		Object obj = list.removeFirst();
		Assert.assertEquals("aa",obj);
		Assert.assertEquals(2, list.size);
		Assert.assertEquals("bb", list.get(0));
		Assert.assertEquals("cc", list.get(1));
		Assert.assertEquals("[bb,cc]", list.toString());
	}
	
	@Test
	public void test_removeLast() {
		LinkedList list=new LinkedList();
		list.add("aa");
		list.add("bb");
		list.add("cc");
		Object obj = list.removeLast();
		Assert.assertEquals("cc",obj);
		Assert.assertEquals(2, list.size);
		Assert.assertEquals("aa", list.get(0));
		Assert.assertEquals("bb", list.get(1));
		Assert.assertEquals("[aa,bb]", list.toString());
	}

}
