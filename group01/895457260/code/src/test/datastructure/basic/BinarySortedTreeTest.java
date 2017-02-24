package test.datastructure.basic;

import datastructure.basic.BinarySortedTree;
import datastructure.basic.BinaryTreeNode;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

/**
 * BinarySortedTree Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>二月 24, 2017</pre>
 */
public class BinarySortedTreeTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    private BinarySortedTree<Integer> getTree() {
        return new BinarySortedTree<>();
    }

    /**
     * Method: add(T o)
     */
    @Test
    public void testAdd() throws Exception {
//TODO: Test goes here...
        BinarySortedTree<Integer> tree = getTree();
        int[] addValues = {5, 3, 1, 7, 6, 4, 8};
        for (int i : addValues) {
            tree.add(i);
        }

        final Object[] left = new Object[addValues.length];
        final Object[] value = new Object[addValues.length];
        final Object[] right = new Object[addValues.length];
        tree.traverse(new BinarySortedTree.Visitor() {
            int pos = 0;
            @Override
            public void visit(BinaryTreeNode node) {
                left[pos] = node.getLeft() == null ? null : (int) node.getLeft().getData();
                value[pos] = node.getData();
                right[pos] = node.getRight() == null ? null : (int) node.getRight().getData();
                pos++;
            }
        });
        Assert.assertArrayEquals(left, new Object[]{null, 1, null, 3, null, 6, null});
        Assert.assertArrayEquals(value, new Object[]{1, 3, 4, 5, 6, 7, 8});
        Assert.assertArrayEquals(right, new Object[]{null, 4, null, 7, null, 8, null});
    }
} 
