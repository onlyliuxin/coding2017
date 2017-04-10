package week01.test;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import week01.basic.ArrayList;
import week01.basic.Iterator;


public class ArrayListTest {
	ArrayList list = null;
	
    @Before
    public void init(){
    	list = new ArrayList();
    	for(int i=1;i<=500;i++){
            list.add(i);
  		}
    }
	
	@Test
	public void addTest(){
		Assert.assertEquals(500, list.size());
        for(int i=1;i<=list.size();i++){
        	Assert.assertEquals(i, list.get(i-1));
        }
	}
	
	@Test
	public void addIndexTest(){
		list.add(250, 3333);
		Assert.assertEquals(3333, list.get(250));
		Assert.assertEquals(500, list.get(500));
	}
	
	@Test
	public void removeIndexTest(){
		list.remove(250);
		Assert.assertEquals(499, list.size());
		Assert.assertEquals(252, list.get(250));
		Assert.assertEquals(500, list.get(498));
	}
	
	@Test
	public void iteratorTest(){
		Iterator iterator = list.iterator();
		int count = 1;
		while(iterator.hasNext()){
			Assert.assertEquals(++count, iterator.next());
		}
	}
	
}
