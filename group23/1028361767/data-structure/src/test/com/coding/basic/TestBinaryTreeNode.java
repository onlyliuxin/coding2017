package test.com.coding.basic;

import com.coding.basic.BinaryTreeNode;
import org.junit.Test;

public class TestBinaryTreeNode {

    @Test
    public void testInsert() {
        BinaryTreeNode binaryTree = new BinaryTreeNode(5);
        binaryTree.insert(2);
        binaryTree.insert(7);
        binaryTree.insert(1);
        binaryTree.insert(6);

        printNode(binaryTree);

        binaryTree.insert(4);
        binaryTree.insert(8);

        System.out.println("*************************");
        printNode(binaryTree);
    }

    private void printNode(BinaryTreeNode node) {
        System.out.print("node's data is " + node.getDataIntVal());
        System.out.println("   ,node's parent' data is " + (node.getParent() == null ? "null" : node.getParent().getDataIntVal()));
        if (node.getLeft() != null) {
            System.out.println("find left child node.");
            printNode(node.getLeft());
        }
        if (node.getRight() != null) {
            System.out.println("find right child node.");
            printNode(node.getRight());
        }
    }
}
