import queue.Queue;
import tree.BinaryTreeNode;

/**
 * Created by gongxun on 2017/5/15.
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
        if (root != null) {
            return getExtreme(false);
        }
        return null;
    }

    public T findMax() {
        if (root != null) {
            return getExtreme(true);
        }
        return null;
    }

    /**
     * 获得极值
     *
     * @param isMax
     * @return
     */
    private T getExtreme(boolean isMax) {
        T extreme = root.getData();
        Queue<BinaryTreeNode<T>> queue = new Queue<BinaryTreeNode<T>>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            BinaryTreeNode<T> node = queue.poll();
            extreme = node.getData();
            BinaryTreeNode<T> next = (isMax == true) ? node.getRight() : node.getLeft();
            if (next != null) {
                queue.offer(next);
            }
        }
        return extreme;
    }

    public int height() {
        return maxHeight(root);
    }

    private int maxHeight(BinaryTreeNode<T> root) {
        if (root == null)
            return 0;
        int leftHeight = maxHeight(root.getLeft());
        int rightHeight = maxHeight(root.getRight());
        return leftHeight > rightHeight ? ++leftHeight : ++rightHeight;
    }

    public int size() {
        if (root != null) {
            int size = 1;
            Queue<BinaryTreeNode<T>> queue = new Queue<BinaryTreeNode<T>>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                BinaryTreeNode<T> node = queue.poll();
                if (node.getLeft() != null) {
                    queue.offer(node.getLeft());
                    size++;
                }
                if (node.getRight() != null) {
                    queue.offer(node.getRight());
                    size++;
                }
            }
            return size;
        }
        return -1;
    }

    public void remove(T e) {

    }

}
