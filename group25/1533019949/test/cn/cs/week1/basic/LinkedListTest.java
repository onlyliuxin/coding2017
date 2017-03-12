package cn.cs.week1.basic;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by YCS on 2017/3/12.
 */
public class LinkedListTest {
  LinkedList myLinkedList = new LinkedList();
  @Test
  public void test(){
    //size()
    Assert.assertEquals(myLinkedList.size(),0);
    //add()
    myLinkedList.add(2);
    //addFirst()
    myLinkedList.addFirst(1);
    //addLast()
    myLinkedList.addLast(3);
    //iterator()
    int[] r1 = {1,2,3};
    int i = 0;
    Iterator iterator = myLinkedList.iterator();
    while(iterator.hasNext()){
      Assert.assertEquals(iterator.next(),r1[i++]);
    }
    //size()
    Assert.assertEquals(myLinkedList.size(),3);
    //get()
    Assert.assertEquals(myLinkedList.get(0),1);
    Assert.assertEquals(myLinkedList.get(1),2);
    //remove()
    Assert.assertEquals(myLinkedList.remove(1),2);
    //removeFirst()
    Assert.assertEquals(myLinkedList.removeFirst(),1);
    //removeLast()
    Assert.assertEquals(myLinkedList.removeLast(),3);
    //size()
    Assert.assertEquals(myLinkedList.size(),0);
  }
}
