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
//        if (root != null) {
//            return getExtreme(false);
//        }
//        return null;
        return getExtremeRecursive(root, false);
    }

    public T findMax() {
//        if (root != null) {
//            return getExtreme(true);
//        }
//        return null;
        return getExtremeRecursive(root, true);
    }

    /**
     * 递归获取极值对应的节点
     *
     * @param node
     * @param isMax
     * @return
     */
    private BinaryTreeNode<T> getExtremeNodeRecursive(BinaryTreeNode<T> node, boolean isMax) {
        BinaryTreeNode<T> next = isMax == true ? node.getRight() : node.getLeft();
        if (next == null)
            return node;
        return getExtremeNodeRecursive(next, isMax);
    }

    /**
     * 递归获取极值
     *
     * @param node
     * @param isMax
     * @return
     */
    private T getExtremeRecursive(BinaryTreeNode<T> node, boolean isMax) {
        BinaryTreeNode<T> next = isMax == true ? node.getRight() : node.getLeft();
        if (next == null)
            return node.getData();
        return getExtremeRecursive(next, isMax);
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
        BinaryTreeNode<T> parent = findParent(root, e);
        if (parent == null)
            throw new RuntimeException("cannot remove non-exist node");

        BinaryTreeNode<T> removeNode = null;
        boolean isLeft = true;//待删除的节点是否是parent的左节点
        BinaryTreeNode<T> left = parent.getLeft();
        BinaryTreeNode<T> right = parent.getRight();
        if (left != null && left.getData().compareTo(e) == 0)
            removeNode = left;
        else {
            removeNode = right;
            isLeft = false;
        }
        //删除叶子节点
        if (removeNode.getLeft() == null && removeNode.getRight() == null) {
            if (isLeft)
                parent.setLeft(null);
            else
                parent.setRight(null);
        }
        //删除只存在右子节点的根节点
        else if (removeNode.getLeft() == null && removeNode.getRight() != null) {
            if (isLeft)
                parent.setLeft(removeNode.getRight());
            else
                parent.setRight(removeNode.getRight());
        }
        //删除只存在左子节点的根节点
        else if (removeNode.getRight() == null && removeNode.getLeft() != null) {
            if (isLeft)
                parent.setLeft(removeNode.getLeft());
            else
                parent.setRight(removeNode.getLeft());
        }
        //删除根节点
        else {
            if (isLeft) {
                //删除的是左节点，找所有右子节点中最小的
                BinaryTreeNode<T> min = getExtremeNodeRecursive(removeNode.getRight(), false);

            } else {

            }
        }
    }

    /**
     * 返回和targetData值相等的node节点所属的父节点
     *
     * @param node
     * @param targetData
     * @return
     */
    public BinaryTreeNode<T> findParent(BinaryTreeNode<T> node, T targetData) {
        if (node != null) {
            BinaryTreeNode<T> left = node.getLeft();
            BinaryTreeNode<T> right = node.getRight();
            if (targetData.compareTo(node.getData()) > 0) {
                if (right != null && right.getData().equals(targetData))
                    return node;
                return findParent(right, targetData);
            } else if (targetData.compareTo(node.getData()) < 0) {
                if (left != null && left.getData().equals(targetData))
                    return node;
                return findParent(left, targetData);
            }
        }
        return null;
    }


}
