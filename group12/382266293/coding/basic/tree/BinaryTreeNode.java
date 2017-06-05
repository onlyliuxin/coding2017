package tree;

import java.util.Objects;

public class BinaryTreeNode<T> {
	
	T data;
	BinaryTreeNode<T> left;
	BinaryTreeNode<T> right;

	public BinaryTreeNode(T data){
		this.data=data;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public BinaryTreeNode<T> getLeft() {
		return left;
	}
	public void setLeft(BinaryTreeNode<T> left) {
		this.left = left;
	}
	public BinaryTreeNode<T> getRight() {
		return right;
	}
	public void setRight(BinaryTreeNode<T> right) {
		this.right = right;
	}
	
	public BinaryTreeNode<T> insert(Object o){
		
		if (Objects.equals(this.data, o)) {
			return null;
		} else if ((int) this.data - (int)o > 0) {
			if (this.getLeft() == null) {
				BinaryTreeNode<T> res = new BinaryTreeNode<T>((T)o);
				this.setLeft(res);
			} else {
				this.getLeft().insert(o);
			}
		} else if ((int) this.data - (int)o < 0) {
			if (this.getRight() == null) {
				BinaryTreeNode<T> res = new BinaryTreeNode<T>((T)o);
				this.setRight(res);
			} else {
				this.getRight().insert(o);
			}
		}

		return null;

	}
	
	
	
	@Override
	public String toString() {
		return "[data=" + data + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BinaryTreeNode other = (BinaryTreeNode) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		return true;
	}
	
	public BinaryTreeNode<T> findMaxNode() {

		BinaryTreeNode<T> node = this;
		while(node.getRight() != null) {
			node = node.getRight();
		}

		return node;
	}
	
	public BinaryTreeNode<T> findMinNode() {

		BinaryTreeNode<T> node = this;
		while(node.getLeft() != null) {
			node = node.getLeft();
		}

		return node;
	}
	
}
