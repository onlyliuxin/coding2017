package test.com.coding.basic;


import com.coding.basic.Iterator;
import com.coding.basic.LinkedList;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class LinkedListTest {
    int[] a={0,101,202,303,404,505,606};
LinkedList linkedList ;
@Before
public void before() throws Exception {
  linkedList =new LinkedList(a);
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
    linkedList.add(5);
    int[] b={0,101,202,303,404,505,606,5};
    Assert.assertEquals((new LinkedList(b)).toString(),linkedList.toString());
}

/**
*
* Method: add(int index, Object o)
*
*/
@Test
public void testAddForIndexO() throws Exception {
    linkedList.add(2,5);
    int[] b={0,101,5,202,303,404,505,606};
    Assert.assertEquals((new LinkedList(b)).toString(),linkedList.toString());
}

/**
*
* Method: get(int index)
*
*/
@Test
public void testGet() throws Exception {
    Assert.assertEquals(505,linkedList.get(5));
}

/**
*
* Method: remove(int index)
*
*/
@Test
public void testRemoveIndex() throws Exception {
    int[] b={0,101,202,303,404,606};
    Assert.assertEquals(505,linkedList.remove(5));
    Assert.assertEquals((new LinkedList(b)).toString(),linkedList.toString());
}

/**
*
* Method: size()
*
*/
@Test
public void testSize() throws Exception {
    Assert.assertEquals(7,linkedList.size());
}

/**
*
* Method: addFirst(Object o)
*
*/
@Test
public void testAddFirst() throws Exception {
    int[] b={-99,0,101,202,303,404,505,606};
    linkedList.addFirst(-99);
    Assert.assertEquals((new LinkedList(b)).toString(),linkedList.toString());
}

/**
*
* Method: addLast(Object o)
*
*/
@Test
public void testAddLast() throws Exception {
    int[] b={0,101,202,303,404,505,606,-99};
    linkedList.addLast(-99);
    Assert.assertEquals((new LinkedList(b)).toString(),linkedList.toString());
}

/**
*

*/
@Test
public void testRemoveFirst() throws Exception {
    int[] b={101,202,303,404,505,606,};
    linkedList.removeFirst();
    Assert.assertEquals((new LinkedList(b)).toString(),linkedList.toString());
}

/**
*
* Method: removeLast()
*
*/
@Test
public void testRemoveLast() throws Exception {
    int[] b={0,101,202,303,404,505};
    linkedList.removeLast();
    Assert.assertEquals((new LinkedList(b)).toString(),linkedList.toString());
}

/**
*
* Method: iterator()
*
*/
@Test
public void testIterator() throws Exception {
 LinkedList temp=new LinkedList();
    Iterator iterator=linkedList.iterator();
    while (iterator.hasNext()){
        temp.add(iterator.next());
    }
    Assert.assertEquals(temp.toString(),linkedList.toString());
}

/**
*
* Method: reverse()
*
*/
@Test
public void testReverse() throws Exception {
    int[] b={9,7,3};
    int[] a={3,7,9};
    linkedList=new LinkedList(a);
    linkedList.reverse();
    Assert.assertEquals((new LinkedList(b)).toString(),linkedList.toString());
}

/**
*
 /**
 * 删除一个单链表的前半部分
 * 例如：list = 2->5->7->8 , 删除以后的值为 7->8 [4 2
 * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10  [5 2


 */
@Test
public void testRemoveFirstHalf() throws Exception {


    int[] b={303,404,505,606};

    linkedList.removeFirstHalf();
    Assert.assertEquals((new LinkedList(b)).toString(),linkedList.toString());
}

/**
*
* Method: remove(int i, int length)
*
*/
@Test
public void testRemoveForILength() throws Exception {
    int[] b={0,101,404,505,606};
    linkedList.remove(2,2);
    Assert.assertEquals((new LinkedList(b)).toString(),linkedList.toString());
} 

/** 
* 
* Method: getElements(LinkedList list)
 * 11->101->202->301->401->501->601->701
 * listB = 1->3->4->6
 * 返回的结果应该是[101,301,401,601]
* 
*/ 
@Test
public void testGetElements() throws Exception {
    int[] b={1,3,4,6};
   int[] c = linkedList.getElements(new LinkedList(b));
   int[] d={101,303,404,606};
    Assert.assertEquals(new LinkedList(d).toString(),new LinkedList(c).toString());
} 

/** 
* 
* Method: subtract(LinkedList list) 
* 
*/ 
@Test
public void testSubtract() throws Exception {
    int[] b={1,303,606};
    LinkedList list2=new LinkedList(b);
    linkedList.subtract(list2);

    int[] result={0,101,202,404,505};
    Assert.assertEquals(new LinkedList(result).toString(),linkedList.toString());
} 

/** 
* 
* Method: removeDuplicateValues() 
* 
*/ 
@Test
public void testRemoveDuplicateValues() throws Exception {
    int[] a={1,1,1,1,2,2,2,3,3,4,5,6,7,7,7,8,9,11,11,11};
    int[] b={1,2,3,4,5,6,7,8,9,11};
   LinkedList ah= new LinkedList(a);
    ah.removeDuplicateValues();
    Assert.assertEquals(new LinkedList(b).toString(),ah.toString());
} 

/** 
* 
* Method: removeRange(int min, int max) 
* 
*/ 
@Test
public void testRemoveRange() throws Exception {
    int[] a={0,101,202,303,404,505,606};
    int[] b={0,101,        404,505,606};
    LinkedList bl=new LinkedList(b);
    linkedList.removeRange(200,400);
    Assert.assertEquals(bl.toString(),linkedList.toString());
} 

/** 
* 
* Method: intersection(LinkedList list) 
* 
*/ 
@Test
public void testIntersection() throws Exception {
    int[] a={0,101,202,303,404        };
    int[] b={0,101,        404,505,606};
    int[] c={0,101,        404};
    LinkedList bl=new LinkedList(b);
    LinkedList al=new LinkedList(a);
    LinkedList cl=new LinkedList(c);

    Assert.assertEquals(cl.toString(),al.intersection(bl).toString());
} 


} 
