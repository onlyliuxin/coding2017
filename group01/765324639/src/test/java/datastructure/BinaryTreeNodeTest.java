package datastructure;

import org.junit.Assert;
import org.junit.Test;

public class BinaryTreeNodeTest {

    private BinaryTreeNode root = new BinaryTreeNode(5);

    @Test
    public void testInsert() {
        root.insert(2);
        root.insert(7);
        root.insert(1);
        root.insert(6);

        Assert.assertEquals((Integer) 5, root.getData());
        Assert.assertEquals((Integer) 2, root.getLeft().getData());
        Assert.assertEquals((Integer) 1, root.getLeft().getLeft().getData());
        Assert.assertEquals(null, root.getLeft().getRight());
        Assert.assertEquals((Integer) 7, root.getRight().getData());
        Assert.assertEquals((Integer) 6, root.getRight().getLeft().getData());
        Assert.assertEquals(null, root.getRight().getRight());

        root.insert(4);
        root.insert(8);
        Assert.assertEquals((Integer) 4, root.getLeft().getRight().getData());
        Assert.assertEquals((Integer) 8, root.getRight().getRight().getData());
    }

}
