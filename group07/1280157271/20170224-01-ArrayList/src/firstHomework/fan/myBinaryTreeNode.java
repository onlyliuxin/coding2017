package firstHomework.fan;
import java.util.Comparator; 

public class myBinaryTreeNode {
	
	private int data;
	private myBinaryTreeNode left;
	private myBinaryTreeNode right;
	
	//构造函数
	public myBinaryTreeNode(){
	}
	public myBinaryTreeNode(int value){
		this.data = value;
		this.left = null;
		this.right = null;
	}
	public myBinaryTreeNode(int value,myBinaryTreeNode left,myBinaryTreeNode right){
		this.data = value;
		this.left = left;
		this.right = right;
	}
	private myBinaryTreeNode root;//定义根节点
 
	//get/set方法
	public Object getData() {
		return data;
	}
	public void setData(int data) {
		this.data = data;
	}
	
	
	public myBinaryTreeNode getLeft() {
		return left;
	}
	public void setLeft(myBinaryTreeNode left) {
		this.left = left;
	}
	
	
	public myBinaryTreeNode getRight() {
		return right;
	}
	public void setRight(myBinaryTreeNode right) {
		this.right = right;
	}
	
	
	public void insert(int o){
		this.root = insert(o,this.root);
		 
	}
	public myBinaryTreeNode insert(int value,myBinaryTreeNode t){
		if(t == null){//根节点为空
			return new myBinaryTreeNode(value);
		}
		//插入值与当前节点比较，小于插入到左子树，大于插入到右子树
		if(value<t.data){
			t.left = insert(value,t.left);//t节点是最后的节点，他的left一定为空，此时递归开始返回
			
		}else if(value>t.data){
			t.right = insert(value,t.right);
		}
		return  t;
	}
	
}