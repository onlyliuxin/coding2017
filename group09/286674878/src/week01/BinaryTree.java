package week01;



public class BinaryTree {
	class BinaryTreeNode {
		private int data;
		private BinaryTreeNode left = null;
		private BinaryTreeNode right = null;
		public BinaryTreeNode(int data){
			this.data = data; 
		}
	}
	//前序遍历
	private BinaryTreeNode root;
	public void preorder(BinaryTreeNode bt){
		if(bt!=null)
		{
			getData(bt);
			preorder(bt.left);
			preorder(bt.right);
		}
	}
	public int getData(BinaryTreeNode bt){
		preorder(bt);
		return bt.data;
	}
	public void setData(int data) {
		BinaryTreeNode newadd = new BinaryTree.BinaryTreeNode(data);
		Insert(newadd,data);
		
	}
	public BinaryTreeNode getLeft(BinaryTreeNode bt) {
		preorder(bt);
		return bt.left;
	}
	//public void setLeft(BinaryTreeNode left) {
	//	this.left = left;
	//}
	public BinaryTreeNode getRight(BinaryTreeNode bt) {
		preorder(bt);
		return bt.right;
	}
	
	//public void setRight(BinaryTreeNode right) {
	//	this.right = right;
	//}
	
	public boolean Insert(BinaryTreeNode bt,int data){
		if(bt == null){
			bt.data = data;
			return true;
		}
		else{
			if(data==bt.data)
				{
			    System.out.println("The data has already exist");
			    return false;}
			else if(data < bt.data){
				return Insert(bt.left,data);
			}else return Insert(bt.right,data);
		} 
		
	}
	public BinaryTreeNode Search(BinaryTreeNode root, int data){
		if(root==null){
			//throw new IllegalArgumentException("The Tree is Empty "+root.data);
			System.out.println("The Tree is an empty tree.");
			return root;
		}else{
			if(root.data==data)
				{return root;}
			else if(data>root.data)
				{return Search(root.right,data);}
			else
				{return Search(root.left,data);}
		}
	}

}

