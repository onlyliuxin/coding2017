 package com.github.ipk2015.coding2017.basic.tree;

import java.util.ArrayList;
import java.util.List;

import com.github.ipk2015.coding2017.basic.queue.Queue;



public class BinarySearchTree<T extends Comparable> {
	
	BinaryTreeNode<T> root;
	public BinarySearchTree(BinaryTreeNode<T> root){
		this.root = root;
	}
	public BinaryTreeNode<T> getRoot(){
		return root;
	}
	public T findMin(){
		T min = findMinInOneNode(root,root.getData());
		return min;
	}
	
	private T findMinInOneNode(BinaryTreeNode<T> node,T result){
		BinaryTreeNode<T> rightNode = node.getRight();
		if(null != rightNode){
			T right = findMinInOneNode(rightNode,result);
			result = compareMin(right,result);
		}
		
		BinaryTreeNode<T> leftNode = node.getLeft();
		if(null != leftNode){
			T left = findMinInOneNode(leftNode,result);
			result = compareMin(left,result);
		}else{
			result = compareMin(node.getData(),result);
		}
		return result;
	}
	private T compareMin(T data,T min){
		int compareTo = min.compareTo(data);
		return compareTo > 0 ? data : min;
	}
	public T findMax(){
		T result = findMaxInOneNode(root,root.getData());
		return result;
	}
	private T findMaxInOneNode(BinaryTreeNode<T> node,T result){
		BinaryTreeNode<T> leftNode = node.getLeft();
		if(null != leftNode){
			T left = findMaxInOneNode(leftNode,result);
			result = compareMax(left,result);
		}
		
		BinaryTreeNode<T> rightNode = node.getRight();
		if(null != rightNode){
			T right = findMaxInOneNode(rightNode,result);
			result = compareMax(right,result);
		}else{
			result = compareMax(node.getData(),result);
		}
		return result;
	}
	private T compareMax(T data,T min){
		int compareTo = min.compareTo(data);
		return compareTo < 0 ? data : min;
	}
	
	public int height() {
	    return findHeightInOneNode(root);
	}
	
	private int findHeightInOneNode(BinaryTreeNode<T> node){
		BinaryTreeNode<T> left = node.getLeft();
		BinaryTreeNode<T> right = node.getRight();
		if(null == left && null == right){
			return 1;
		}
		int leftHeight = 0;
		if(null != left){
			leftHeight = findHeightInOneNode(left);
		}
		
		int rightHeight = 0;
		if(null != right){
			rightHeight = findHeightInOneNode(right);
		}
		
		return leftHeight > rightHeight ? leftHeight+1: rightHeight+1;
	}
	
	public int size() {
		List<T> list = BinaryTreeUtil.inOrderVisit(root);
		return list.size();
	}
	/*
	 * 首先我们找到待删除的节点Z，如果节点Z的两个孩子均为空，那么将其父节点中对应指向Z的指针置为空，然后删除节点Z。
	 * 如果节点Z仅有一个孩子，那么将Z节点的父节点中指向Z的指针指向Z仅有的孩子，然后删除节点Z。
	 * 如果节点Z有两个非空的子节点，那么找到节点Z的中序后继节点Y（即右子树的最左节点），将节点Y的Key值覆盖节点Z的Key值，
	 * 此时节点Y的两个孩子均为空或者只有一个右孩子，将节点Y的右指针里的值覆盖其父节点中指向Y的指针，然后删除节点Y。
	 * 注意：不可按节点数据是按左小右大来查找，因为删除后会发生错乱。
	 * */
	public void remove(T e){
		List<BinaryTreeNode<T>> list;
		boolean isRoot = e.compareTo(root.getData()) == 0;
		if(isRoot){
			BinaryTreeNode<T> virtualNode = new BinaryTreeNode<T>();
			virtualNode.right = root;
			list = new ArrayList<BinaryTreeNode<T>>();
			list.add(virtualNode);
			list.add(root);
		}else{
			list = findOneNodeAndParentNode(e);
		}
		if(list.isEmpty()){
			return;
		}
		BinaryTreeNode<T> parentNode = list.get(0);
		BinaryTreeNode<T> rmNode = list.get(1);
		
		boolean isLeft = null != parentNode.getLeft() && rmNode.getData().compareTo(parentNode.getLeft().getData()) == 0;
		BinaryTreeNode<T> leftNode = rmNode.getLeft();
		BinaryTreeNode<T> rightNode = rmNode.getRight();
		if(null == leftNode && null == rightNode){
			if(isLeft){
				parentNode.left = null;
			}else{
				parentNode.right = null;
			}
		}else if(null == leftNode && null != rightNode){
			if(isLeft){
				parentNode.left = rightNode;
			}else{
				parentNode.right = rightNode;
			}
			rmNode.right = null;
		}else if(null == rightNode && null != leftNode){
			if(isLeft){
				parentNode.left = leftNode;
			}else{
				parentNode.right = leftNode;
			}
			rmNode.left = null;
		}else if(null != rightNode && null != leftNode){
			BinaryTreeNode<T> leftParentInOrder = rightNode;
			BinaryTreeNode<T> leftNodeInOrder = leftParentInOrder.getLeft();
			if(null == leftNodeInOrder){
				rmNode.data = rightNode.getData();
				rmNode.right = rightNode.getRight();
				rightNode.right = null;
			}else{
				while(null != leftNodeInOrder.getLeft()){
					leftParentInOrder = leftNodeInOrder;
					leftNodeInOrder = leftParentInOrder.getLeft();
				}
				rmNode.data = leftNodeInOrder.getData();
				leftParentInOrder.left = leftNodeInOrder.right;
				leftNodeInOrder.right = null;
			}
		}
		if(isRoot){
			BinaryTreeNode<T> virtualParent = list.get(0);
			root = virtualParent.right;
			virtualParent.right = null;
		}
		
	}
	
	private List<BinaryTreeNode<T>> findOneNodeAndParentNode(T e){
		List<BinaryTreeNode<T>> list = new ArrayList<BinaryTreeNode<T>>();
		
		findNodeAParentByData(list,root,e);
		
		return list;
	}
	
	private boolean findNodeAParentByData(List<BinaryTreeNode<T>> list,BinaryTreeNode<T> node,T e){
		BinaryTreeNode<T> leftNode = node.getLeft();
		if(null !=leftNode){
			 if(e.compareTo(leftNode.getData()) == 0){
				 list.add(node);
				 list.add(leftNode);
				 return true;
			 }else{
				 boolean isFind1 = findNodeAParentByData(list,leftNode,e);
				 if(isFind1){
					 return true;
				 }
			 }
		}
		
		BinaryTreeNode<T> rightNode = node.getRight();
		if(null !=rightNode){
			 if(e.compareTo(rightNode.getData()) == 0){
				 list.add(node);
				 list.add(rightNode);
				 return true;
			 }else{
				 boolean isFind2 = findNodeAParentByData(list,rightNode,e);
				 if(isFind2){
					 return true;
				 }
			 }
		}
		return false;
	}
	
	public List<T> levelVisit(){
		List<T> list = new ArrayList();
		if(null == root){
			return list;
		}
		Queue queue = new Queue();
		queue.enQueue(root);
		
		while(!queue.isEmpty()){
			BinaryTreeNode<T> node = (BinaryTreeNode<T>)queue.deQueue();
			list.add(node.getData());
			BinaryTreeNode<T> left = node.getLeft();
			if(null != left){
				queue.enQueue(left);
			}
			
			BinaryTreeNode<T> right = node.getRight();
			if(null != right){
				queue.enQueue(right);
			}
		}
 		return list; 
	}
	/*
	 * 每一个节点的值都大于左节点的所有值，小于右节点的所有值
	 * */
	public boolean isValid(){
		return isNodeValid(root);
	}
	private boolean isNodeValid(BinaryTreeNode<T> node){
		if(null == node){
			return true;
		}
		BinaryTreeNode<T> left = node.getLeft();
		if(null != left){
			boolean isLeftNodeValid = isNodeValid(left);
			if(!isLeftNodeValid){
				return false;
			}
			if(node.getData().compareTo(findMaxInOneNode(left,left.getData())) <= 0){
				return false;
			}
		}
		BinaryTreeNode<T> right = node.getRight();
		if(null != right){
			boolean isRightNodeValid = isNodeValid(right);
			if(!isRightNodeValid){
				return false;
			}
			if(node.getData().compareTo(findMaxInOneNode(right,right.getData())) >= 0){
				return false;
			}
		}
		return true;
	}
	
	public T getLowestCommonAncestor(T n1, T n2){
		if(null == root){
			return null;
		}
		List<BinaryTreeNode<T>> list1 = getNodeAllAncestor(n1);
		List<BinaryTreeNode<T>> list2 = getNodeAllAncestor(n2);
		int size1 = list1.size();
		int size2 = list2.size();
		int minSize = size1 < size2? size1 :size2;
		T result = null;
		for(int i = 0;i < minSize; i++){
			T data1 = list1.get(size1-1-i).getData();
			if(data1.compareTo(list2.get(size2-1-i).getData()) != 0){
				break;
			}
			result = data1;
		}
		return result;
	}
	private List<BinaryTreeNode<T>> getNodeAllAncestor(T data){
		List<BinaryTreeNode<T>> list = new ArrayList();
		isAncestor(list,root,data);
		return list;
	}
	private boolean isAncestor(List<BinaryTreeNode<T>> list,BinaryTreeNode<T> node,T data){
		if(null == node){
			return false;
		}
		if(data.compareTo(node.getData()) == 0){
			list.add(node);
			return true;
		}
		BinaryTreeNode<T> left = node.getLeft();
		if(null != left){
			boolean isLeftAncetor = isAncestor(list,left,data);
			if(isLeftAncetor){
				list.add(node);
				return true;
			}
		}
		BinaryTreeNode<T> right = node.getRight();
		if(null != right){
			boolean isRightAncetor = isAncestor(list,right,data);
			if(isRightAncetor){
				list.add(node);
				return true;
			}
		}
		return false;
	}
	
	public List<T> getNodesBetween(T n1, T n2){
		List<T> list = new ArrayList();
		getNodesBetween(root,list,n1,n2);
		return list;
	}
	private void getNodesBetween(BinaryTreeNode<T> node,List<T> list,T n1,T n2){
		if(null == node){
			return;
		}
		T data = node.getData();
		if(data.compareTo(n2) >= 0){
			getNodesBetween(node.getLeft(),list,n1,n2);
			return;
		}
		if(data.compareTo(n1) <= 0){
			getNodesBetween(node.getRight(),list,n1,n2);
			return;
		}
		list.add(data);
		getNodesBetween(node.getLeft(),list,n1,n2);
		getNodesBetween(node.getRight(),list,n1,n2);
	}
	
}

