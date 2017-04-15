package com.coding.basic;

import junit.framework.TestCase;

public class Testarray extends TestCase{
	
	public void testararry(){
		Throwable tx = null;
		try {
			ArrayList n = new ArrayList();
			Object o = null ;
		
			n.add(o);
			fail();
		} catch (Exception e) {
			tx =e;
			assertEquals(Exception.class, tx.getClass());
			assertEquals("对象不能为空", e.getMessage());
		}
	}

}
