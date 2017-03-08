package com.coding.basic;

import static org.junit.Assert.*;

public class Test {

	@org.junit.Test
	public void test() {
		
/*		ArrayList al = new ArrayList();
		al.add(1);
		al.add(2);
		al.add(3);
		al.add(4);
		al.add(5);
		al.add(200);
		al.add(10,100);
		al.Iteror();
		//System.out.println(al.length());
		//System.out.println(al.size());
		System.out.println("==================");
		al.remove(0);
		al.Iteror();*/
		
		LinkList ls = new LinkList();
		ls.add(100);
		ls.add(300);
		ls.add(500);
		ls.add(1000);
		ls.add(3,2000);
		ls.display();
		System.out.println(ls.get(4));
	}

}

