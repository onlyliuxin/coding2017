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
    Iterator iterator = list.iterator();
    while (iterator.hasNext()) {
        System.out.print(iterator.next()+" ");
    }
}

/** 
* 
* Method: removeFirstHalf() 
* 
*/ 
public void testRemoveFirstHalf() throws Exception { 
//TODO: Test goes here...
    LinkedList list = new LinkedList();
    list.add(2);

    list.add(3);
    list.add(8);
    list.add(10);
    list.add(11);

    list.removeFirstHalf();
    // list.addFirst(2);
    //System.out.println(list.size());
    Iterator iterator = list.iterator();
    while (iterator.hasNext()) {
        // String s= new String(iterator.next());
        System.out.print(iterator.next()+" ");
    }
} 

/** 
* 
* Method: remove(int i, int length) 
* 
*/ 
public void testRemoveForILength() throws Exception { 
//TODO: Test goes here...
    LinkedList list = new LinkedList();
    list.add(2);

    list.add(3);
    list.add(8);
    list.add(10);
    list.add(11);

    list.remove(3,6);
    // list.addFirst(2);
    System.out.println(list.size());
    Iterator iterator = list.iterator();
    while (iterator.hasNext()) {
        // String s= new String(iterator.next());
        System.out.print(iterator.next()+" ");
    }

} 

/** 
* 
* Method: getElements(LinkedList list) 
* 
*/ 
public void testGetElements() throws Exception { 
//TODO: Test goes here...
    LinkedList list = new LinkedList();
    list.add(11);
    list.add(101);
    list.add(201);
    list.add(301);
    list.add(401);
    list.add(501);
    list.add(601);
    list.add(701);
    //list.remove(3,6);
    LinkedList listb = new LinkedList();
    listb.add(1);
    listb.add(3);
    listb.add(4);
    listb.add(6);
    int[] res;
    res=list.getElements(listb);
    //System.out.println(list.size());
    for (int i = 0; i <res.length; i++) {
        System.out.print(res[i]+" ");
    }
} 

/** 
* 
* Method: subtract(LinkedList list) 
* 
*/ 
public void testSubtract() throws Exception { 
//TODO: Test goes here...
    LinkedList list = new LinkedList();
    list.add(11);
    list.add(101);
    list.add(201);
    list.add(301);
    list.add(401);
    list.add(501);
    list.add(601);
    list.add(701);
    //list.remove(3,6);
    LinkedList listb = new LinkedList();
    listb.add(101);
    listb.add(701);
/*
    listb.add(301);
    listb.add(401);
    listb.add(601);
*/

    list.subtract(listb);
    Iterator iterator = list.iterator();
    while (iterator.hasNext()) {
        System.out.print(iterator.next()+"  ");
    }
} 

/** 
* 
* Method: removeDuplicateValues() 
* 
*/ 
public void testRemoveDuplicateValues() throws Exception { 
//TODO: Test goes here...
    LinkedList list = new LinkedList();
    list.add(11);
    list.add(201);
    list.add(301);
    list.add(301);
    list.removeDuplicateValues();
    Iterator iterator = list.iterator();
    while (iterator.hasNext()) {
        System.out.print(iterator.next()+"  ");
    }
} 

/** 
* 
* Method: removeRange(int min, int max) 
* 
*/ 
public void testRemoveRange() throws Exception { 
//TODO: Test goes here...
    LinkedList list = new LinkedList();
    list.add(11);
    list.add(101);
    list.add(201);
    list.add(301);
    list.add(401);
    list.add(501);
    list.add(601);
    list.add(701);

    list.removeRange(222,555);
    Iterator iterator = list.iterator();
    while (iterator.hasNext()) {
        System.out.print(iterator.next()+"  ");
    }
} 

/** 
* 
* Method: intersection(LinkedList list) 
* 
*/ 
public void testIntersection() throws Exception { 
//TODO: Test goes here...
    LinkedList list = new LinkedList();
    LinkedList list1 = new LinkedList();
    LinkedList list2 = new LinkedList();
    list.add(11);
    list.add(101);
    list.add(201);
    list.add(301);
    list.add(401);
    list.add(501);
    list.add(601);
    list.add(701);
    list1.add(501);
    list1.add(601);
    list1.add(701);
    list2=list.intersection(list1);
    Iterator iterator = list2.iterator();
    while (iterator.hasNext()) {
        System.out.print(iterator.next()+"  ");
    }
} 



public static Test suite() { 
return new TestSuite(LinkedListTest.class); 
} 
} 
