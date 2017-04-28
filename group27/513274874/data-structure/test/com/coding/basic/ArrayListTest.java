package com.coding.basic;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

/** 
* ArrayList Tester. 
* 
* @author <Authors name> 
* @since <pre>三月 6, 2017</pre> 
* @version 1.0 
*/ 
public class ArrayListTest { 

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
   ArrayList arrayList = new com.coding.basic.ArrayList();
   arrayList.add(100);
   arrayList.add(20);

   for(int i = 1 ;i <= 200 ;i++){
      arrayList.add(new Random().nextInt(100));
   }

   System.out.println(arrayList);

   assert(arrayList.size() == 202);
}

/** 
* 
* Method: add(int index, Object o) 
* 
*/ 
@Test
public void testAddForIndexO() throws Exception {
   ArrayList arrayList = new com.coding.basic.ArrayList();
   arrayList.add(1);
   arrayList.add(2);
   arrayList.add(3);
   arrayList.add(4);
   arrayList.add(5);

   arrayList.add(3,"添加");
   //arrayList.add(100,3);
   assert(arrayList.size() == 6);
   System.out.println(arrayList);
} 

/** 
* 
* Method: get(int index) 
* 
*/ 
@Test
public void testGet() throws Exception {
   ArrayList arrayList = new com.coding.basic.ArrayList();
   arrayList.add(1);
   arrayList.add(2);
   arrayList.add(3);
   arrayList.add(4);
   arrayList.add(5);

   assert(((Integer)arrayList.get(3)).intValue() == 4);
} 

/** 
* 
* Method: remove(int index) 
* 
*/ 
@Test
public void testRemove() throws Exception {
   ArrayList arrayList = new com.coding.basic.ArrayList();
   arrayList.add(1);
   arrayList.add(2);
   arrayList.add(3);
   arrayList.add(4);
   arrayList.add(5);

   arrayList.remove(3);
   //arrayList.add(100,3);
   assert(arrayList.size() == 4);
   System.out.println(arrayList);
} 

/** 
* 
* Method: size() 
* 
*/ 
@Test
public void testSize() throws Exception { 
//TODO: Test goes here...

   ArrayList arrayList = new com.coding.basic.ArrayList();
   arrayList.add(100);
   arrayList.add(20);

   for(int i = 1 ;i <= 200 ;i++){
      arrayList.add(new Random().nextInt(100));
   }

   System.out.println(arrayList);

   assert(arrayList.size() == 202);
} 

/** 
* 
* Method: iterator() 
* 
*/ 
@Test
public void testIterator() throws Exception { 
//TODO: Test goes here...
   ArrayList arrayList = new com.coding.basic.ArrayList();
   arrayList.add(100);
   arrayList.add(20);

   for(int i = 1 ;i <= 200 ;i++){
      arrayList.add(new Random().nextInt(100));
   }
   System.out.println(arrayList);

   Iterator iterator = arrayList.iterator();
   while(iterator.hasNext()){
      System.out.print(iterator.next() + ",");
   }

   assert(arrayList.size() == 202);
}

} 
