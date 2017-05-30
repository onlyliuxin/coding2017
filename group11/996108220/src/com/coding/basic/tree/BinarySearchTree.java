package com.coding.basic.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.coding.basic.tree.BinaryTreeNode;
import com.sun.corba.se.impl.orbutil.graph.Node;


public class BinarySearchTree<T extends Comparable> {
	
	BinaryTreeNode<T> root;
	int size;
	public BinarySearchTree(BinaryTreeNode<T> root){
		this.root = root;
	}
	public BinaryTreeNode<T> getRoot(){
		return root;
	}
	public T findMin(){
		return findMin(root);
	}
	public T findMin(BinaryTreeNode<T> node){
		while (node.left!=null) {
			node=node.left;		
		}
		return node.data;
	}
	public T findMax(){
		BinaryTreeNode<T> node=root;
		while (node.right!=null) {
			node=node.right;		
		}
		return node.data;
	}
	public int height() {
		
		return height(root);

	}
	private int height(BinaryTreeNode<T> node) {
		if (node==null) {
			return 0;
		}
		else {
			int lheight=height(node.left);
			int rheight=height(node.right);
			int height=lheight>rheight?lheight:rheight;
		    return height+1;
		}
	}
	public int size() {
		return size(root);
	}
	private int size(BinaryTreeNode<T> node) {
		if (node==null) {
			return 0;
		}
		else {
			return size(node.left)+size(node.right)+1;
		}
	}
	public void remove(T e){
		BinaryTreeNode<T> node=searchNode(e, root);
		if (node==null) {
			return;
		}
		if (node.left==null&&node.right!=null) {
			node.data=node.right.data;
			node.right=null;
		}
		else if (node.right==null&&node.left!=null) {
			node.data=node.left.data;
			node.left=null;
		}
		else if (node.right==null&&node.left==null) {
			node=null;
		}
		else {
			T data=findMin(node.right);
			remove(data);
			node.data=data;
			
		}
		
	}
    public BinaryTreeNode searchNode(T key,BinaryTreeNode<T> node) {
    	if (node!=null) {
    		if (node.data.compareTo(key)==0) {
				return node;
			}
    		else if (node.data.compareTo(key)>0) {
    			return searchNode(key,node.left);
			}
    		else {
    			return searchNode(key,node.right);
			}	
		}
    	else{
    		return null;
    	}					
	}
	public void insert(T o){
		if (size==0) {
			root=new BinaryTreeNode<T>(o);
			}
		else{
			insert(o,root);	
			}
		size++;
		}
	
	private void insert(T o,  BinaryTreeNode<T> ptr) {
		if ((ptr.right==null&&(ptr.data.compareTo(o)<=0))){
			ptr.right=new BinaryTreeNode<T>(o);	
		}
		else if (ptr.left==null&&(ptr.data.compareTo(o)>0)) {
			ptr.left=new BinaryTreeNode<T>(o);

		} 
		else if (ptr.left!=null&&(( ptr.data).compareTo(o)>0)) {
			insert(o, ptr.left);
		}
		else {
			insert(o, ptr.right);
		}		
	}
    public void preOrder(BinaryTreeNode Node)
    {
        if (Node != null)
        {
            System.out.print(Node.data + " ");
            preOrder(Node.left);
            preOrder(Node.right);
        }
    }

    /**
     * 中序遍历
     */
    public void midOrder(BinaryTreeNode Node)
    {
        if (Node != null)
        {
            midOrder(Node.left);
            System.out.print(Node.data + " ");
            midOrder(Node.right);
        }
    }
    public void midOrder(BinaryTreeNode<T> node,List<T> list) {
        if (node != null)
        {
            midOrder(node.left,list);
            list.add(node.data);
            midOrder(node.right,list);
        }
	}
    /**
     * 后序遍历
     */
    public void posOrder(BinaryTreeNode Node)
    {
        if (Node != null)
        {
            posOrder(Node.left);
            posOrder(Node.right);
            System.out.print(Node.data + " ");
        }
    }	
    public List<T> levelVisit(){
    	List<BinaryTreeNode<T>> queue=new LinkedList<BinaryTreeNode<T>>();
		queue.add(root);
		List<T> list=new ArrayList<T>();	
		while (queue.size()!=0) {
			BinaryTreeNode<T> node=queue.remove(0);
			list.add(node.data);
			if (node.left!=null) {
				queue.add(node.left);
			}
			if (node.right!=null) {
				queue.add(node.right);
			}
		}
		return list;
	}
	public boolean isValid(){
		ArrayList<T> list=new ArrayList<T>();
		midOrder(root, list);
		for (int i = 0; i < list.size()-1; i++) {
			if (list.get(i).compareTo(list.get(i+1))>=0) {
				return false;
			}
		}
		return true;
	}
	public T getLowestCommonAncestor(T n1, T n2){
		 return getLowestCommonAncestor(root,n1,n2);
	}

	private T getLowestCommonAncestor(BinaryTreeNode<T> node, T n1, T n2) {
		if (node.data.compareTo(n1)<0&&node.data.compareTo(n2)<0) {
			return getLowestCommonAncestor(node.right, n1, n2);
		}
		else if (node.data.compareTo(n1)>0&&node.data.compareTo(n2)>0) {
			return getLowestCommonAncestor(node.left, n1, n2);
		}
		else {
			return node.data;
		}	
	}
	/**
	 * 返回所有满足下列条件的节点的值：  n1 <= n <= n2 , n 为
	 * 该二叉查找树中的某一节点
	 * @param n1
	 * @param n2
	 * @return
	 */
	public List<T> getNodesBetween(T n1, T n2){
		T key=getLowestCommonAncestor(n1, n2);
		List<T> list=new ArrayList<>();
		BinaryTreeNode<T> node=findNode(key, root);
		getNodesBetween(n1, n2,list,node);
		
		return list;
	}
    private void getNodesBetween(T n1, T n2, List<T> list,
			BinaryTreeNode<T> node) {
		if (node.data.compareTo(n1)<0&&node.data.compareTo(n2)<0) {
			getNodesBetween(n1, n2, list, node);
		}
		else if (node.data.compareTo(n1)>0&&node.data.compareTo(n2)>0) {
			getNodesBetween(n1, n2, list, node);
		}
		else {
			list.add(node.data);
		}	
	}

	public BinaryTreeNode findNode(T key,BinaryTreeNode<T> node) {
    	if (node!=null) {
    		if (node.data.compareTo(key)==0) {
				return node;
			}
    		else if (node.data.compareTo(key)>0) {
    			return findNode(key,node.left);
			}
    		else {
    			return findNode(key,node.right);
			}	
		}
    	else{
    		return null;
    	}					
	}
}

