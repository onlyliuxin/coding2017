package my.collection.linear;

public class MyBinaryTreeNode implements Comparable<Object>{
	
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
	
	public MyBinaryTreeNode insert(Object o){
		//cast to MyBinaryTreeNode
		MyBinaryTreeNode newNode = new MyBinaryTreeNode();
		newNode.setData(o);
		newNode.setLeft(null);
		newNode.setRight(null);
		
		//insert to current node
		if(data == null){
			this.setData(o);
			this.setLeft(null);
			this.setRight(null);
		}else{
			//insert to left child
			if(compareTo(o) == -1){
				if(this.getLeft() == null){
					this.setLeft(newNode);
				}else{
					this.data = this.getLeft().data;
					this.setLeft(this.getLeft().getLeft());
					this.setRight(this.getRight().getRight());
					insert(o);
				}
			//insert to right child
			}else if(compareTo(o) == 1){
				if(this.getRight() == null){
					this.setRight(newNode);
				}else{
					this.data = this.getLeft().data;
					this.setLeft(this.getLeft().getLeft());
					this.setRight(this.getRight().getRight());
					insert(o);
				}
			//can't insert node which has same data.
			}else{
				
			}
		}
		return  newNode;
	}

	public int compareTo(Object o) {
		int compareFlag = 0;
		if(o instanceof Integer){
			if(Integer.valueOf(o.toString()) < Integer.valueOf(data.toString())){
				compareFlag = -1;
			}else if(Integer.valueOf(o.toString()) > Integer.valueOf(data.toString())){
				compareFlag = 1;
			}else{
				compareFlag = 0;
			}
		}
		return compareFlag;
	}
	
}
