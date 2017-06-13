package week1.com.coding.Test;

import org.junit.Test;

import week1.com.coding.basic.BinaryTreeNode;

public class BinaryTreeNodeTest
{
    
    @Test
    public void test()
    {
        BinaryTreeNode baiseTreeNode = new BinaryTreeNode("根节点");
        BinaryTreeNode.TreeNode tn1 = baiseTreeNode.insert("第二层左节点", baiseTreeNode.root());
        BinaryTreeNode.TreeNode tn2 = baiseTreeNode.insert("第二层右节点", baiseTreeNode.root());
        BinaryTreeNode.TreeNode tn3 = baiseTreeNode.insert("第三层左节点", tn1);
        BinaryTreeNode.TreeNode tn4 = baiseTreeNode.insert("第三层右节点", tn1);
    }
    
}
