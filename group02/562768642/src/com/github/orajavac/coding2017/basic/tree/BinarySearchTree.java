package com.github.orajavac.coding2017.basic.tree;

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
	public int height() {
	    return -1;
	}
	public int size() {
		return -1;
	}
	public void remove(T e){
		
	}
	
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
	
}