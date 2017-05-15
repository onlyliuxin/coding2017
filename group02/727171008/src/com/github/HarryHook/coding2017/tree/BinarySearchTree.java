package com.github.HarryHook.coding2017.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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
	if (root == null) {
	    return null;
	}
	BinaryTreeNode<T> p = root;
	BinaryTreeNode<T> q = null;
	while (p != null) {
	    q = p;
	    p = p.left;
	}
	return q.data;
    }
    
    //查找某个子树的最小值
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

    /*
     * 思路： 利用队列，一层一层地出队列 — 在我们每次访问完毕一层时，这时队列中存储的刚好是下一层的所有元素。
     * 在下一次循环开始时，首先记录该层的元素个数，一次性访问完这一层的所有元素
     */
    public int height() {
	if (root == null) {
	    return 0;
	}
	Queue<BinaryTreeNode<T>> queue = new Queue<>();
	queue.enQueue(root);
	int height = 0;
	while (!(queue.isEmpty())) {
	    height++;
	    int currentSize = queue.size();
	    int count = 0;
	    while (count < currentSize) {
		BinaryTreeNode<T> tmp = queue.deQueue();
		count++;
		if (tmp.left != null) {
		    queue.enQueue(tmp.left);
		}
		if (tmp.right != null) {
		    queue.enQueue(tmp.right);
		}
	    }
	}
	return height;
    }

    public int size() {
	List<T> result = inOrderWithoutRecursion(root);
	return result.size();
    }

    public static <T extends Comparable<T>> List<T> inOrderWithoutRecursion(BinaryTreeNode<T> root) {

	List<T> result = new ArrayList<T>();
	Stack<BinaryTreeNode<T>> s = new Stack<>();
	BinaryTreeNode<T> p = root;
	while (p != null || !(s.isEmpty())) {
	    while (p != null) {
		s.push(p);
		p = p.getLeft();
	    }
	    if (!(s.isEmpty())) {
		p = s.pop();
		result.add(p.getData());
		p = p.getRight();
	    }
	}
	return result;
    }

    /*
     * 思路： 
     * 1.若没找到值为e的节点，返回空
     * 2.若该节点的左子树或右子树为空，直接删除该节点，将子树的根节点替换掉该节点
     * 3.该节点的左右子树都存在，将右子树的后继(右子树最小的节点)替换被删除的节点
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
	    node.data = findMin(node.right); // 应该找出右子树的最小节点 而不是整个树的最小节点
	    node.right = remove(node.data, node.right);
	} else
	    node = (node.left != null) ? node.left : node.right;
	return node;

    }


}
