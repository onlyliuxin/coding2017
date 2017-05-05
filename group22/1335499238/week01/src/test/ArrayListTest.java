package test;


import org.junit.Assert;
import org.junit.Test;

import basic.ArrayList;
import basic.Iterator;

public class ArrayListTest {
	
	@Test
	public void test01(){
		ArrayList arrayList = new ArrayList();
		arrayList.add("612");
		arrayList.add("1");
		arrayList.add("2");
		arrayList.add("5");
		arrayList.add("6");
		Assert.assertEquals("[612, 1, 2, 5, 6]", arrayList.toString());
		
		Object remove = arrayList.remove(2);
		Assert.assertEquals("2", remove);
		
		arrayList.add(2, "13");
		Assert.assertEquals("[612, 1, 13, 5, 6]", arrayList.toString());
		
		Object object = arrayList.get(2);
		Assert.assertEquals("13", object);
		
		Assert.assertEquals(5, arrayList.size());
		
		Iterator iterator = arrayList.iterator();
		while (iterator.hasNext()) {
			System.out.print(iterator.next()+"   ");
			
		}
	}

}
