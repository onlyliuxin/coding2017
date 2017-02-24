package test.com.pxshuo.basic.impl;

import org.junit.Assert;
import org.junit.Test;

import com.pxshuo.basic.impl.ArrayList;

public class ArrayListTest {
	@Test
	public void addTest() {
		ArrayList arrayList = new ArrayList();
		arrayList.add("String");
		Assert.assertEquals(arrayList.get(0), "String");
	}
}
