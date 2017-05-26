package com.coding.basic.tree;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class BinarySearchTreeTest {
	
	BinarySearchTree<Integer> tree = null;
	
	@Before
	public void setUp() throws Exception {
		BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>(6);
		root.left = new BinaryTreeNode<Integer>(2);
		root.right = new BinaryTreeNode<Integer>(8);
		root.left.left = new BinaryTreeNode<Integer>(1);
		root.left.right = new BinaryTreeNode<Integer>(4);
		root.left.right.left = new BinaryTreeNode<Integer>(3);
		tree = new BinarySearchTree<Integer>(root);
	}

	@After
	public void tearDown() throws Exception {
		tree = null;
	}

	@Test
	public void testFindMin() {
		Assert.assertEquals(1, tree.findMin().intValue());
		
	}

	@Test
	public void testFindMax() {
		Assert.assertEquals(8, tree.findMax().intValue());
	}

	@Test
	public void testHeight() {
		Assert.assertEquals(4, tree.height());
	}

	@Test
	public void testSize() {
		Assert.assertEquals(6, tree.size());
	}

	@Test
	public void testRemoveLeaf() {
		tree.remove(4);
		BinaryTreeNode<Integer> root= tree.getRoot();
		Assert.assertEquals(3, root.left.right.data.intValue());
		
	}
	@Test
	public void testRemoveMiddleNode() {
		tree.remove(2);
		BinaryTreeNode<Integer> root= tree.getRoot();
		Assert.assertEquals(3, root.left.data.intValue());
		Assert.assertEquals(4, root.left.right.data.intValue());
	}
	@Test
	public void testLevelVisit(){
		tree.getRoot().left.right.right=new BinaryTreeNode<Integer>(5);
		List<Integer> levelList=tree.levelVisit();
		for(Integer i:levelList){
			System.out.println(i);
		}
	}
	@Test
	public void testIsValid(){
		BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>(6);
		root.left = new BinaryTreeNode<Integer>(2);
		root.right = new BinaryTreeNode<Integer>(8);
		root.left.left = new BinaryTreeNode<Integer>(1);
		root.left.right = new BinaryTreeNode<Integer>(4);
		root.left.right.left = new BinaryTreeNode<Integer>(3);
		{
			BinarySearchTree<Integer>Valid=new BinarySearchTree<>(root);
			Assert.assertEquals(true,Valid.isValid());
		}
		{
			root.left.left.left=new BinaryTreeNode<Integer>(5);
			BinarySearchTree<Integer>disValid=new BinarySearchTree<>(root);
			Assert.assertEquals(false,disValid.isValid());
		}
		{
			root.left.left.right=new BinaryTreeNode<Integer>(0);
			BinarySearchTree<Integer>disValid=new BinarySearchTree<>(root);
			Assert.assertEquals(false,disValid.isValid());
		}
		{
			root.left.right.right=new BinaryTreeNode<Integer>(9);
			BinarySearchTree<Integer>disValid=new BinarySearchTree<>(root);
			Assert.assertEquals(false, disValid.isValid());
		}
		{
			root.right.left=new BinaryTreeNode<Integer>(4);
			BinarySearchTree<Integer>disValid=new BinarySearchTree<>(root);
			Assert.assertEquals(false,disValid.isValid());
		}
	}
	@Test
	public void testGetLowestCommonAncestor(){
		int ancestor;
		{
			ancestor=tree.getLowestCommonAncestor(1, 4);
			Assert.assertEquals(2, ancestor);
		}
		{
			ancestor=tree.getLowestCommonAncestor(1, 3);
			Assert.assertEquals(2, ancestor);
			
		}
		{
			ancestor=tree.getLowestCommonAncestor(1, 8);
			Assert.assertEquals(6, ancestor);
		}
		{
			ancestor=tree.getLowestCommonAncestor(1, 2);
			Assert.assertEquals(2, ancestor);
		}
		{
			ancestor=tree.getLowestCommonAncestor(2, 4);
			Assert.assertEquals(2, ancestor);
		}
		{
			ancestor=tree.getLowestCommonAncestor(4, 6);
			Assert.assertEquals(6, ancestor);
		}
		{
			ancestor=tree.getLowestCommonAncestor(4, 4);
			Assert.assertEquals(4, ancestor);
		}
	}
	@Test
	public void testGetNodesBetween() {
		List<Integer> nodes = null;
		{
			nodes = tree.getNodesBetween(1, 8);
			System.out.print("1-8:");
			for (Integer i : nodes) {
				System.out.print(i+"-");
			}
			System.out.println();
		}
		{
			nodes=tree.getNodesBetween(2, 4);
			System.out.print("2-4:");
			for (Integer i : nodes) {
				System.out.print(i+"-");
			}
			System.out.println();
		}
		{
			nodes=tree.getNodesBetween(6, 4);
			System.out.print("6-4:");
			for (Integer i : nodes) {
				System.out.print(i+"-");
			}
			System.out.println();
		}
		{
			nodes=tree.getNodesBetween(3, 4);
			System.out.print("3-4:");
			for (Integer i : nodes) {
				System.out.print(i+"-");
			}
			System.out.println();
		}
		{
			nodes=tree.getNodesBetween(3, 8);
			System.out.print("3-8:");
			for (Integer i : nodes) {
				System.out.print(i+"-");
			}
			System.out.println();
		}
		{
			tree.getRoot().left.right.right=new BinaryTreeNode<Integer>(5);
			nodes=tree.getNodesBetween(6, 4);
			System.out.print("after adding 5:\n6-4:");
			for (Integer i : nodes) {
				System.out.print(i+"-");
			}
			System.out.println();
		}
	}
}
