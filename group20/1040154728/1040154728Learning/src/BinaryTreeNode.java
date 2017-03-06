public class BinaryTreeNode <T extends Comparable> {
	private T data;
	private BinaryTreeNode left;
	private BinaryTreeNode right;
	private int size;
    public T getData() {
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

	

}
