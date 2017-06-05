package structure.week11;

public class BinaryTreeNode<T> {
	public BinaryTreeNode(T _val){
		this.val = _val;
		this.left = null;
		this.right = null;
	}
	public void setLeft(BinaryTreeNode<T> node){
		this.left = node;
	}
	public BinaryTreeNode<T> getLeft(){
		return this.left;
	}
	public void setRight(BinaryTreeNode<T> node){
		this.right = node;
	}
	public BinaryTreeNode<T> getRight(){
		return this.right;
	}
    T val;
    BinaryTreeNode<T> left;
    BinaryTreeNode<T> right;
}
