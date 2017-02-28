package week01.basic;

public class MyBinaryTreeNode {

	private Object data;
	private MyBinaryTreeNode left;
	private MyBinaryTreeNode right;

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public MyBinaryTreeNode getLeft() {
		return left;
	}

	public void setLeft(MyBinaryTreeNode left) {
		this.left = left;
	}

	public MyBinaryTreeNode getRight() {
		return right;
	}

	public void setRight(MyBinaryTreeNode right) {
		this.right = right;
	}

	public MyBinaryTreeNode insert(Object o) {
		if(this.getData() == null && this.getLeft() == null && this.getRight() == null){
			this.setData(o);
			this.setLeft(null);
			this.setRight(null);
			return this;
		}
		
		MyBinaryTreeNode node = new MyBinaryTreeNode();
		MyBinaryTreeNode currentNode = this;
		while(true){
			if((Integer) o < (Integer) getData()){
				if(currentNode.getLeft() == null){
					node.setData(o);
					node.setLeft(null);
					node.setRight(null);
					
					currentNode.setLeft(node);
					return this;
				}else{
					currentNode = currentNode.getLeft();
				}
				
			}else{
				if(currentNode.getRight() == null){
					node.setData(o);
					node.setLeft(null);
					node.setRight(null);
					
					currentNode.setRight(node);
					return this;
				}else{
					currentNode = currentNode.getRight();
				}
			}
		}
	}
}
