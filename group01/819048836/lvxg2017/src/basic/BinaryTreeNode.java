package basic;

//������
public class BinaryTreeNode { 
	private int data;//节点值
    private BinaryTreeNode left; //左子节点
    private BinaryTreeNode right;//右子节点
    public  BinaryTreeNode(int data){
    	this.data =data;
    	this.left = null;
    	this.right = null;
    }
    public  void setDate(int data){
    	this.data =data;
    }
    public int getData(){
    	return data;
    }
    public  BinaryTreeNode getLeft(){
    	return left;
    }
    public BinaryTreeNode getRight(){
    	return right;
    }
    public void setLeft(BinaryTreeNode left){
    	this.left = left;
    }
    public void setRight(BinaryTreeNode right){
    	this.right =right;
    }
    
    
    
}
