package basic;

//������
public class BinaryTreeNode { 
	private int data;// �ڵ�ֵ
    private BinaryTreeNode left; //���ӽڵ�
    private BinaryTreeNode right;//���ӽڵ�
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
