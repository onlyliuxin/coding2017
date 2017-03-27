package week1.collectiontest;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import week1.collections.ArrayList;
import week1.collections.Iterator;

public class ArrayListTest {
	ArrayList list;
	@Before
	public void init(){
		list = new ArrayList();
		for(int i=1;i<=10;i++){
			list.add(i);
		}
	}
	
	@Test
	public void test1(){
		list.add(4,4.5);
		assertEquals(4.5, list.get(4));
	}
	
	@Test
	public void test2(){
		for(int i=10;i>=1;i--){
			assertEquals(i, list.remove(i-1));
		}
	}
	
	@Test
	public void test3(){
		Iterator it = list.iterator();
		while(it.hasNext()){
			for(int i=1;i<=10;i++){
				assertEquals(it.next(), i);
			}
		}
	}
}
