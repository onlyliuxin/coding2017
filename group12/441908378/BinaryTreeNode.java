
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
		BinaryTreeNode node;
		do{
			node=compare(o);
		}while(node==null);
		node.data=o;
		return  node;
	}
	
	public BinaryTreeNode compare(Object o){
		int a=(Integer)data;
		int b=(Integer)o;
		if(a>b){
			return left;
		}else{
			return right;
		}
	}
	

}
