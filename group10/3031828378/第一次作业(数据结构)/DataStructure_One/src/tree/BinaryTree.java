package tree;

public class BinaryTree {
	private TreeNode parent;
	
	public BinaryTree(){
		parent = new TreeNode(null,null,null);
	}
	
	public void insert(Object o ){
		TreeNode  node = new TreeNode(null,o,null);
		if(parent.item==null){
			parent= node;	
			return;
		}
		insertNode(parent,node);		
	}			
	
	private void insertNode(TreeNode parentNode, TreeNode newNode) {
		if(parentNode.compareTo(newNode)<= 0){
			if(parentNode.right==null){
				parentNode.right = newNode;
			}else{
				insertNode(parentNode.right,newNode);
			}
				
		}else{
			if(parentNode.left==null){
				parentNode.left=newNode;
			}else{
				insertNode(parentNode.left, newNode);
			}
		}
	}
	
	public void printTree(){
		printNode(this.parent);
	}




	private void printNode(TreeNode node) {
		if (node == null) {
			System.out.println("node :" + node.item);
			printNode(node.left);
			printNode(node.right);
		}

	}




	class TreeNode implements Comparable<TreeNode>{
		Object item;
		TreeNode left;
		TreeNode right;
		TreeNode(TreeNode left,Object item,TreeNode right){
			this.item=item;
			this.left =left;
			this.right=right;
		}
		
		@Override
		public int compareTo(TreeNode o) {
			Integer parentItem = (Integer) this.item;
			Integer oItem = (Integer) o.item;
			
			return parentItem.compareTo(oItem);
		}
	}

}
