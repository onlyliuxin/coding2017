package com.maple.test;

import org.junit.Test;

import com.maple.basic.ArrayList;
import com.maple.basic.Iterator;

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
