package TestCollection;

import static util.Print.*;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import static util.TestUtil.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Collection.Concrete.BinaryTreeNode;
import junit.framework.TestCase;

public class BinaryTreeNodeTest extends TestCase {

	private BinaryTreeNode<Integer> myTree;
	
	@Before
	public void setUp() throws Exception {
		myTree = new BinaryTreeNode<Integer>();
		assertEquals(0, myTree.size());
	}

	@After
	public void tearDown() throws Exception {
		myTree = null;
	}

	@Test
	public void testInsert() {
		Set<Integer> expected =  new TreeSet<Integer>();
		int size = getRandomNumber();
		int j = 0 ;
		while (expected.size() != size) {
			j = getRandomNumber();
			expected.add(j);
			myTree.insert(j);
		}
		
		assertEquals(size,myTree.size());
		assertEquals(expected.toString(),myTree.toString());
	}
	
	public void testSize() {
		
		for (int i = 0; i < getRandomNumber(); i++) {
			myTree.insert(18);
			myTree.insert(-19);
			myTree.insert(1);
			assertEquals(3,myTree.size());
		}	
	}
}