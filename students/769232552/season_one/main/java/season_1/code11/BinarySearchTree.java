package code11;

import code10.BinaryTreeNode;

import java.util.*;

/**
 * Created by yyglider on 2017/5/16.
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
        if (this.root == null) {
            return null;
        }

        BinaryTreeNode pNode = this.root;
        while (pNode != null && pNode.left != null) {
            pNode = pNode.left;
        }
        return (T) pNode.getData();
    }

    public T findMax() {
        if (this.root == null) {
            return null;
        }

        BinaryTreeNode pNode = this.root;
        while (pNode != null && pNode.right != null) {
            pNode = pNode.right;
        }
        return (T) pNode.getData();
    }

    public int height() {
        if (this.root == null) {
            return 0;
        }
        return getHeight(this.root);
    }

    private int getHeight(BinaryTreeNode<T> node) {
        if (node == null) {
            return 0;
        }
        int left = getHeight(node.left);
        int right = getHeight(node.right);
        return 1 + Math.max(left, right);
    }

    public int size() {
        if (this.root == null) {
            return 0;
        }
        return getSize(this.root);
    }

    private int getSize(BinaryTreeNode node) {
        if (node == null) {
            return 0;
        }
        int left = getSize(node.left);
        int right = getSize(node.right);
        return 1 + left + right;
    }


    public void remove(T e) {
        if (this.root == null || e == null) {
            return;
        }

        if (e.equals(this.root.getData())) {
            this.root = null;
            return;
        }

        BinaryTreeNode pNode = this.root; //记录待删除的元素位置
        BinaryTreeNode pPreNode = this.root; //记录待删除的元素的前一个位置

        //find e
        while (pNode != null) {
            if (e.compareTo(pNode.getData()) > 0) {
                pPreNode = pNode;
                pNode = pNode.right;
            } else if (e.compareTo(pNode.getData()) < 0) {
                pPreNode = pNode;
                pNode = pNode.left;
            } else {
                break;
            }

        }

        //delete e
        if (pNode != null && e.equals(pNode.getData())) {
            BinaryTreeNode node;
            BinaryTreeNode preNode;

            //如果其结点只包含左子树，或者右子树的话，此时直接删除该结点
            boolean hasOneChild = ((pNode.right != null && pNode.left == null) && (pNode.right == null && pNode.left != null));
            if (hasOneChild) {
                if (pNode.left != null) {
                    node = pNode.left;
                    pNode.setData(node.getData());
                    pNode.left = null;
                    return;
                }
                node = pNode.right;
                pNode.setData(node.getData());
                pNode.right = null;

            }

            //如果其结点既包含左子树，也包含右子树
            //找到其右子树的的最小元素（实际上用左子树最大元素代替也是可以的）
            if (pNode.right != null && pNode.left != null) {
                preNode = pNode;
                node = pNode.right;
                while (node != null && node.left != null) {
                    preNode = node;
                    node = node.left;
                }
                //如果是叶子节点
                if (node.right == null) {
                    pNode.setData(node.getData());

                    preNode.right = null;
                    return;
                }

                pNode.setData(node.getData());

                //非叶子结点，用其右孩子的元素来替代
                preNode = node;
                node = node.right;
                preNode.setData(node.getData());
                preNode.right = null;
                return;
            }

            //其本身就是叶子节点
            pPreNode = null;

        }
    }

    //层序遍历,使用队列的方式
    public List<T> levelVisit() {
        if (this.root == null) {
            return null;
        }
        List<T> results = new ArrayList<T>();
        Queue<BinaryTreeNode> queue = new ArrayDeque<BinaryTreeNode>();

        queue.add(this.root);

        while (!queue.isEmpty()) {
            BinaryTreeNode node = queue.poll();
            results.add((T) node.getData());

            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
        return results;
    }


    /**
     * 验证是否是二叉查找树
     */
    public boolean isValid() {
        if (this.root == null) {
            return true;
        }
        return checkValid(this.root);
    }

    public boolean checkValid(BinaryTreeNode node) {

        if (node == null) {
            return true;
        }
        T nodeData = (T) node.getData();
        boolean currentFlag = (((node.left != null && nodeData.compareTo(node.left.getData()) > 0)
                        || node.left == null)
                && ((node.right != null && nodeData.compareTo(node.right.getData()) < 0
                        || node.right == null)));
        boolean leftFlag = checkValid(node.left);
        boolean rightFlag = checkValid(node.right);
        return currentFlag && leftFlag && rightFlag;
    }



    /**
     * 最近公共祖先（LCA）问题
     * 基本思想为：从树根开始，该节点的值为t，
     * 如果t大于t1和t2，说明t1和t2都位于t的左侧，所以它们的共同祖先必定在t的左子树中，从t.left开始搜索；
     * 如果t小于t1和t2，说明t1和t2都位于t的右侧，那么从t.right开始搜索；
     * 如果t1<=t<= t2，说明t1和t2位于t的两侧（或t=t1，或t=t2），那么该节点t为公共祖先。
     */
    public T getLowestCommonAncestor(T n1, T n2) {
        if (this.root == null || n1 == null || n2 == null) {
            return null;
        }

        BinaryTreeNode pNode = this.root;

        while (pNode != null) {
            if (n1.compareTo(pNode.getData()) > 0 && n2.compareTo(pNode.getData()) > 0) {
                pNode = pNode.right;
            } else if (n1.compareTo(pNode.getData()) < 0 && n2.compareTo(pNode.getData()) < 0) {
                pNode = pNode.left;
            } else {
                return (T) pNode.getData();
            }
        }
        return null;
    }


    /**
     * 两个节点之间的最短路径
     */
    public List<T> getNodesBetween(T n1, T n2) {

        if (this.root == null || n1 == null || n2 == null) {
            return null;
        }

        final List<T> list1 = new ArrayList<T>();
        List<T> list2 = new ArrayList<T>();
        BinaryTreeNode pNode1 = this.root;
        BinaryTreeNode pNode2 = this.root;

        //find n1 path
        while (pNode1 != null) {
            list1.add((T) pNode1.getData());

            if (n1.compareTo(pNode1.getData()) > 0) {
                pNode1 = pNode1.right;
            } else if (n1.compareTo(pNode1.getData()) < 0) {
                pNode1 = pNode1.left;
            } else {
                break;
            }
        }
        //find n2 path
        while (pNode2 != null) {
            list2.add((T) pNode2.getData());

            if (n2.compareTo(pNode2.getData()) > 0) {
                pNode2 = pNode2.right;
            } else if (n2.compareTo(pNode2.getData()) < 0) {
                pNode2 = pNode2.left;
            } else {
                break;
            }
        }

        //join path1 and path2
        int i = 0, j = 0;
        T first = null;
        List<T> results = new ArrayList<T>();

        while (i < list1.size() && i < list2.size()) {
            if (list1.get(i) != list2.get(i)) {
                break;
            }
            i++;
        }
        if (i > 0) {
            i--;
        }
        int resultsLen = list1.size() + list2.size() - 2 * i - 1;
        for (int k = list1.size() - 1; k > i; k--) {
            results.add(list1.get(k));
        }
        for (int k = i; k < list2.size(); k++) {
            results.add(list2.get(k));
        }

        Collections.reverse(list1);

        return results;
    }


}
