package week01.BasicDataStructureTest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import week01.BasicDataStructure.ArrayList;
import week01.BasicDataStructure.Iterator;

public class ArrayListTest {
	
	private ArrayList arrayList = new ArrayList();

	@Before
	public void setUp() throws Exception {
		for(int i = 0;i < 100 ; i++){
			arrayList.add(i);
		}
	}

	@Test
	public void testAddObject() {
		for(int i = 0;i < 100;i++){
		Assert.assertEquals(arrayList.get(i), i);
		}
	}

	@Test
	public void testAddIntObject() {
		arrayList.add(0,10);
		arrayList.add(22, 44);
        arrayList.add(40, 5);
        arrayList.add(100,88);
        Assert.assertEquals(arrayList.get(0), 10);
        Assert.assertEquals(arrayList.get(22),44);
        Assert.assertEquals(arrayList.get(40), 5);
        Assert.assertEquals(arrayList.get(100), 88);
	}

	@Test
	public void testGet() {
		Assert.assertEquals(arrayList.get(0), 0);
		Assert.assertEquals(arrayList.get(33), 33);
		Assert.assertEquals(arrayList.get(77), 77);
		Assert.assertEquals(arrayList.get(99), 99);
	}

	@Test
	public void testRemove() {
		Assert.assertEquals(arrayList.remove(0), 0);
		Assert.assertEquals(arrayList.remove(0), 1);
		Assert.assertEquals(arrayList.remove(97), 99);
		Assert.assertEquals(arrayList.size(), 97);
	}

	@Test
	public void testSize() {
		Assert.assertEquals(arrayList.size(), 100);
		arrayList.add(5,5);
		Assert.assertEquals(arrayList.size(),101);
		arrayList.remove(5);
		Assert.assertEquals(arrayList.size(), 100);
	}

	@Test
	public void testIterator() {
		Iterator iterator = arrayList.iterator();
		for(int i=0;iterator.hasNext();i++){
			Assert.assertEquals(iterator.next(),i);
		}
	}
}
