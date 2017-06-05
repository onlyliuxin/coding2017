package basic.tree;


import basic.queue.Queue;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉查找树,左子树小于右子树
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
        BinaryTreeNode head = root;
        T min = null;
        while (head != null) {
            min = (T) head.getData();
            head = head.left;
        }
        return min;
    }

    public T findMax() {
        BinaryTreeNode head = root;
        T max = null;
        while (head != null) {
            max = (T) head.getData();
            head = head.right;
        }
        return max;
    }

    public int height() {
        return getHeight(root);
    }

    private int getHeight(BinaryTreeNode node) {
        if (node == null) {
            return 0;
        }
        int leftH = getHeight(node.left);
        int rightH = getHeight(node.right);
        return leftH > rightH ? leftH + 1 : rightH + 1;
    }

    public int size() {
        BinaryTreeNode<T> temp = root;
        int size = 0;
        return BinaryTreeUtil.inOrderVisit(root).size();
    }

    /**
     * 删除中间的结点，树要做调整
     *
     * @param e 要删除的结点值
     */
    public void remove(T e) {
        BinaryTreeNode<T> node = root;
        BinaryTreeNode<T> father = null;
        //找到要删除的结点
        while (node != null) {
            if (node.getData().equals(e)) {
                break;
            }
            if (node.getData().compareTo(e) > 0) {
                father = node;
                node = node.getLeft();
            } else {
                father = node;
                node = node.getRight();
            }
        }

        //删除的结点是root结点
        if (father == null) {
            //TODO
        }

        //待删除结点为叶子结点
        if (node.getLeft() == null && node.getRight() == null) {
            if (father.left == node) {
                father.left = null;
            } else {
                father.right = null;
            }
        }

        //待删除结点左结点为null，右结点不为null
        if (node.getLeft() == null && node.getRight() != null) {
            BinaryTreeNode right = node.getRight();
            if (father.left == node) {
                father.left = right;
            } else {
                father.right = right;
            }
        }


        //待删除结点右结点为null，左结点不为null
        if (node.getLeft() != null && node.getRight() == null) {
            BinaryTreeNode left = node.getLeft();
            if (father.left == node) {
                father.left = left;
            } else {
                father.right = left;
            }
        }

        //待删除结点左右结点都不为null
        if (node.getLeft() != null && node.getRight() != null) {
            //从右子树中选择最小的一个结点替换当前结点
            BinaryTreeNode min = node.getRight();
            BinaryTreeNode temp = node.getRight();
            BinaryTreeNode minFather = node.getRight();
            while (temp.left != null) {
                min = temp.getLeft();
                temp = temp.left;
                if (temp.left != null) {
                    minFather = temp;
                }
            }
            minFather.left = null;
            min.left = node.getLeft();
            min.right = node.getRight();
            if (father.left == node) {
                father.left = min;
            } else {
                father.right = min;
            }
        }

    }


    public void remove1(T e) {
        removeNode(e, this.root);
    }

    private BinaryTreeNode removeNode(T e, BinaryTreeNode node) {
        if (this.root == null) {
            throw new NullPointerException();
        }

        //找子树
        if (e.compareTo(node.getData()) < 0) {
            node.setLeft(removeNode(e, node.getLeft()));
        } else if (e.compareTo(node.getData()) > 0) {
            node.setRight(removeNode(e, node.getRight()));
        } else {
            //删除
            if (node.getLeft() != null && node.getRight() != null) {
                BinaryTreeNode n = findMinNode(node.getRight());
                node.setData(n.getData());
                node.setRight(removeNode((T) node.getData(), node.getRight()));
            } else {
                BinaryTreeNode n = node;
                if (node.getLeft() == null) {
                    node = node.getRight();
                } else if (node.getRight() == null) {
                    node = node.getLeft();
                }
            }
        }
        return node;

    }

    private BinaryTreeNode findMinNode(BinaryTreeNode node) {

        if (node == null) {
            return null;
        }

        if (node.getLeft() == null) {
            return node;
        }

        return findMinNode(node.getLeft());
    }

    /**
     * 按层遍历
     * @return
     */
    public List<T> levelVisit() {

        List<T> list = new ArrayList<T>();

        Queue<BinaryTreeNode> queue = new Queue<BinaryTreeNode>();

        queue.enQueue(this.root);

        while (!queue.isEmpty()) {

            BinaryTreeNode node = queue.deQueue();

            list.add((T) node.getData());

            if (node.getLeft() != null) {
                queue.enQueue(node.getLeft());
            }

            if (node.getRight() != null) {
                queue.enQueue(node.getRight());
            }

        }

        return list;
    }

    /**
     * 是否为二叉树
     *
     * @return
     */
    public boolean isValid() {

        Queue<BinaryTreeNode> queue = new Queue<BinaryTreeNode>();

        queue.enQueue(this.root);

        while (!queue.isEmpty()) {

            BinaryTreeNode node = queue.deQueue();

            T t = (T) node.getData();

            if (node.getLeft() != null) {

                if (t.compareTo(node.getLeft().getData()) <= 0) {
                    return false;
                }

                queue.enQueue(node.getLeft());
            }

            if (node.getRight() != null) {

                if (t.compareTo(node.getRight().getData()) >= 0) {
                    return false;
                }

                queue.enQueue(node.getRight());
            }

        }

        return true;

    }

    /**
     * 获取两个节点的最小公共祖先
     * 同层算同一级别？
     *
     * @param n1
     * @param n2
     * @return
     */
    public T getLowestCommonAncestor(T n1, T n2) {

        if (n1.compareTo(n2) > 0) {
            T t = n1;
            n1 = n2;
            n2 = t;
        }

        BinaryTreeNode ancestor = null;

        Queue<BinaryTreeNode> queue = new Queue<BinaryTreeNode>();

        queue.enQueue(this.root);

        while (!queue.isEmpty()) {

            BinaryTreeNode node = queue.deQueue();

            T t = (T) node.getData();

            if (t.compareTo(n1) > 0 && t.compareTo(n2) < 0) {
                ancestor = node;
            }

            if (node.getLeft() != null) {

                queue.enQueue(node.getLeft());
            }

            if (node.getRight() != null) {

                queue.enQueue(node.getRight());
            }

        }


        return (T) ancestor.getData();

    }

    /**
     * 给定两个值， 获得处于这两个值中间的节点
     *
     * @param n1
     * @param n2
     * @return
     */
    public List<T> getNodesBetween(T n1, T n2) {

        if (n1.compareTo(n2) > 0) {
            T t = n1;
            n1 = n2;
            n2 = t;
        }

        List<T> list = new ArrayList<T>();

        Queue<BinaryTreeNode> queue = new Queue<BinaryTreeNode>();

        queue.enQueue(this.root);

        while (!queue.isEmpty()) {

            BinaryTreeNode node = queue.deQueue();

            T t = (T) node.getData();

            if (t.compareTo(n1) > 0 && t.compareTo(n2) < 0) {
                list.add(t);
            }

            if (node.getLeft() != null) {
                queue.enQueue(node.getLeft());
            }

            if (node.getRight() != null) {
                queue.enQueue(node.getRight());
            }

        }

        return list;

    }
}

