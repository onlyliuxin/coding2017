package com.github.HarryHook.coding2017.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinaryTreeUtil {
    /**
     * 用递归的方式实现对二叉树的前序遍历， 需要通过BinaryTreeUtilTest测试
     * 
     * @param root
     * @return
     */
    static List<Object> result = new ArrayList<>();
    public static <T extends Comparable<T>> List<T> preOrderVisit(BinaryTreeNode<T> root) {
	// List<T> result = new ArrayList<T>();
	if (root != null) {
	    result.add(root.getData());
	    preOrderVisit(root.getLeft());
	    preOrderVisit(root.getRight());
	}

	return (List<T>) result;
    }

    /**
     * 用递归的方式实现对二叉树的中遍历
     * 
     * @param root
     * @return
     */
    static List<Object> result1 = new ArrayList<>();

    public static <T extends Comparable<T>> List<T> inOrderVisit(BinaryTreeNode<T> root) {

	if (root != null) {
	    inOrderVisit(root.getLeft());
	    result1.add(root.getData());
	    inOrderVisit(root.getRight());
	}
	return (List<T>) result1;
    }

    /**
     * 用递归的方式实现对二叉树的后遍历
     * 
     * @param root
     * @return
     */
    static List<Object> result2 = new ArrayList<>();

    public static <T extends Comparable<T>> List<T> postOrderVisit(BinaryTreeNode<T> root) {
	// List<T> result = new ArrayList<T>();
	if (root != null) {
	    postOrderVisit(root.getLeft());
	    postOrderVisit(root.getRight());
	    result2.add(root.getData());
	}
	return (List<T>) result2;
    }

    /**
     * 用非递归的方式实现对二叉树的前序遍历
     * 
     * @param root
     * @return
     */
    public static <T extends Comparable<T>> List<T> preOrderWithoutRecursion(BinaryTreeNode<T> root) {

	List<T> result = new ArrayList<T>();
	Stack<BinaryTreeNode<T>> s = new Stack<>();
	BinaryTreeNode<T> p = root;
	while (p != null || !(s.isEmpty())) {
	    while (p != null) {
		result.add(p.getData());
		s.push(p);
		p = p.getLeft();
	    }
	    if (!(s.isEmpty())) {
		p = s.pop();
		p = p.getRight();
	    }
	}
	return result;
    }

    /**
     * 用非递归的方式实现对二叉树的中序遍历
     * 
     * @param root
     * @return
     */
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
     * 后续遍历非递归实现 保证根结点在左孩子和右孩子访问之后才能访问，因此对于任一结点P，先将其入栈。如果P不存在左孩子和右孩子，则可以直接访问它；
     * 或者P存在左孩子或者右孩子，但是其左孩子和右孩子都已被访问过了，则同样可以直接访问该结点
     */
    public static <T extends Comparable<T>> List<T> postOrderWithoutRecursion(BinaryTreeNode<T> root) {

	List<T> result = new ArrayList<T>();
	Stack<BinaryTreeNode<T>> s = new Stack<>();
	BinaryTreeNode<T> pre = null;
	BinaryTreeNode<T> cur = null;
	s.push(root);
	while (!(s.isEmpty())) {
	    cur = s.peek();
	    if ((cur.getRight() == null && cur.getLeft() == null)
		    || (pre != null && (pre == cur.getLeft() || pre == cur.getRight()))) {
		result.add(cur.getData());
		s.pop();
		pre = cur;
	    } else {
		// 入栈时保持栈顶元素是左子树
		if (cur.getRight() != null) {
		    s.push(cur.getRight());
		}
		if (cur.getLeft() != null) {
		    s.push(cur.getLeft());
		}
	    }
	}
	return result;
    }

    public static void main(String[] args) {
	BinaryTreeNode<Integer> binaryTreeNode = new BinaryTreeNode<>();
	binaryTreeNode.insert(4);
	binaryTreeNode.insert(1);
	binaryTreeNode.insert(3);
	binaryTreeNode.insert(5);
	binaryTreeNode.insert(2);
	System.out.println(preOrderVisit(binaryTreeNode));
	System.out.println(preOrderWithoutRecursion(binaryTreeNode));
	System.out.println(inOrderVisit(binaryTreeNode));
	System.out.println(inOrderWithoutRecursion(binaryTreeNode));
	System.out.println(postOrderVisit(binaryTreeNode));
	System.out.println(postOrderWithoutRecursion(binaryTreeNode));
    }

}
