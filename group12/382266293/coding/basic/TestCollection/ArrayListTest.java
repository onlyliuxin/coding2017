package TestCollection;

import static util.Print.*;
import static util.TestUtil.*;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import Collection.Iterator;
import Collection.List;
import Collection.Concrete.ArrayList;
import junit.framework.TestCase;

public class ArrayListTest extends TestCase {


	private ArrayList<Integer> myAL;
	private static Random rnd = new Random();
	
	@Before
	public void setUp() throws Exception {
		
		myAL = new ArrayList<Integer>();
		assertEquals(true,myAL.isEmpty());
		
	}

	@After
	public void tearDown() throws Exception {
		myAL = null;
	}

	@Test
	public void testRawTypeArrayList() {
		
		List rawList = new ArrayList();
		assertEquals(rawList.size(), 0);
		rawList.add(new Date());
		assertEquals(1, rawList.size());
	}

	@Test
	public void testEmpty() {
		
		assertEquals(true,myAL.isEmpty());
		
		myAL.add(5);
		assertEquals(false,myAL.isEmpty());
		
		int num = getRandomNumber();
		addIntWithNatureOrder(myAL, num);
		assertEquals(false,myAL.isEmpty());
		
	}

	@Test
	public void testAddIntAutoBoxing() {
		
		myAL.add(5);
		myAL.add(5);
		myAL.add(5);
		myAL.add(1,10);
		int c = myAL.get(1);
		assertEquals(10,c);
		
		assertEquals(4,myAL.size());
		myAL.add(4,15);
		int a = myAL.get(0);
		Integer b = myAL.get(1);
		c =  myAL.get(2);
		int d = myAL.get(3);
		int e = myAL.get(4);
		assertEquals(5,a);
		assertEquals(new Integer(10),b);
		assertEquals(5,c);
		assertEquals(5,d);
		assertEquals(15,e);
	}
	
	@Test
	public void testGet() {
		
		int[] result = addRandomInt(myAL, getRandomNumber());
		
		int actual,expected;
		
		for (int i = 0; i < result.length; i++) {
			actual = myAL.get(i);
			expected = result[i];
			assertEquals(expected, actual);
		}

	}

	@Test
	public void testRemove() {

		addIntWithNatureOrder(myAL, 100);
		
		testRemoveAndGetFromTail(myAL);
		try {
			myAL.remove(10);
			fail("no exception thrown");
		} catch (Exception e) {
			assertEquals(IndexOutOfBoundsException.class, e.getClass());
		}

	}

	@Test
	public void testSize() {
		
		assertEquals(0,myAL.size());
		int num = getRandomNumber();
		addIntWithNatureOrder(myAL, num);
		assertEquals(num,myAL.size());
	}


	
	@Test
	public void testGrow() {
		
		int actualSize = 12345;
		
		addIntWithNatureOrder(myAL, actualSize);
		
		assertEquals(actualSize,myAL.size());
	}
	

	@Test
	public void testIterator() {
		
		addIntWithNatureOrder(myAL,100);
		
		Iterator<Integer> it = myAL.iterator();

		for(int i = 0; it.hasNext(); i++){
			int actual = it.next();
			assertEquals(i,actual);
		}
		
	    try {
			it.next();
	    } catch (NoSuchElementException ex) {
	        assertEquals(ex.getClass(),NoSuchElementException.class);
	    }
	}
	
	@Test
	public void testIndexOf() {
		
		int num = 200;
		addIntWithNatureOrder(myAL,num);

		int expected,actual;
		for (int i = 0; i < num-1; i++) {
			expected = i;
			actual = myAL.indexOf(i);
			assertEquals(expected, actual);
		}
		
		assertEquals(-1, myAL.indexOf(-1*getRandomNumber()));

	}

}
