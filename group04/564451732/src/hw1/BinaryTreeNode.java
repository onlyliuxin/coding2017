package hw1;

public class BinaryTreeNode {
	private Comparable data;
	private BinaryTreeNode left;
	private BinaryTreeNode right;
	private BinaryTreeNode root;
	
	public BinaryTreeNode (Comparable data) {
		this.data = data;
	}
	public BinaryTreeNode () {
		
	}
	public Object getData() {
		return data;
	}
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
		return;
	}
	
	
	
	public BinaryTreeNode getRoot() {
		return root;
	}
	public void setRoot(BinaryTreeNode root) {
		this.root = root;
	}
	public BinaryTreeNode insert(Comparable o){
		BinaryTreeNode result = new BinaryTreeNode(o);
		if (root == null) {
			root = result;
		} else {
			BinaryTreeNode dummy = root;
			insertHelper(o,dummy);
			
		}
		return result;
	}
	
	private void insertHelper (Comparable o, BinaryTreeNode root) {
		if (o.compareTo(root.data) < 0) {
			if (root.left == null) {
				root.left = new BinaryTreeNode(o);
			} else {
				insertHelper (o, root.left);
			}
		} else {
			if (root.right == null) {
				root.right = new BinaryTreeNode(o);
				
			} else {
				insertHelper (o, root.right);
			}
		}
	}
}
