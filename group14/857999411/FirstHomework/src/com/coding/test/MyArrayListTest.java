package com.coding.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.coding.basic.MyArrayList;

public class MyArrayListTest {

	@Test
	public void test() {
		MyArrayList sa =new MyArrayList();
		sa.add(0,0);
		sa.add(1,1);
		sa.add(2,2);
		sa.add(3,3);

		//System.out.println(sa.get(1));

		for(int i=0; i<sa.size(); i++)
		{
			System.out.print(sa.get(i));
		}

		System.out.println(sa.remove(3));

		for(int i=0; i<sa.size(); i++)
		{
			System.out.print(sa.get(i));
		}
	}

}
