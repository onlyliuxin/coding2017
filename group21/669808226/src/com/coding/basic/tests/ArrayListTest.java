/**
 * 
 */
package com.coding.basic.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.coding.basic.ArrayList;
import com.coding.basic.Iterator;

/**
 * @author Jack
 *
 */
public class ArrayListTest {

	private ArrayList arrayList;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		arrayList = new ArrayList();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		arrayList = null;
	}

	/**
	 * Test method for {@link com.coding.basic.ArrayList#add(java.lang.Object)}.
	 */
	@Test
	public void testAddObject() {
		//fail("Not yet implemented");
		arrayList.add(null);
		assertEquals(arrayList.size(), 0);
		
		arrayList.add(new Integer(1024));
		assertEquals(arrayList.size(), 1);
		
		for(int i=0; i<200; ++i)
		{
			arrayList.add(new Integer(2048));
		}
		assertEquals(arrayList.size(), 201);
		
		AssertIntegerElement(arrayList.size() - 1, 2048);
		
		AssertIntegerElement(0, 1024);
	}

	private void AssertIntegerElement(int index, int val) {
		Object element = arrayList.get(index);
		assertNotNull(element);
		Integer iFirst = (Integer)element;
		assertNotNull(iFirst);
		assertEquals(iFirst.intValue(), val);
	}

	/**
	 * Test method for {@link com.coding.basic.ArrayList#add(int, java.lang.Object)}.
	 */
	@Test
	public void testAddIntObject() {
		//fail("Not yet implemented");
		arrayList.add(0, null);
		assertEquals(arrayList.size(), 0);
		
		arrayList.add(-1, new Object());
		assertEquals(arrayList.size(), 0);
		
		arrayList.add(0, new Integer(1024));
		assertEquals(arrayList.size(), 1);
		
		for(int i=0; i<200; ++i)
		{
			arrayList.add(i, new Integer(i));
		}
		assertEquals(arrayList.size(), 201);
		//[0,1,2,3,....198,199,1024] 
		
		AssertIntegerElement(0, 0);
		
		AssertIntegerElement(arrayList.size() - 2, 199);
		
		AssertIntegerElement(arrayList.size() - 1, 1024);
		
		arrayList.add(201, new Integer(201));
		//[0,1,2,3,....198,199,1024,201] 
		arrayList.add(210, new Integer(210));
		//[0,1,2,3,....198,199,1024,201,210] 
		arrayList.add(300, new Integer(300));
		//[0,1,2,3,....198,199,1024,201,210,300] 
		
		assertEquals(arrayList.size(), 204);//actual size
		
		AssertIntegerElement(201,201);
		
		Object number210Element = arrayList.get(210);
		assertNull(number210Element);
		
		AssertIntegerElement(202,210);
		
		Object number300Element = arrayList.get(300);
		assertNull(number300Element);
		
		AssertIntegerElement(203,300);
		
		for(int j=0; j<204; ++j)
		{
			assertNotNull(arrayList.get(j));
		}
		
		assertNull(arrayList.get(204));
		assertNull(arrayList.get(209));
		assertNull(arrayList.get(300));
	}


	/**
	 * Test method for {@link com.coding.basic.ArrayList#remove(int)}.
	 */
	@Test
	public void testRemove() {
		arrayList.add(new Integer(1024));
		//assertEquals(arrayList.size(), 1);
		
		Object obj1024 = arrayList.remove(0);
		assertEquals(arrayList.size(), 0);
		
		assertNotNull(obj1024);
		
		Integer int1024 = (Integer)obj1024;
		assertNotNull(int1024);
		assertEquals(int1024.intValue(), 1024);
		
		assertNull(arrayList.get(0));
		
		for(int i=0; i<200; ++i)
		{
			arrayList.add(i, new Integer(i));
		}
		assertEquals(arrayList.size(), 200);
		
		arrayList.remove(0);
		assertEquals(arrayList.size(), 199);
		
		AssertIntegerElement(0, 1);
		
		AssertIntegerElement(arrayList.size() - 1, 199);
		
		assertNull(arrayList.get(arrayList.size()));
		
		//verify this:[1,2,3,....,199]
		for(int i=0; i<199; ++i)
		{
			assertEquals(((Integer)arrayList.get(i)).intValue(), i + 1);
		}
		
		Object number198Element = arrayList.remove(198);
		assertEquals(arrayList.size(), 198);
		assertNull(arrayList.get(198));
		assertEquals(((Integer)number198Element).intValue(), 199);
		
		Object number5Element = arrayList.remove(5);
		assertEquals(arrayList.size(), 197);
		assertNull(arrayList.get(197));
		assertEquals(((Integer)number5Element).intValue(), 6);
		Object newNumber5Element = arrayList.get(5);
		assertEquals(((Integer)newNumber5Element).intValue(), 7);
		//Խ��index
		Object outofboundElement = arrayList.remove(1024);
		assertNull(outofboundElement);
		assertEquals(arrayList.size(), 197);
		
		Object negativeIndexElement= arrayList.remove(-1);
		assertNull(negativeIndexElement);
		assertEquals(arrayList.size(), 197);
		
		//fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.coding.basic.ArrayList#iterator()}.
	 */
	@Test
	public void testIterator() {
		//fail("Not yet implemented");
		
		Iterator iter=arrayList.iterator();
		
		assertFalse(iter.hasNext());
		assertNull(iter.next());
		
		for(int i=0; i<10; ++i)
		{
			arrayList.add(i, new Integer(i));
		}

		iter=arrayList.iterator();
		
		for(int i=0; i<10; ++i)
		{
			assertTrue(iter.hasNext());
			Object obj = iter.next();
			assertNotNull(obj);
			assertEquals(((Integer)obj).intValue(), i);
		}
		
		assertFalse(iter.hasNext());
		assertNull(iter.next());
	}

}
