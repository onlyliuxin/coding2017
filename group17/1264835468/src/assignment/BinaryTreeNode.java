package assignment;

//
public class BinaryTreeNode implements Comparable<BinaryTreeNode> {
	private Comparable data;
	private BinaryTreeNode left;
	private BinaryTreeNode right;

	public BinaryTreeNode(Comparable data) {
		this.data = data;
	}

	public Comparable getData() {
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
	}

	@Override
	public int compareTo(BinaryTreeNode o) {
		return data.compareTo(o.data);
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
}
