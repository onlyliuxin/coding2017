package main;

public class BinaryTreeNode {
	@SuppressWarnings("rawtypes")
	private Comparable data;
	private BinaryTreeNode left;
	private BinaryTreeNode right;

	@SuppressWarnings("rawtypes")
	public BinaryTreeNode(Comparable data, BinaryTreeNode left, BinaryTreeNode right) {
		this.data = data;
		this.left = left;
		this.right = right;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public BinaryTreeNode insert(Comparable o) {
		int result = o.compareTo(data);
		if (0 == result) {
			return this;
		} else if (0 < result) {
			if (null == this.right) {
				BinaryTreeNode node = new BinaryTreeNode(o, null, null);
				this.right = node;
				return node;
			}
			return this.right.insert(o);
		} else {
			if (null == this.left) {
				BinaryTreeNode node = new BinaryTreeNode(o, null, null);
				this.left = node;
				return node;
			}
			return this.left.insert(o);
		}

	}

	@SuppressWarnings("rawtypes")
	public Comparable getData() {
		return data;
	}

	@SuppressWarnings("rawtypes")
	public void setData(Comparable data) {
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

}
