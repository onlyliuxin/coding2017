package cn.cs.week1.basic;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by YCS on 2017/3/12.
 */
public class BinaryTreeTest {
  @Test
  public void test(){
    BinaryTreeNode myRoot= new BinaryTreeNode();
    BinaryTreeNode myNode1= new BinaryTreeNode();
    BinaryTreeNode myNode2= new BinaryTreeNode();
    //setXXX()
    myRoot.setData(5);
    myNode1.setData(3);
    myNode2.setData(7);
    myRoot.setLeft(myNode1);
    myRoot.setRight(myNode2);
    //insert();
    myRoot.insert(1);
    myRoot.insert(6);
    myRoot.insert(8);
    //getXXX()
    Assert.assertEquals(myRoot.getLeft().getLeft().getData(),1);
    Assert.assertEquals(myRoot.getRight().getLeft().getData(),6);
    Assert.assertEquals(myRoot.getRight().getRight().getData(),8);
  }
}
