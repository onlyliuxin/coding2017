package test.com.coding.basic; 

import com.coding.basic.BinarySearchTree;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After; 

/** 
* BinarySearchTree Tester. 
* 
* @author <Authors name> 
* @since <pre>¶þÔÂ 23, 2017</pre> 
* @version 1.0 
*/ 
public class BinarySearchTreeTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: getRoot() 
* 
*/ 
@Test
public void testGetRoot() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: setRoot(BinarySearchTreeNode<T> root) 
* 
*/ 
@Test
public void testSetRoot() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: insert(T data) 
* 
*/ 
@Test
public void testInsert() throws Exception {
    BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
    bst.insert(5);
    bst.insert(2);
    bst.insert(7);
    bst.insert(1);
    bst.insert(6);
    bst.insert(4);
    bst.insert(8);
} 

/** 
* 
* Method: contains(T data) 
* 
*/ 
@Test
public void testContains() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: preOrder(BinarySearchTreeNode<T> n) 
* 
*/ 
@Test
public void testPreOrder() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: midOrder(BinarySearchTreeNode<T> n) 
* 
*/ 
@Test
public void testMidOrder() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: postOrder(BinarySearchTreeNode<T> n) 
* 
*/ 
@Test
public void testPostOrder() throws Exception { 
//TODO: Test goes here... 
} 


} 
