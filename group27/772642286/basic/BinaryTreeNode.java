package week01.basic;

public class BinaryTreeNode<T extends Comparable> {
	
	private T data;
	private BinaryTreeNode left;
	private BinaryTreeNode right;
	
	public Object getData() {
		return data;
	}
	public void setData(T data) {
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
	
	public BinaryTreeNode insert(T o){
		return insert(this,o);
	}
	
	
	public BinaryTreeNode insert(BinaryTreeNode root,T o){
		if(o.compareTo(root) >= 0){
			if(root.left == null){
				BinaryTreeNode binaryTreeNode = new BinaryTreeNode();
				root.left = binaryTreeNode;
				binaryTreeNode.data = o;
				return binaryTreeNode;
			}
			return insert(root.left, o);
		}
		else{
			if (root.right == null) {
				BinaryTreeNode binaryTreeNode = new BinaryTreeNode();
				root.right = binaryTreeNode;
				binaryTreeNode.data = o;
				return binaryTreeNode;
			}
			return insert(root.right, o);
		}
	}
		
	
}
