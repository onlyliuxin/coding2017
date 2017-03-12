package test.com.coding.basic; 

import com.coding.basic.Iterator;
import com.coding.basic.LinkedListbak;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After; 

/** 
* LinkedListbak Tester. 
* 
* @author <Authors name> 
* @since <pre>ÈýÔÂ 12, 2017</pre> 
* @version 1.0 
*/ 
public class LinkedListbakTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: add(Object o) 
* 
*/ 
@Test
public void testAddO() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: add(int index, Object o) 
* 
*/ 
@Test
public void testAddForIndexO() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: get(int index) 
* 
*/ 
@Test
public void testGet() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: remove(int index) 
* 
*/ 
@Test
public void testRemove() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: size() 
* 
*/ 
@Test
public void testSize() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: addFirst(Object o) 
* 
*/ 
@Test
public void testAddFirst() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: addLast(Object o) 
* 
*/ 
@Test
public void testAddLast() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: removeFirst() 
* 
*/ 
@Test
public void testRemoveFirst() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: removeLast() 
* 
*/ 
@Test
public void testRemoveLast() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: iterator() 
* 
*/ 
@Test
public void testIterator() throws Exception { 
//TODO: Test goes here...
    LinkedListbak list = new LinkedListbak();
    list.add("3");
    list.add("8");
    list.add("10");
    //list.reverse();
    System.out.println(list.size());
    Iterator iterator = list.iterator();
    while (iterator.hasNext()) {
        // String s= new String(iterator.next());
        System.out.print(iterator.next());
    }
} 


} 
