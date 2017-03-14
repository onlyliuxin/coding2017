package com.m0226.test;

import org.junit.Test;

import com.m0226.basic.ArrayList;
import com.m0226.basic.Iterator;

public class TestArrayList{
	
	@Test
	public void testAdd(){
		ArrayList list1=new ArrayList();
		list1.add(0);
		list1.add(1);
		//list1.add(3, -1);//error
		//list1.remove(2);//error
		Iterator ite=list1.iterator();
		while(ite.hasNext()){
			System.out.println(ite.next());
		}
	}
}
