package basic.tree;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;



public class BinarySearchTreeTest {
	
	BinarySearchTree<Integer> tree = null;

	/**      6
	 *     2   8
	 *   1   4
	 *      3
 	 * @throws Exception
	 */
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
		tree.remove1(4);
		BinaryTreeNode<Integer> root= tree.getRoot();
		Assert.assertEquals(3, root.left.right.data.intValue());
		
	}
	@Test
	public void testRemoveMiddleNode() {
		tree.remove1(2);
		BinaryTreeNode<Integer> root= tree.getRoot();
		Assert.assertEquals(3, root.left.data.intValue());
		Assert.assertEquals(4, root.left.right.data.intValue());
	}

	@Test
	public void testRemove() {
		tree.remove1(6);
		BinaryTreeNode<Integer> root= tree.getRoot();
		Assert.assertEquals(2, root.left.data.intValue());
		Assert.assertEquals(8, root.data.intValue());
		//Assert.assertEquals(null, root.left);
	}

	@Test
	public void testLevelVisit() {
		String str = tree.levelVisit().toString();

		Assert.assertEquals("[6, 2, 8, 1, 4, 3]", str);

	}

	@Test
	public void testIsValid() {

		Assert.assertEquals(true, tree.isValid());

	}

	@Test
	public void testGetLowestCommonAncestor() {

		Assert.assertEquals(3, (int)tree.getLowestCommonAncestor(2, 8));

	}

	@Test
	public void testGetNodesBetween() {
		String str = tree.getNodesBetween(1, 4).toString();
		Assert.assertEquals("[2, 3]", str);

		str = tree.getNodesBetween(0, 9).toString();
		Assert.assertEquals("[6, 2, 8, 1, 4, 3]", str);


	}
}
