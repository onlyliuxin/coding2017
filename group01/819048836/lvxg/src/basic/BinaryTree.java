package basic;

public class BinaryTree {
	private BinaryTreeNode root;//���ڵ�
	
	//�������
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
				 //���ҷ�
				 if(newNode.getData()>currentNode.getData()){
					 currentNode = currentNode.getRight();
					 if(currentNode ==null){
						 parentNode.setRight(newNode); 
					     return;
					 }
				 }
				 
			 }
			
		}
		
		
	}

}
