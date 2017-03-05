package test;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import com.coding.basic.ArrayList;
import com.coding.basic.Iterator;

	

public class ArrayListTest {

	@Test
	public void testAdd() {
		ArrayList array = new ArrayList();
		for (int i = 0; i < 105; i++) {
			array.add(i);
		}
		
		Assert.assertEquals("Shoule be the same", 105, array.size());
	}
	
	@Test
	public void testAddIndex() {
		ArrayList array = new ArrayList();
		for (int i = 0; i < 105; i++) {
			array.add(i);
		}
		
		array.add(100, 100);
		Assert.assertEquals("Shoule be the same", 100, array.get(100));
		Assert.assertEquals("Shoule be the same", 100, array.get(101));
	}
	
	@Test
	public void testRemove() {
		ArrayList array = new ArrayList();
		for (int i = 0; i < 105; i++) {
			array.add(i);
		}
		
		Assert.assertEquals("Shoule be the same", 100, array.remove(100));
		Assert.assertEquals("Shoule be the same", 104, array.size());
	}
	
	@Test
	public void testIterator() {
		ArrayList array = new ArrayList();
		for (int i = 0; i < 105; i++) {
			array.add(i);
		}
		Iterator iterator = array.iterator();
		int j = 0;
		while(iterator.hasNext()){
			Assert.assertEquals("Shoule be the same", iterator.next(), array.get(j));
			j++;
		}
	}

}
