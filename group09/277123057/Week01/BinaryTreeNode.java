package Week01;
//
/*
 *��û����
 * */
public class BinaryTreeNode {
	private Object data;
	private BinaryTreeNode left;
	private BinaryTreeNode right;
	
	public Object getData(){
		return data;
	}
	
	public void setData(Object data){
		this.data = data;
	}
	
	public BinaryTreeNode getLeft() {
		return left;
	}
	
	public void setLeft(BinaryTreeNode left){
		this.left = left;
	}
}
