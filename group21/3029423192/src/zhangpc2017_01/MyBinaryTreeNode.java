package zhangpc2017_01;

public class MyBinaryTreeNode {
	private Object data;
	
	//递归定义，左二叉树和右二叉树
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
		
		//创建对象调用compareTo方法,btn为父节点
		MyBinaryTreeNode btn = new MyBinaryTreeNode();
		btn.insertNode(btn,o);
		return btn;
	}
	
	private void insertNode(MyBinaryTreeNode parentNode,Object o){
		if(compareTo(parentNode, o)<=0){
			if(parentNode.right == null){
				parentNode.right.data = o;
				return;//跳出if
			}
			insertNode(parentNode.right,o);
		}else{
			if(parentNode.left == null){
				parentNode.left.data = o;
				return;//跳出if
			}
			insertNode(parentNode.left,o);
		}
	}
	
	public int compareTo(MyBinaryTreeNode parentNode,Object o) {
		//多态，强制类型转换 Object--Integer
		return (Integer)parentNode.data - (Integer)o;
	}
	
}
