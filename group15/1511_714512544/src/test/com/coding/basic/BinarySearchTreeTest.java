package test.com.coding.basic;

import static org.junit.Assert.*;

import com.coding.basic.BinarySearchTree;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BinarySearchTreeTest {
	BinarySearchTree<Integer> bst = null;
	@Before
	public void setUp() throws Exception {
		bst = new BinarySearchTree<Integer>();
		bst.insert(5);
		bst.insert(2);
		bst.insert(7);
		bst.insert(1);
		bst.insert(6);
		bst.insert(4);
		bst.insert(8);
	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void testInsert() {
	}

	@Test
	public void testContains() {
		assertEquals(true,bst.contains(8));
	}
	
	@Test
	public void testPreOrder(){
		bst.preOrder(bst.getRoot());
	}

	@Test
	public void testPreOrderWithoutRecursion(){
		bst.preOrderWithoutRecursion();
	}
	
	@Test
	public void testMidOrder(){
		bst.midOrder(bst.getRoot());
	}

	@Test
	public void testMidOrderWithoutRecursion(){
		bst.midOrderWithoutRecursion();
	}
	
	@Test
	public void testPostOrder(){
		bst.postOrder(bst.getRoot());
	}

	@Test
	public void testPostOrderWithoutRecursion(){
		bst.postOrderWithoutRecursion();
	}

	@Test
	public void traveralByLevel(){
		bst.traveralByLevel(bst.getRoot());
	}

	@Test
	public void postOrderGetHeight(){
		int height = bst.postOrderGetHeight(bst.getRoot());
		assertEquals(3, height);
	}


}
