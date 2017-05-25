package com.github.HarryHook.coding2017.tree;

import java.util.ArrayList;
import java.util.List;

import com.github.HarryHook.coding2017.queue.Queue;

public class BinarySearchTree<T extends Comparable> {
    BinaryTreeNode<T> root;

    public BinarySearchTree(BinaryTreeNode<T> root) {
	this.root = root;
    }

    public BinaryTreeNode<T> getRoot() {
	return root;
    }

    public T findMin() {

	return findMin(root);
    }

    // 查找某个子树的最小值
    public T findMin(BinaryTreeNode<T> node) {
	if (node == null) {
	    return null;
	}
	BinaryTreeNode<T> p = node;
	BinaryTreeNode<T> q = null;
	while (p != null) {
	    q = p;
	    p = p.left;
	}
	return q.data;
    }

    public T findMax() {

	return findMax(root);
    }

    public T findMax(BinaryTreeNode<T> root) {
	if (root == null) {
	    return null;
	}
	BinaryTreeNode<T> p = root;
	BinaryTreeNode<T> q = null;
	while (p != null) {
	    q = p;
	    p = p.right;
	}
	return q.data;
    }

    public int height() {
	return height(root);
    }

    private int height(BinaryTreeNode<T> root) {
	if (root == null) {
	    return 0;
	}
	int leftHeight = height(root.left);
	int rightHeight = height(root.right);

	return leftHeight > rightHeight ? leftHeight + 1 : rightHeight + 1;
    }

    public int size() {
	return size(root);
    }

    public int size(BinaryTreeNode<T> root) {
	if (root == null) {
	    return 0;
	}
	return size(root.left) + 1 + size(root.right);
    }

    /*
     * 思路： 
     * 1.若没找到值为t的节点，返回空 。
     * 2.若该节点的左子树或右子树为空，直接删除该节点，将子树的根节点替换掉该节点。
     * 3.该节点的左右子树都存在，将右子树的后继(右子树最小的节点)替换被删除的节点。
     */
    public void remove(T t) {
	root = remove(t, root);
    }

    /** 在某个位置开始判断删除某个结点 */
    public BinaryTreeNode<T> remove(T t, BinaryTreeNode<T> node) {
	if (node == null)
	    return node;// 没有找到,doNothing
	int result = t.compareTo(node.data);
	if (result > 0)
	    node.right = remove(t, node.right);
	else if (result < 0)
	    node.left = remove(t, node.left);
	else if (node.left != null && node.right != null) {
	    node.data = findMin(node.right); // 找出右子树的最小节点
	    node.right = remove(node.data, node.right);
	} else {
	    node = (node.left != null) ? node.left : node.right;
	}
	return node;

    }

    /*
     * 分层次遍历
     * 思路：
     * 利用队列，一层一层地出队列 — 在我们每次访问完毕一层时，这时队列中存储的刚好是下一层的所有元素。
     * 在下一次循环开始时，首先记录该层的元素个数，一次性访问完这一层的所有元素
     */
    public List<T> levelVisit() {
	if (root == null) {
	    return null;
	}

	List<T> list = new ArrayList<>();
	Queue<BinaryTreeNode<T>> queue = new Queue<>();
	queue.enQueue(root);

	while (!(queue.isEmpty())) {
	    int currentSize = queue.size();
	    int count = 0;
	    while (count < currentSize) {
		BinaryTreeNode<T> tmp = queue.deQueue();
		list.add(tmp.data);
		count++;
		if (tmp.left != null) {
		    queue.enQueue(tmp.left);
		}
		if (tmp.right != null) {
		    queue.enQueue(tmp.right);
		}
	    }
	}
	return list;
    }

    /*
     * 
     * 思路：
     * 1:中序遍历，检查元素是否由小到大排列
     * 2:left节点是否比root节点小，right节点是否比root节点大，然后分别递归遍历左右子树
     */
    public boolean isValid() {
	return checkBST(root);
    }

    public boolean checkBST(BinaryTreeNode<T> root) {
	if (root == null) {
	    return true;
	}
	if (root.left != null) {
	    if (root.left.data.compareTo(root.data) > 0) {
		return false;
	    }
	}
	if (root.right != null) {
	    if (root.right.data.compareTo(root.data) < 0) {
		return false;
	    }
	}
	return checkBST(root.left) && checkBST(root.right);
    }

    /*
     * 两个值的范围内的所有节点
     */
    public List<T> getNodesBetween(T n1, T n2) {
	if (root == null) {
	    return null;
	}
	List<T> list = new ArrayList<>();
	return searchRange(n1, n2, list, root);
    }

    /*
     * 相当于中序遍历
     */
    private List<T> searchRange(T min, T max, List<T> result, BinaryTreeNode<T> node) {
	if (node == null) {
	    return null;
	}
	int cmpMin = min.compareTo(node.data);
	int cmpMax = max.compareTo(node.data);
	if (cmpMin < 0) {
	    searchRange(min, max, result, node.left);
	}
	if (cmpMin <= 0 && cmpMax >= 0) {
	    result.add(node.data);
	}
	if (cmpMax > 0) {
	    searchRange(min, max, result, node.right);
	}
	return result;
    }

    /*
     * 两个不同节点的最小祖先 
     * 思路：
     * left比root节点大， 遍历root的右子树，max比root节点小，遍历root左子树
     * 不满足以上情况表示找到他们的祖先，并返回
     */
    public T getLowestCommonAncestor(T n1, T n2) {
	//return findNodeWithNoCursion(root, n1, n2);
	return findNodeWithCursion(root, n1, n2);	 
    }

    private T findNodeWithCursion(BinaryTreeNode<T> node, T n1, T n2) {
	if (node == null) {
	    return null;
	}
	if (n1.compareTo(node.data) < 0 && n2.compareTo(node.data) < 0) {
	    return findNodeWithCursion(node.left, n1, n2);
	}
	if (n1.compareTo(node.data) > 0 && n2.compareTo(node.data) > 0) {
	    return findNodeWithCursion(node.right, n1, n2);
	}
	return node.data;
    }

    private T findNodeWithNoCursion(BinaryTreeNode<T> node, T n1, T n2) {
	if (n1.compareTo(n2) > 0) {
	    T tmp = n1;
	    n1 = n2;
	    n2 = tmp;
	}
	while (true) {
	    if (n1.compareTo(node.data) > 0) {
		node = node.right;
	    } else if (n2.compareTo(node.data) < 0) {
		node = node.left;
	    } else {
		return node.data;
	    }
	}

    }

    public static void main(String[] args) {
	BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>(5);
	root.left = new BinaryTreeNode<Integer>(2);
	root.right = new BinaryTreeNode<Integer>(6);
	root.left.left = new BinaryTreeNode<Integer>(1);
	root.left.right = new BinaryTreeNode<Integer>(4);
	root.left.right.left = new BinaryTreeNode<Integer>(3);
	BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>(root);
	System.out.println(tree.levelVisit());
	System.out.println(tree.isValid());
	System.out.println(tree.getNodesBetween(1, 6));
	System.out.println(tree.getLowestCommonAncestor(1, 6));
    }

}
