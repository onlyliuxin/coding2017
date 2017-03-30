package com.xusheng.tree;

/**
 * 实现平衡二叉树
 * @author xusheng
 *
 * @param <T>
 */
public class AvlTree<T extends Comparable<? super T>> {

	private AvlNode<T> root;
	
	public AvlTree() {
		this.root = null;
	}
	
	/**
	 * 对不平衡点的左儿子的左子树进行插入
	 * 将二叉树围绕不平衡点的左子节点进行左旋转
	 * @param k2
	 * @return
	 */
	private AvlNode<T> rotateWithLeftChild(AvlNode<T> k2){
		AvlNode<T> k1 = k2.leftNode;
		k2.leftNode = k1.rightNode;
		k1.rightNode = k2;
		k2.height = Math.max(height(k2.leftNode), height(k2.rightNode))+1;
		k1.height = Math.max(height(k1.leftNode), k2.height)+1;
		return k1;
	}
	
	/**
	 * 对不平衡点的右儿子的右子树进行插入
	 * 将二叉树围绕不平衡点的右子节点进行旋转
	 * @param k2
	 * @return
	 */
	private AvlNode<T> rotateWithRightChild(AvlNode<T> k2){
		AvlNode<T> k1 = k2.rightNode;
		k2.rightNode = k1.leftNode;
		k1.leftNode = k2;
		k2.height = Math.max(height(k2.leftNode), height(k2.rightNode))+1;
		k1.height = Math.max(height(k1.rightNode), k2.height)+1;
		return k1;
	}
	
	/**
	 * 对不平衡点的左儿子的右子树进行插入
	 * 先将二叉树不平衡点的左儿子围绕它的右子节点旋转
	 * 后将二叉树围绕不平衡点的左儿子进行旋转
	 * @param k3
	 * @return
	 */
	private AvlNode<T> doubleRotateWithLeftChild(AvlNode<T> k3){
		k3.leftNode = rotateWithRightChild(k3.leftNode);
		return rotateWithLeftChild(k3);
	}
	
	/**
	 * 对不平衡点的右儿子的左子树进行插入
	 * 先将二叉树不平衡点的右儿子围绕它的左子节点旋转
	 * 后将二叉树围绕不平衡点的右儿子进行旋转
	 * @param k3
	 * @return
	 */
	private AvlNode<T> doubleRotateWithRightChild(AvlNode<T> k3){
		k3.rightNode = rotateWithLeftChild(k3.rightNode);
		return rotateWithRightChild(k3);
	}
	
	private static final int ALLOW_IMBALANCE = 1;//该常量表示数的高度差最多为1
	/**
	 * 此方法用来对二叉树进行平衡
	 * @param node
	 * @return
	 */
	private AvlNode<T> balance(AvlNode<T> node){
		
		if(node == null){
			return node;
		}
		
		if(height(node.leftNode) - height(node.rightNode) > ALLOW_IMBALANCE){
			if(height(node.leftNode.leftNode) >= height(node.leftNode.rightNode)){
				node = rotateWithLeftChild(node);
			}else{
				node = doubleRotateWithLeftChild(node);
			}
		}else if(height(node.rightNode) - height(node.leftNode) > ALLOW_IMBALANCE){
			if(height(node.rightNode.rightNode) >= height(node.rightNode.leftNode)){
				node = rotateWithRightChild(node);
			}else{
				node = doubleRotateWithRightChild(node);
			}
		}
		
		node.height = Math.max(height(node.leftNode), height(node.rightNode))+1;
		
		return node;
	}
	private int height(AvlNode<T> t){
		return t == null ? -1 : t.height;
	}

	public boolean isEmpty(){
		return this.root == null;
	}
	
	public void insert(T element){
		root = insert(element,root);
	}
	
	private AvlNode<T> insert(T element,AvlNode<T> node){
		if(node == null){
			return new AvlNode<T>(element,null,null); 
		}
		
		int intCompare = element.compareTo(node.data);
		
		if(intCompare < 0){
			node.leftNode = insert(element,node.leftNode);
		}else if(intCompare > 0){
			node.rightNode = insert(element,node.rightNode);
		}
		return node;
	}
	
	
	public boolean contains(T element){
		return contains(element,root);
	}
	
	private boolean contains(T element,AvlNode<T> node){
		
		if(node == null){
			return false;
		}
		
		int intCompare = element.compareTo(node.data);
		
		if(intCompare < 0){
			return contains(element,node.leftNode);
		}else if(intCompare > 0){
			return contains(element,node.rightNode);
		}else{
			return true;
		}
	}
	
	public void remove(T element){
		remove(element,root);
	}
	
	private AvlNode<T> remove(T element,AvlNode<T> node){
		if(node == null){
			return node;
		}
		
		int intCompare = element.compareTo(node.data);
		
		if(intCompare < 0){
			node.leftNode = remove(element,node.leftNode);
		}else if(intCompare > 0){
			node.rightNode = remove(element,node.rightNode);
		}
		else if(node.leftNode != null && node.rightNode != null){
			node.data = findMin(node).data;
			node.rightNode = remove(node.data,node.rightNode);
		}else{
			node = (node.leftNode != null) ? node.leftNode : node.rightNode;
		}
		return node;
	}
	

	private AvlNode<T> findMin(AvlNode<T> node){
		if(node == null){
			return null;
		}else if(node.leftNode == null){
			return node;
		}else{
			return findMin(node.leftNode);
		}
	}
	
	private AvlNode<T> findMax(AvlNode<T> node){
		if(node == null){
			return null;
		}
		
		while(node.rightNode != null){
			node = node.rightNode;
		}
		
		return node;
	}

	private class AvlNode<T>{
		
		private T data;
		private AvlNode<T> leftNode;
		private AvlNode<T> rightNode;
		private int height;
		
		public AvlNode(T data, AvlNode<T> leftNode,AvlNode<T> rightNode) {
			this.data = data;
			this.leftNode = leftNode;
			this.rightNode = rightNode;
		}
		
		public AvlNode(T element) {
			this(element,null,null);
		}
		
		
	}
}
