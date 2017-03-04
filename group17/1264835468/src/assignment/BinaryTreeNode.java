package assignment;

public class BinaryTreeNode<T extends Comparable<? super T>> {
	private T data;
	private BinaryTreeNode<T> left;
	private BinaryTreeNode<T> right;

	public BinaryTreeNode(T data) {
		this.data = data;
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

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("node:" + data);
		// 非叶节点则加上左右子节点data
		if (left != null || right != null) {
			if (left != null)
				stringBuilder.append(",left:" + left.data);
			else
				stringBuilder.append(",left:null");
			if (right != null)
				stringBuilder.append(",right:" + right.data);
			else
				stringBuilder.append(",right:null");
		}
		return stringBuilder.toString();
	}

	public static void main(String[] args) {
		// BinaryTreeNode<Integer> binaryTreeNode = new BinaryTreeNode<>(1);
	}

}
