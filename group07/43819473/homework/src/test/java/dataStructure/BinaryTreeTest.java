package dataStructure;

import com.alibaba.fastjson.JSON;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by zj on 2017/2/26.
 */
public class BinaryTreeTest {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void insert() throws Exception {
        BinaryTree tree=new BinaryTree();
        tree.insert(6);
        tree.insert(5);
        tree.insert(8);
        tree.insert(3);
        tree.insert(4);
        System.out.println(JSON.toJSONString(tree.getRoot()));
    }
}