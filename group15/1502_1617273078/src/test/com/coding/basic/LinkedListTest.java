package test.com.coding.basic; 

import com.coding.basic.Iterator;
import com.coding.basic.LinkedList;
import junit.framework.Test; 
import junit.framework.TestSuite; 
import junit.framework.TestCase; 

/** 
* LinkedList Tester. 
* 
* @author <Authors name> 
* @since <pre>03/12/2017</pre> 
* @version 1.0 
*/ 
public class LinkedListTest extends TestCase { 
public LinkedListTest(String name) { 
super(name); 
} 

public void setUp() throws Exception { 
super.setUp(); 
} 

public void tearDown() throws Exception { 
super.tearDown(); 
} 

/** 
* 
* Method: add(Object o) 
* 
*/ 
public void testAddO() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: add(int index, Object o) 
* 
*/ 
public void testAddForIndexO() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: get(int index) 
* 
*/ 
public void testGet() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: remove(int index) 
* 
*/ 
public void testRemoveIndex() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: size() 
* 
*/ 
public void testSize() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: addFirst(Object o) 
* 
*/ 
public void testAddFirst() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: addLast(Object o) 
* 
*/ 
public void testAddLast() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: removeFirst() 
* 
*/ 
public void testRemoveFirst() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: removeLast() 
* 
*/ 
public void testRemoveLast() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: iterator() 
* 
*/ 
public void testIterator() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: reverse() 
* 
*/ 
public void testReverse() throws Exception { 
//TODO: Test goes here... 
    LinkedList list = new LinkedList();
    list.add(3);
    list.add(8);
    list.add(10);
    list.reverse();
   // list.addFirst(2);
    System.out.println(list.size());
    Iterator iterator = list.iterator();
    while (iterator.hasNext()) {
       // String s= new String(iterator.next());
        System.out.print(iterator.next());
    }
    //System.out.println(list.get(1));
} 

/** 
* 
* Method: removeFirstHalf() 
* 
*/ 
public void testRemoveFirstHalf() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: remove(int i, int length) 
* 
*/ 
public void testRemoveForILength() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getElements(LinkedList list) 
* 
*/ 
public void testGetElements() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: subtract(LinkedList list) 
* 
*/ 
public void testSubtract() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: removeDuplicateValues() 
* 
*/ 
public void testRemoveDuplicateValues() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: removeRange(int min, int max) 
* 
*/ 
public void testRemoveRange() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: intersection(LinkedList list) 
* 
*/ 
public void testIntersection() throws Exception { 
//TODO: Test goes here... 
} 



public static Test suite() { 
return new TestSuite(LinkedListTest.class); 
} 
} 
