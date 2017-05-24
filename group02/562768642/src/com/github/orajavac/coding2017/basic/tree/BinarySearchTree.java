package com.github.orajavac.coding2017.basic.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import com.github.orajavac.coding2017.basic.BinaryTreeNode;
import com.github.orajavac.coding2017.basic.stack.Stack;

@SuppressWarnings("rawtypes")
public class BinarySearchTree<T extends Comparable> {
	
	private static Stack s = new Stack();
	BinaryTreeNode<T> root;
	public BinarySearchTree(BinaryTreeNode<T> root){
		this.root = root;
	}
	public BinaryTreeNode<T> getRoot(){
		return root;
	}
	public T findMin(){
		T result = findMinOrMax(Integer.parseInt(getRoot().getData().toString()),getRoot(),false);
		root.setLeft(root.getRight());
		result = findMinOrMax((Integer) result,getRoot(),false);
		return result;
	}
	public T findMax(){
		T result = findMinOrMax(Integer.parseInt(getRoot().getData().toString()),getRoot(),true);
		root.setLeft(root.getRight());
		result = findMinOrMax((Integer) result,getRoot(),true);
		return result;
	}
	
	public int height(){
		return height(root);
	}
	
	public int height(BinaryTreeNode<T> root) {
		if(root==null){
			return 0;
		}else{
			int l = height(root.left);
			int r = height(root.right);
			if (l>r){
				return l+1;
			}else{
				return r+1;
			}
		}
	}
	
	public int size(){
		return size(root);
	}
	
	public int size(BinaryTreeNode<T> root) {
		if(root==null){
			return 0;
		}
		return size(root.left)+1+size(root.right);
	}
	
	
	public void remove(T e){
		
	}
	
	@SuppressWarnings("unchecked")
	public T findMinOrMax(Integer num,BinaryTreeNode<T> node,boolean bol){
		while(true){
			if (node.getLeft()!=null){
				if (bol){
					if (Integer.parseInt(node.getLeft().getData().toString()) > num){	//max
						num = Integer.parseInt(node.getLeft().getData().toString());
					}
				}else{
					if (Integer.parseInt(node.getLeft().getData().toString()) < num){	//min
						num = Integer.parseInt(node.getLeft().getData().toString());
					}
				}
				s.push(node.getLeft());
				node = node.getLeft();
			}else{
				node = (BinaryTreeNode<T>)s.pop();
				node.setLeft(node.getRight());
			}
			if (node.getLeft()==null&&s.length()==0)
				break;
		}
		return (T)num;
	}
	
	@SuppressWarnings("unchecked")
	public List<T> levelVisit(){
		Queue q = new LinkedList();
		List<T> result = new ArrayList<T>();
		result.add(root.data);
		q.add(root.getLeft());
		q.add(root.getRight());
		while(q.size()!=0){
			BinaryTreeNode<T> node = (BinaryTreeNode<T>)q.poll();
			result.add(node.data);
			if (node!=null&&node.left!=null){
				q.add(node.getLeft());
			}
			if (node!=null&&node.right!=null){
				q.add(node.getRight());
			}
		}
		return result;
	}
	
	public boolean isValid(){
		return isValid(root,Integer.parseInt(root.data.toString()));
	}
	
	public boolean isValid(BinaryTreeNode<T> node,int d){
		boolean f = false;
		if (node == null){
			return false;
		}
		if (Integer.parseInt(node.data.toString()) <= d) {
			f = true;
			d = Integer.parseInt(node.data.toString());
			isValid(node.left,d);
		}else{
			f = false;
		}
		
		if (Integer.parseInt(node.data.toString()) >= d) {
			f = true;
			d = Integer.parseInt(node.data.toString());
			isValid(node.right,d);
		}else{
			f = false;
		}
		
		return f;
	}
	
	public T getLowestCommonAncestor(T n1, T n2){
		return getLowestCommonAncestor(root,n1,n2);
        
	}
	
	public T getLowestCommonAncestor(BinaryTreeNode<T> n,T n1, T n2){
		if (root == null)
			return null;
		if ((Integer.parseInt(root.data.toString()) >= (Integer) n1 && Integer
				.parseInt(root.data.toString()) <= (Integer) n2)
				|| (Integer.parseInt(root.data.toString()) >= (Integer) n1 && Integer
						.parseInt(root.data.toString()) <= (Integer) n2))
			return (T) root.data.toString();
		else if (Integer.parseInt(root.data.toString()) > (Integer) n1
				&& Integer.parseInt(root.data.toString()) > (Integer) n2)
			return getLowestCommonAncestor(root.left, n1, n2);
		else if (Integer.parseInt(root.data.toString()) < (Integer) n1
				&& Integer.parseInt(root.data.toString()) < (Integer) n2)
			return getLowestCommonAncestor(root.right, n1, n2);
		return null;       
	}
	
	public List<T> getNodesBetween(T n1, T n2){
		List<T> result = new ArrayList<T>();
		List<T> seq = new ArrayList<T>();
		result.add((T) root.getData());
		BinaryTreeNode<T> node = root;
		getNodesBetweenProcess(node,result,seq,n1,n2);
		node.setLeft(root.getRight());
		getNodesBetweenProcess(node,result,seq,n1,n2);
		return seq;		
	}
	
	public List<T> getNodesBetweenProcess(BinaryTreeNode<T> node,List<T> result,List<T> seq,
			T n1, T n2){
		while(true){
			if (node.getLeft()!=null){				
				if (Integer.parseInt(node.getLeft().getData().toString()) >= Integer
						.parseInt(n1.toString())
						&& Integer
								.parseInt(node.getLeft().getData().toString()) <= Integer
								.parseInt(n2.toString())) {
					seq.add((T) node.getLeft().getData());
				}
				s.push(node.getLeft());
				node = node.getLeft();
			}else{
				node = (BinaryTreeNode<T>)s.pop();
				node.setLeft(node.getRight());
			}
			if (node.getLeft()==null&&s.length()==0)
				break;
		}
		return seq;
	}
}