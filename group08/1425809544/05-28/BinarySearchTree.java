package com.zhuoyue.scheduleplan.domain;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author xyy
 * @create 2017-05-26 15:50
 * @deprecated 二叉查找树是一个二叉树, 其中每个节点都含有一个Comparable的值,
 * 每个节点的值大于左子树的所有值,小于右子树的所有值
 **/
public class BinarySearchTree<T extends Comparable> {


    BinaryTreeNode<T> root;

    public BinarySearchTree(BinaryTreeNode<T> root) {
        this.root = root;
    }

    public BinaryTreeNode<T> getRoot() {
        return root;
    }

    public T findMin() {
        if (root == null) {
            return null;
        }
        return findMin(root).getData();
    }

    private BinaryTreeNode<T> findMin(BinaryTreeNode<T> root) {

        if (root == null) {
            return null;
        } else if (root.getLeft() == null) {
            return root;
        } else {
            return findMin(root.getLeft());
        }
    }

    public T findMax() {
        if (root == null) {
            return null;
        }
        return findMax(root).getData();
    }

    private BinaryTreeNode<T> findMax(BinaryTreeNode<T> root) {

        if (root == null) {
            return null;
        } else if (root.getRight() == null) {
            return root;
        } else {
            return findMax(root.getRight());
        }

    }

    public int height() {
        return height(root);
    }

    private int height(BinaryTreeNode<T> root) {
        if (root == null) {
            return 0;
        } else {
            int leftChildHeight = height(root.getLeft());
            System.out.println(leftChildHeight);
            int rigthChildHeight = height(root.getRight());
            System.out.println(rigthChildHeight);
            if (leftChildHeight > rigthChildHeight) {
                return leftChildHeight + 1;
            } else {
                return rigthChildHeight + 1;
            }
        }
    }

    public int size() {
        return size(root);
    }

    private int size(BinaryTreeNode<T> root) {
        if (root == null) {
            return 0;
        }
        return size(root.getLeft()) + 1 + size(root.getRight());
    }

    public void remove(T e) {
        remove(e, root);
    }

    //暂时没看懂
    private BinaryTreeNode<T> remove(T e, BinaryTreeNode<T> root) {
        if (root == null) {
            return root;
        }
        int compareResult = e.compareTo(root.data);

        if (compareResult < 0) {
            root.left = remove(e, root.getLeft());
        } else if (compareResult > 0) {
            root.right = remove(e, root.getRight());
        } else {
            if (root.getLeft() != null && root.getRight() != null) {
                root.data = findMin(root.getRight()).data;
                root.right = remove(root.data, root.right);
            } else {
                root = (root.left != null) ? root.left : root.right;
            }
        }


        return root;
    }


    /**
     * @Author xuyangyang
     * @Describe 层次遍历
     * @Date 2017/5/27
     * @Params
     * @Return
     */
    public List<T> levelVisit(BinaryTreeNode<T> root) {

        if (root == null) {
            return null;
        }
        LinkedList<BinaryTreeNode<T>> queue = new LinkedList();
        BinaryTreeNode<T> current = null;
        queue.offer(root);
        while (!queue.isEmpty()) {
            current = queue.poll();
            System.out.println(current.getData());
            if (current.left != null) {
                queue.offer(current.left);
            }
            if (current.right != null) {
                queue.offer(current.right);
            }
        }
        return null;
    }

    public boolean isValid() {
        return isValid(root);
    }

    /**
     * @Author xuyangyang
     * @Describe 是否是一颗有效的二叉搜索树
     * @Date 2017/5/27
     * @Params
     * @Return
     */
    public boolean isValid(BinaryTreeNode<T> root) {
        if (root == null)
            return true;
        if (root.left != null && findMax(root.left).data.compareTo(root.data) > 0) {
            return false;
        }
        if (root.right != null && findMin(root.right).data.compareTo(root.data) < 0) {
            return false;
        }
        return isValid(root.left) && isValid(root.right);
    }


    /**
     * @Author xuyangyang
     * @Describe 获取两个节点的最小公共祖先
     * @Date 2017/5/27
     * @Params
     * @Return
     */
    public T getLowestCommonAncestor(BinaryTreeNode<T> n1, BinaryTreeNode<T> n2) {
        getLowestCommonAncestor(root, n1, n2);
        return null;
    }

    private BinaryTreeNode<T> getLowestCommonAncestor(BinaryTreeNode<T> root, BinaryTreeNode<T> n1, BinaryTreeNode<T> n2) {
        if (getLCAUtil(root.left, n1) && getLCAUtil(root.left, n2)) {
            return getLowestCommonAncestor(root.left, n1, n2);
        }
        if (getLCAUtil(root.right, n1) && getLCAUtil(root.right, n2)) {
            return getLowestCommonAncestor(root.right, n1, n2);
        }
        return root;

    }

    private boolean getLCAUtil(BinaryTreeNode<T> root, BinaryTreeNode<T> n1) {
        if (root == null) {
            return false;
        }
        if (root == n1) {
            return true;
        }
        return getLCAUtil(root.left, n1) || getLCAUtil(root.right, n1);

    }


    /**
     * @Author xuyangyang
     * @Describe 给定两个值， 获得处于这两个值中间的节点
     * @Date 2017/5/27
     * @Params
     * @Return
     */
    public List<T> getNodesBetween(T n1, T n2) {
        List<T> result = new ArrayList<>();
        getNodesBetween(result, root, n1, n2);
        return result;
    }

    public void getNodesBetween(List<T> result, BinaryTreeNode<T> root, T start, T end) {
        if (root == null) {
            return;
        }
        if (start.compareTo(root.data) < 0) {
            getNodesBetween(result, root.left, start, end);
        }
        if (start.compareTo(root.data) < 0 && end.compareTo(root.data) >0) {
            result.add(root.data);
            System.out.println(root.data);
        }
        if (end.compareTo(root.data) > 0) {
            getNodesBetween(result, root.right, start, end);
        }
    }

}
