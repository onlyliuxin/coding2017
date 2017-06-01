package me.lzb.basic.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author LZB
 */
public class BinarySearchTree<T extends Comparable<T>> {
    BinaryTreeNode<T> root;

    public BinarySearchTree(BinaryTreeNode<T> root) {
        this.root = root;
    }

    public BinaryTreeNode<T> getRoot() {
        return root;
    }

    public T findMin() {
        if (this.root == null) {
            throw new RuntimeException("empty tree");
        }
        return getMinNode(this.root).getData();
    }

    public T findMax() {
        if (this.root == null) {
            throw new RuntimeException("empty tree");
        }
        return getMaxNode(this.root).getData();
    }

    private BinaryTreeNode<T> getMinNode(BinaryTreeNode<T> node) {
        if (node == null) {
            return node;
        }

        if (node.getLeft() == null) {
            return node;
        }
        return getMinNode(node.getLeft());
    }

    private BinaryTreeNode<T> getMaxNode(BinaryTreeNode<T> node) {
        if (node == null) {
            return node;
        }

        if (node.getRight() == null) {
            return node;
        }

        return getMaxNode(node.getRight());
    }


    public int height() {
        return getHeight(root, 0);
    }

    private int getHeight(BinaryTreeNode<T> node, int h) {
        if (node == null) {
            return h;
        }

        int lh = getHeight(node.getLeft(), h + 1);
        int rh = getHeight(node.getRight(), h + 1);

        return lh > rh ? lh : rh;
    }


    public int size() {
        return getSize(root, 1);
    }

    private int getSize(BinaryTreeNode<T> node, int s) {
        if (node == null) {
            return s - 1;
        }

        s = getSize(node.getLeft(), s + 1);
        s = getSize(node.getRight(), s + 1);

        return s;
    }

    public void remove(T t) {
        BinaryTreeNode<T> node = getFatherNode(root, t);
        removeNode(node, t);
    }


    private void removeNode(BinaryTreeNode<T> father, T t) {
        if (father == null) {
            return;
        }

        //移除左子节点
        if (father.getLeft() != null && father.getLeft().getData().equals(t)) {
            BinaryTreeNode<T> remove = father.getLeft();
            BinaryTreeNode<T> l = remove.getLeft();
            BinaryTreeNode<T> r = remove.getRight();

            //被删除节点没有子节点，直接删除
            if (l == null && r == null) {
                father.setLeft(null);
                return;
            }

            //被删除节点有一个子节点，用非空子节点顶替被删除节点
            if (l == null) {
                father.setLeft(r);
                return;
            }

            if (r == null) {
                father.setLeft(l);
                return;
            }


            //被删除节点有两个子节点
            //右子树的最小节点，顶替被删除位置
            BinaryTreeNode<T> rm = getMinNode(r);
            BinaryTreeNode<T> rmf = getFatherNode(r, rm.getData());
            rmf.setLeft(null);
            father.setLeft(rm);
            rm.setLeft(l);
            rm.setRight(r);


            return;

        }


        //移除右子节点
        if (father.getRight() != null && father.getRight().getData().equals(t)) {
            BinaryTreeNode<T> remove = father.getRight();
            BinaryTreeNode<T> l = remove.getLeft();
            BinaryTreeNode<T> r = remove.getRight();

            if (l == null && r == null) {
                father.setRight(null);
                return;
            }

            if (l == null) {
                father.setRight(r);
                return;
            }


            if (r == null) {
                father.setRight(l);
                return;
            }


            BinaryTreeNode<T> rm = getMinNode(r);
            BinaryTreeNode<T> rmf = getFatherNode(r, rm.getData());
            rmf.setLeft(null);
            father.setRight(rm);
            rm.setLeft(l);
            rm.setRight(r);
            return;
        }


    }


    private BinaryTreeNode<T> getFatherNode(BinaryTreeNode<T> node, T t) {
        if (node == null) {
            return node;
        }
        if (node.getLeft() != null && node.getLeft().getData().equals(t)) {
            return node;
        }

        if (node.getRight() != null && node.getRight().getData().equals(t)) {
            return node;
        }
        if (node.isSmallerThanParam(t)) {
            return getFatherNode(node.getRight(), t);
        } else {
            return getFatherNode(node.getLeft(), t);
        }
    }


    /**
     * 按层次遍历： levelVisit
     *
     * @return
     */
    public List<T> levelVisit() {
        List<T> result = new ArrayList<>();
        levelVisit(result, root);
        return result;
    }


    private void levelVisit(List<T> list, BinaryTreeNode<T> node) {
        if (node == null) {
            return;
        }
        //先放入根节点
        Queue<BinaryTreeNode<T>> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            //上一层的节点个数
            int l = queue.size();
            //循环取出上一层这些节点
            for (int i = 0; i < l; i++) {
                BinaryTreeNode<T> temp = queue.poll();
                list.add(temp.getData());
                //把下一层的节点放入队列
                if (temp.getLeft() != null) {
                    queue.add(temp.getLeft());
                }
                if (temp.getRight() != null) {
                    queue.add(temp.getRight());
                }
            }
        }
    }


    /**
     * 判断一个二叉树是不是二叉查找树
     *
     * @return
     */
    public boolean isValid() {
        return isValid(true, root);
    }

    private boolean isValid(boolean b, BinaryTreeNode<T> node) {
        if (!b || node == null) {
            return b;
        }

        if (node.getLeft() == null && node.getRight() == null) {
            return b;
        }

        if (node.getLeft() != null) {
            if (node.isSmallerThanParam(node.getLeft())) {
                b = false;
            }
        }

        if (node.getRight() != null) {
            if (node.isBiggerThanParam(node.getRight())) {
                b = false;
            }
        }

        boolean bl = isValid(b, node.getLeft());
        boolean br = isValid(b, node.getRight());

        return bl && br;
    }


    /**
     * 获取两个节点的最小公共祖先
     *
     * @param n1
     * @param n2
     * @return
     */
    public T getLowestCommonAncestor(T n1, T n2) {
        return getLowestCommonAncestor(root, n1, n2);
    }


    private T getLowestCommonAncestor(BinaryTreeNode<T> node, T n1, T n2) {
        if (node == null) {
            return null;
        }

        //找到一个位于两者之间的节点，就是公共祖先。
        if ((node.isSmallerThanParam(n1) && node.isBiggerThanParam(n2)) || (node.isSmallerThanParam(n2) && node.isBiggerThanParam(n1))) {
            return node.getData();
        }

        //当前节点比n1和n2都小，最小公共祖先在右子树。
        if (node.isSmallerThanParam(n1) && node.isSmallerThanParam(n2)) {
            return getLowestCommonAncestor(node.getRight(), n1, n2);
        }

        //当前节点比n1和n2都大，最小公共祖先在左子树。
        if (node.isBiggerThanParam(n1) && node.isBiggerThanParam(n2)) {
            return getLowestCommonAncestor(node.getLeft(), n1, n2);
        }
        return null;
    }


    /**
     * 给定两个值， 获得处于这两个值中间的节点
     *
     * @param n1
     * @param n2
     * @return
     */
    public List<T> getNodesBetween(T n1, T n2) {
        List<T> result = new ArrayList<>();
        T s, b;
        if (n1.compareTo(n2) < 0) {
            s = n1;
            b = n2;
        } else {
            s = n2;
            b = n1;
        }
        getNodesBetween(result, root, s, b);

        return result;
    }

    private void getNodesBetween(List<T> list, BinaryTreeNode<T> node, T small, T bigger) {
        if (node == null) {
            return;
        }

        if (node.isBiggerThanParam(small) && node.isSmallerThanParam(bigger)) {
            list.add(node.getData());
        }

        getNodesBetween(list, node.getLeft(), small, bigger);
        getNodesBetween(list, node.getRight(), small, bigger);
    }
}
