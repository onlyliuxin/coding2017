package week1.com.coding.Test;

import org.junit.Test;

import week1.com.coding.basic.BinaryTreeNode;

public class BinaryTreeNodeTest
{
    
    @Test
    public void test()
    {
        BinaryTreeNode baiseTreeNode = new BinaryTreeNode("���ڵ�");
        BinaryTreeNode.TreeNode tn1 = baiseTreeNode.insert("�ڶ�����ڵ�", baiseTreeNode.root());
        BinaryTreeNode.TreeNode tn2 = baiseTreeNode.insert("�ڶ����ҽڵ�", baiseTreeNode.root());
        BinaryTreeNode.TreeNode tn3 = baiseTreeNode.insert("��������ڵ�", tn1);
        BinaryTreeNode.TreeNode tn4 = baiseTreeNode.insert("�������ҽڵ�", tn1);
    }
    
}
