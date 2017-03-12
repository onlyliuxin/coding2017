package cn.cs.week1.basic;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by YCS on 2017/3/12.
 */
public class ArrayListTest {
  ArrayList myArrayList = new ArrayList();
  @Test
  public void test(){
    //size()
    Assert.assertEquals(myArrayList.size(),0);
    //add()
    myArrayList.add(1);
    myArrayList.add(2);
    Assert.assertEquals(myArrayList.size(),2);
    //get()
    Assert.assertEquals(myArrayList.get(0),1);
    Assert.assertEquals(myArrayList.get(1),2);
    //add(index)
    myArrayList.add(0,0);
    myArrayList.add(1,9);
    myArrayList.add(myArrayList.size(),10);
    Iterator iterator = myArrayList.iterator();
    //iterator()
    int[] results1 = {0,9,1,2,10};
    int i = 0;
    while (iterator.hasNext()){
      Assert.assertEquals(iterator.next(),results1[i++]);
    }
    //remove()
    Assert.assertEquals(myArrayList.remove(0),0);
    myArrayList.remove(2);
    myArrayList.remove(myArrayList.size()-1);
    int[] results2 = {9,1};
    i = 0;
    while (iterator.hasNext()){
      Assert.assertEquals(iterator.next(),results2[i++]);
    }
  }
}
