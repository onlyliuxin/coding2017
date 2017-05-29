package tree;

import queue.Queue;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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

    public List<T> levelVisit() {
        if (root == null) return null;
        List<T> resultList = new ArrayList<>();
        resultList.add(root.getData());

        Queue<BinaryTreeNode<T>> queue = new Queue<>();
        if (root.getLeft() != null) queue.enQueue(root.getLeft());
        if (root.getRight() != null) queue.enQueue(root.getRight());

        while (!queue.isEmpty()) {
            int count = queue.size();
            while (count > 0) {
                BinaryTreeNode<T> treeNode = queue.deQueue();
                resultList.add(treeNode.getData());
                if (treeNode.getLeft() != null) queue.enQueue(treeNode.getLeft());
                if (treeNode.getRight() != null) queue.enQueue(treeNode.getRight());
                count--;
            }
        }

        return resultList;
    }

    public boolean isValid() {
        if (root == null) return false;
        BinaryTreeNode<T> curr = root;
        Queue<BinaryTreeNode<T>> queue = new Queue<>();
        queue.enQueue(curr);
        while (!queue.isEmpty()) {
            BinaryTreeNode<T> treeNode = queue.deQueue();
            if (treeNode.getLeft() != null) {
                BinaryTreeNode<T> leftChild = treeNode.getLeft();
                if (leftChild.getData().compareTo(treeNode.getData()) > 0) return false;
                queue.enQueue(leftChild);
            }
            if (treeNode.getRight() != null) {
                BinaryTreeNode<T> rightChild = treeNode.getRight();
                if (rightChild.getData().compareTo(treeNode.getData()) < 0) return false;
                queue.enQueue(rightChild);
            }
        }
        return true;
    }

    public T getLowestCommonAncestor(T n1, T n2) {
        if (root == null || n1 == null || n2 == null) return null;
        Stack<BinaryTreeNode<T>> ancestorStack = new Stack<>();
        BinaryTreeNode<T> n1Node = root;
        while (n1Node != null) {
            int compareResult = n1.compareTo(n1Node.getData());
            if (compareResult < 0) {
                ancestorStack.push(n1Node);
                n1Node = n1Node.getLeft();
            } else if (compareResult > 0) {
                ancestorStack.push(n1Node);
                n1Node = n1Node.getRight();
            } else {
                break;
            }
        }
        if (n1Node == null) return null;
        BinaryTreeNode<T> result = n1Node;
        while (result.getData().compareTo(root.getData()) != 0) {
            if (hasDescendant(result, n2)) break;
            if (ancestorStack.isEmpty()) return null;
            else result = ancestorStack.pop();
        }

        return result.getData();

    }

    private boolean hasDescendant(BinaryTreeNode<T> treeNode, T t) {
        while (treeNode != null) {
            int compareResult = t.compareTo(treeNode.getData());
            if (compareResult < 0) {
                treeNode = treeNode.getLeft();
            } else if (compareResult > 0) {
                treeNode = treeNode.getRight();
            } else {
                return true;
            }
        }
        return false;
    }

    public List<T> getNodesBetween(T n1, T n2) {
        List<T> resultList = new ArrayList<>();
        getNodesBetween(n1, n2, root, resultList);
        return resultList;
    }

    private void getNodesBetween(T n1, T n2, BinaryTreeNode<T> treeNode, List<T> resultList) {
        if (n1 == null || n2 == null || treeNode == null) return;
        if (n1.compareTo(treeNode.getData()) <= 0 &&
                n2.compareTo(treeNode.getData()) >= 0) {
            resultList.add(treeNode.getData());
            getNodesBetween(n1, n2, treeNode.getLeft(), resultList);
            getNodesBetween(n1, n2, treeNode.getRight(), resultList);
        } else if (n1.compareTo(treeNode.getData()) > 0) {
            getNodesBetween(n1, n2, treeNode.getRight(), resultList);
        } else if (n2.compareTo(treeNode.getData()) < 0) {
            getNodesBetween(n1, n2, treeNode.getLeft(), resultList);
        } else {}
    }

}
