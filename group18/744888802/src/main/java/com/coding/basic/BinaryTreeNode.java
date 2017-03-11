package com.coding.basic;

public class BinaryTreeNode {
	
	private Object data;
	private BinaryTreeNode left;
	private BinaryTreeNode right;
	
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public BinaryTreeNode getLeft() {
		return left;
	}
	public void setLeft(BinaryTreeNode left) {
		this.left = left;
	}
	public BinaryTreeNode getRight() {
		return right;
	}
	public void setRight(BinaryTreeNode right) {
		this.right = right;
	}

	public BinaryTreeNode insert(Object o) {

		BinaryTreeNode binaryTreeNode = new BinaryTreeNode();
		binaryTreeNode.data = o;

		add(this, binaryTreeNode);
		return this;
	}

	private void add(BinaryTreeNode binaryTreeNodeOld, BinaryTreeNode binaryTreeNodeNew) {
		if (binaryTreeNodeOld.data == null) {
			binaryTreeNodeOld.data = binaryTreeNodeNew.data;
			return;
		}


		if (binaryTreeNodeOld.left == null) {
			binaryTreeNodeOld.left = binaryTreeNodeNew;
			return;
		}
		if (binaryTreeNodeOld.right == null) {
			if (comparator(binaryTreeNodeNew, binaryTreeNodeOld.left)){
				binaryTreeNodeOld.right = binaryTreeNodeNew;
			}else{
				binaryTreeNodeOld.right = binaryTreeNodeOld.left;
				binaryTreeNodeOld.left = binaryTreeNodeNew;
			}
			return;
		}

		if(comparator(binaryTreeNodeOld.left, binaryTreeNodeNew))
		{
			add(binaryTreeNodeOld.left,binaryTreeNodeNew);
			return;
		}

		if(comparator(binaryTreeNodeOld.right, binaryTreeNodeNew)){
			add(binaryTreeNodeOld.right,binaryTreeNodeNew);
			return;
		}else{
			binaryTreeNodeNew.left = binaryTreeNodeOld.right;
			binaryTreeNodeOld.right = binaryTreeNodeNew;
		}




	}

	private boolean comparator(BinaryTreeNode binaryTreeNode1, BinaryTreeNode binaryTreeNode2) {
		if ((Integer) binaryTreeNode1.getData() > (Integer) binaryTreeNode2.getData()) {
			return true;
		}
		return false;
	}
	
}
