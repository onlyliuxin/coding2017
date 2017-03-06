package com.sx.structures;

public class BinaryTree {

	private BinaryNode root;

	public BinaryTree(Object o) {
		root = new BinaryNode(o);
	}

	public void insert(Object o) {
		BinaryNode node = new BinaryNode(o);
		insert(root, node);
	}

	private void insert(BinaryNode root, BinaryNode node) {
		if (node.compareTo(root) > 0) {
			if (root.getRight() == null) {
				root.setRight(node);
				return;
			}
			insert(root.getRight(), node);
		} else {
			if (root.getLeft() == null) {
				root.setLeft(node);
				return;
			}
			insert(root.getLeft(), node);
		}

	}
	
	public void preOrder(BinaryNode root){
		order(root);
		if(root.getLeft()!=null)
			preOrder(root.getLeft());
		if(root.getRight()!=null)
			preOrder(root.getRight());
	}
	
	public void postOrder(BinaryNode root){
		if(root.getLeft()!=null)
			postOrder(root.getLeft());
		if(root.getRight()!=null)
			postOrder(root.getRight());
		order(root);
	}
	
	public void inOrder(BinaryNode root){
		if(root.getLeft()!=null)
			inOrder(root.getLeft());
		order(root);
		if(root.getRight()!=null)
			inOrder(root.getRight());
	}
	
	/**
	 * 未实现
	 * @param root
	 * @param node
	 * @return
	 */
	private boolean remove(BinaryNode root, BinaryNode node){
		return false;
	}
	
    public BinaryNode getRoot(){
    	return root;
    }
    
    private void order(BinaryNode root){
    	System.out.print(root.getData()+" ");
    }
}
