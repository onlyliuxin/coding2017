package test.com.coding.basic;

import static org.junit.Assert.*;

import com.coding.basic.BinarySearchTree;
import org.junit.Test;

public class BinarySearchTreeTest {

	@Test
	public void testInsert() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		bst.insert(5);
		bst.insert(2);
		bst.insert(7);
		bst.insert(1);
		bst.insert(6);
		bst.insert(4);
		bst.insert(8);
	}

	@Test
	public void testContains() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		bst.insert(5);
		bst.insert(2);
		bst.insert(7);
		bst.insert(1);
		bst.insert(6);
		bst.insert(4);
		bst.insert(8);
		assertEquals(true,bst.contains(8));
	}
	
	@Test
	public void testPreOrder(){
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		bst.insert(5);
		bst.insert(2);
		bst.insert(7);
		bst.insert(1);
		bst.insert(6);
		bst.insert(4);
		bst.insert(8);
		bst.preOrder(bst.getRoot());
	}

	@Test
	public void testPreOrderWithoutRecursion(){
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		bst.insert(5);
		bst.insert(2);
		bst.insert(7);
		bst.insert(1);
		bst.insert(6);
		bst.insert(4);
		bst.insert(8);
		bst.preOrderWithoutRecursion();
	}
	
	@Test
	public void testMidOrder(){
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		bst.insert(5);
		bst.insert(2);
		bst.insert(7);
		bst.insert(1);
		bst.insert(6);
		bst.insert(4);
		bst.insert(8);
		bst.midOrder(bst.getRoot());
	}

	@Test
	public void testMidOrderWithoutRecursion(){
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		bst.insert(5);
		bst.insert(2);
		bst.insert(7);
		bst.insert(1);
		bst.insert(6);
		bst.insert(4);
		bst.insert(8);
		bst.midOrderWithoutRecursion();
	}
	
	@Test
	public void testPostOrder(){
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		bst.insert(5);
		bst.insert(2);
		bst.insert(7);
		bst.insert(1);
		bst.insert(6);
		bst.insert(4);
		bst.insert(8);
		bst.postOrder(bst.getRoot());
	}

	@Test
	public void testPostOrderWithoutRecursion(){
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		bst.insert(5);
		bst.insert(2);
		bst.insert(7);
		bst.insert(1);
		bst.insert(6);
		bst.insert(4);
		bst.insert(8);
		bst.postOrderWithoutRecursion();
	}

}
