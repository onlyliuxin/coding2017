package com.coding.week1;

import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayList {
	ArrayList arrayList =new ArrayList();

	@Test
	public void Test(){
		//add
		arrayList.add("MM");
		arrayList.add(1,"YY");
		arrayList.add(2,"ZZ");
		//get
		assertEquals((String)arrayList.get(0), "MM");
		assertEquals(arrayList.size(),3 );
		//remove
		assertEquals(arrayList.remove(2), "ZZ"); 
		assertEquals(arrayList.size(),2 );
		//iterator
		Iterator ito =  arrayList.iterator();
		int i=0;
		while(ito.hasNext()){
			assertEquals(ito.next(), arrayList.get(i));
			i++;
		}
	}
	

}
