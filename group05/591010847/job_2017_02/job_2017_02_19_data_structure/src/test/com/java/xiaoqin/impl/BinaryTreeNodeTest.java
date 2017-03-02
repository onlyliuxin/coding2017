package test.com.java.xiaoqin.impl;

import com.java.xiaoqin.impl.BinaryTreeNode;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * BinaryTreeNode Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>二月 27, 2017</pre>
 */
public class BinaryTreeNodeTest {

    private BinaryTreeNode<Integer> binaryTreeNode;


    @Before
    public void before() throws Exception {
        binaryTreeNode = new BinaryTreeNode<>();
    }

    @After
    public void after() throws Exception {
        binaryTreeNode = null;
    }

    /**
     * Method: insert(T o)
     */
    @Test
    public void testInsert() throws Exception {
        binaryTreeNode.insert(5);
        binaryTreeNode.insert(2);
        binaryTreeNode.insert(7);
        binaryTreeNode.insert(1);
        binaryTreeNode.insert(6);
        System.out.println(binaryTreeNode.toString());
        binaryTreeNode.insert(4);
        binaryTreeNode.insert(8);
        System.out.println(binaryTreeNode.toString());
    }

}
