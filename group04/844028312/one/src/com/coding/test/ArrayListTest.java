package com.coding.test;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.BeforeClass;

import com.coding.basic.ArrayList;
import com.coding.basic.Iterator;

public class ArrayListTest {
	private ArrayList arrayList;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		arrayList=new ArrayList();
		for(int i=0;i<10;i++){
			arrayList.add(i);
		}
	}

	
	@org.junit.Test
	public void addAndGet(){
		for(int i=0;i<10;i++){
			Assert.assertEquals(i, (int)arrayList.get(i));
		}
	}
	@org.junit.Test
	public void addWithIndex(){
		arrayList.add(4, "a");
		Assert.assertEquals("a", arrayList.get(4));
	}
	
	@org.junit.Test
	public void remove(){
		Object before=arrayList.get(4);
		Object reMove=arrayList.remove(4);
		Assert.assertEquals(before, reMove);
		
	}
	@org.junit.Test
	public void size(){
		Assert.assertEquals(10, arrayList.size());
	}
	@org.junit.Test
	public void iterator(){
		Iterator it=arrayList.iterator();
		int i=0;
		while(it.hasNext()){
			Assert.assertEquals(it.next(),arrayList.get(i));
			i++;
		}
	}
	
}
