package firstHomework.fan;
import java.util.Comparator; 

public class myBinaryTreeNode {
	
	private int data;
	private myBinaryTreeNode left;
	private myBinaryTreeNode right;
	
	//���캯��
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
	private myBinaryTreeNode root;//������ڵ�
 
	//get/set����
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
		if(t == null){//���ڵ�Ϊ��
			return new myBinaryTreeNode(value);
		}
		//����ֵ�뵱ǰ�ڵ�Ƚϣ�С�ڲ��뵽�����������ڲ��뵽������
		if(value<t.data){
			t.left = insert(value,t.left);//t�ڵ������Ľڵ㣬����leftһ��Ϊ�գ���ʱ�ݹ鿪ʼ����
			
		}else if(value>t.data){
			t.right = insert(value,t.right);
		}
		return  t;
	}
	
}