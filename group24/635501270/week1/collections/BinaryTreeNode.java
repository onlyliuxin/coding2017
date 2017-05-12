package week1.collections;

public class BinaryTreeNode {
	
	private Object data;
	private BinaryTreeNode left;
	private BinaryTreeNode right;
	
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
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
	
	public BinaryTreeNode insert(Object o){
		if(data == null){
			data = o;
			return this;
		}
		if(o instanceof Integer || this.data instanceof Integer){
			int compare = (Integer)this.data - (Integer)o;
			if(compare > 0){
				if(this.left == null){
					this.left = new BinaryTreeNode();
					this.left.data = o;
					return this.left;
				}else{
					return this.left.insert(o);
				}
			}else if(compare < 0){
				if(this.right == null){
					this.right = new BinaryTreeNode();
					this.right.data = o;
					return this.right;
				}else{
					return this.right.insert(o);
				}
			}else{
				return this;
			}
		}
		return  null;
	}
}
