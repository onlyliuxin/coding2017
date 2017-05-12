package com.coding.basic;

import com.coding.basic.array.ArrayList;

public class BinaryTree<T extends Comparable<T>> {
	
	//根节点
	private BinaryTreeNode<T> root;	
	    	
	public BinaryTreeNode<T> insert(T o){
		
		BinaryTreeNode<T> treeNode = new BinaryTreeNode<T>();
		treeNode.setData(o);
		if(root == null){
			root =  treeNode;
		}else{				
			BinaryTreeNode<T> currentNode = root;
			BinaryTreeNode<T> parent;
			while(true){
				parent = currentNode;
				if((currentNode.getData()).compareTo(o)>0){//向左放					
					currentNode = currentNode.getLeft();
					if(currentNode == null){
						parent.setLeft(treeNode);
						treeNode.setParent(parent);
						break;
					}					
				}else{//向右放
					currentNode = currentNode.getRight();
					if(currentNode == null){
						parent.setRight(treeNode);
						treeNode.setParent(parent);
						break;
					}
				}
			}			
		}
		return treeNode;
	}

	/**
	 * 先序遍历
	 * @param node
	 * @return
	 */
	public List<T> traversalBefore(BinaryTreeNode<T> node){
		//所有数据集合
	    List<T> datas = new ArrayList<>();
	    return traversal(node,datas);
	}
	private List<T> traversal(BinaryTreeNode<T> node,List<T> datas){
		
		if(node !=null){
			datas.add(node.getData());
			traversal(node.getLeft(),datas);
			traversal(node.getRight(),datas);
		}
		return datas;
	}

	public BinaryTreeNode<T> getRoot() {
		return root;
	}
	
	/**
	 * 获取比obj大的最小值
	 * @param obj
	 * @return
	 */
	public T getLeastBigger (T obj){
		
		BinaryTreeNode<T> left = root;
		while(left.getLeft()!=null){
			left = left.getLeft();
		}
		return left.getData();
	}
	
}
