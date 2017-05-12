package dataStruct.com.coding.basic;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by songbao.yang on 2017/2/28.
 *
 */
public class BinaryTreeNodeTest {

    private BinaryTreeNode node;

    @Before
    public void setUp() throws Exception {
        node = new BinaryTreeNode();
        node.setData(100);
        node.setLeft(null);
        node.setRight(null);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void insert() throws Exception {

        for (int i = 0; i < 100; i++) {
            int ele = (int)Math.floor(Math.random() * 200);
            node.insert(ele);
        }
    }
}