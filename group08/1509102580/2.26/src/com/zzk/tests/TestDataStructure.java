package com.zzk.tests;

import org.junit.Test;

import com.zzk.coding2017.zuoye_1.ArrayList;
import com.zzk.coding2017.zuoye_1.Iterator;
import com.zzk.coding2017.zuoye_1.LinkedList;
import com.zzk.coding2017.zuoye_1.Stack;

public class TestDataStructure {

	@Test
	public void test1(){
		ArrayList al = new ArrayList();
		al.add("111");
		al.add("222");
		al.add(0, "000");
		al.add(2,"333");
		
		Iterator it = al.iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
		System.out.println(al.get(0));
	}
	@Test
	public void testStack(){
		Stack st = new Stack();
		System.out.println(st.isEmpty());
		st.push("111");
		st.push("333");
		while(!st.isEmpty()){
			System.out.println(st.pop());
		}
	}
	
	
	@Test
	public void testLinked(){
		LinkedList ll = new LinkedList();
		ll.add("1");
		ll.add(0, "2");
		ll.addFirst("0");
		ll.addLast("3");
		//System.out.println(ll.get(0));
		Iterator it = ll.iterator();
		while(it.hasNext()){
			System.out.print(it.next());
		}
	}
}
