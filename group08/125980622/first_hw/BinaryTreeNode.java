import com.sun.xml.internal.bind.v2.runtime.AttributeAccessor;

public class BinaryTreeNode {
	
	private int data;
	private BinaryTreeNode left;
	private BinaryTreeNode right;
	
	public int getData() {
		return data;
	}
	public void setData(int data) {
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
	
	public BinaryTreeNode insert(int o){
	    BinaryTreeNode returnNode = null;
		if (data < o) {
			if (right == null) {
				right = new BinaryTreeNode(o, null, null);
				returnNode = right;
			} else {
		        returnNode = right.insert(o);
			}
		} else if (data > o) {
			if (left == null) {
				returnNode = left = new BinaryTreeNode(o, null, null);
			} else {
				returnNode = left.insert(o);
			}
		}
        return returnNode;
    }

	BinaryTreeNode (int initData, BinaryTreeNode initLeft, BinaryTreeNode initRight) {
		data = initData;
		left = initLeft;
		right = initRight;
	}
}
