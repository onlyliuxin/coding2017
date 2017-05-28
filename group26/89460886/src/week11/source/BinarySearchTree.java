package tree;

import org.junit.Assert;

import java.util.List;

/**
 * @author jiaxun
 */
public class BinarySearchTree<T extends Comparable> {

    BinaryTreeNode<T> root;
    public BinarySearchTree(BinaryTreeNode<T> root) {
        this.root = root;
    }

    public BinaryTreeNode<T> getRoot() {
        return root;
    }

    public T findMin() {
        if (root == null) return null;
        BinaryTreeNode<T> curr = root;
        while (curr.getLeft() != null) {
            curr = curr.getLeft();
        }
        return curr.getData();
    }

    public BinaryTreeNode<T> findMin(BinaryTreeNode<T> treeNode) {
        if (treeNode == null) return null;
        BinaryTreeNode<T> curr = treeNode;
        while (curr.getLeft() != null) {
            curr = curr.getLeft();
        }
        return curr;
    }

    public T findMax() {
        if (root == null) return null;
        BinaryTreeNode<T> curr = root;
        while (curr.getRight() != null) {
            curr = curr.getRight();
        }
        return curr.getData();
    }

    public int height() {
        return getNodeHeight(root);
    }

    private int getNodeHeight(BinaryTreeNode<T> treeNode) {
        if (treeNode == null) return 0;
        int leftChild = getNodeHeight(treeNode.getLeft()) + 1;
        int rightChild = getNodeHeight(treeNode.getRight()) + 1;
        return Math.max(leftChild, rightChild);
    }

    public int size() {
        return getNodeSize(root);
    }

    private int getNodeSize(BinaryTreeNode<T> treeNode) {
        if (treeNode == null) return 0;
        int leftSize = getNodeSize(treeNode.getLeft());
        int rightSize = getNodeSize(treeNode.getRight());
        return leftSize + rightSize + 1;
    }

    public void remove(T t) {
        remove(t, root);
    }

    private BinaryTreeNode<T> remove(T t, BinaryTreeNode<T> treeNode) {
        if (treeNode == null) return treeNode;
        int compareResult = t.compareTo(treeNode.getData());
        if (compareResult < 0) {
            treeNode.setLeft(remove(t, treeNode.getLeft()));
        } else if (compareResult > 0) {
            treeNode.setRight(remove(t, treeNode.getRight()));
        } else if (treeNode.getLeft() != null && treeNode.getRight() != null) {
            treeNode.setData(findMin(treeNode.getRight()).getData());
            treeNode.setRight(remove(treeNode.getData(), treeNode.getRight()));
        } else {
            treeNode = treeNode.getLeft() != null ? treeNode.getLeft() : treeNode.getRight();
        }
        return treeNode;
    }

    public List<T> levelVisit(){

        return null;
    }

    public boolean isValid(){
        return false;
    }

    public T getLowestCommonAncestor(T n1, T n2){
        return null;

    }

    public List<T> getNodesBetween(T n1, T n2){
        return null;
    }

}
