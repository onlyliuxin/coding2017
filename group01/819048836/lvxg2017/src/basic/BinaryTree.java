package basic;

public class BinaryTree {
	private BinaryTreeNode root;//根节点
	
	//插入操作
	public void insert(int value){
		BinaryTreeNode newNode = new BinaryTreeNode(value);
		if(root==null){
			root = newNode;
			root.setLeft(null);
			root.setRight(null);
		}else{
			 BinaryTreeNode currentNode = root;
			 BinaryTreeNode parentNode;
			 while(true)
			 {   
				 parentNode = currentNode;
				 //往右放
				 if(newNode.getData()>currentNode.getData()){
					 currentNode = currentNode.getRight();
					 if(currentNode ==null){
						 parentNode.setRight(newNode); 
					     return;
					 }
				 }else if(newNode.getData()<=currentNode.getData()){
					 currentNode = currentNode.getLeft();
					 if(currentNode ==null){
						 parentNode.setLeft(newNode); 
					     return;
					 }
				 }
				  
			 }
			
		}
		
		
	}

}
