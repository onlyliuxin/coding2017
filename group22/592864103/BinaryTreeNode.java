public class BinaryTreeNode {

	private Integer data;
	private BinaryTreeNode left;
	private BinaryTreeNode right;

	public Integer getData() {
		return data;
	}
	public void setData(Integer data) {
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

	public BinaryTreeNode insert(Integer o){
		if(o>getData()){
			if(this.right==null){
				BinaryTreeNode node = new BinaryTreeNode();
				node.data = o;
				node.left = null;
				node.right = null;
				this.right = node;
				return node;
			}else{
				getRight();
				insert(o);
				return null;
			}
		}else if(o<getData()){
			if (this.left == null){
				BinaryTreeNode node = new BinaryTreeNode();
				node.data = o;
				node.left = null;
				node.right = null;
				this.left = node;
				return node;
			}else{
				getLeft();
				insert(o);
				return null;
			}
		}else{
			System.out.println("出现重复元素，请检查输入！");
			return null;
		}
	}

}
