package week1.collection;
public class BinaryTreeNode {
	
	private Comparable data;
	private BinaryTreeNode left=null;
	private BinaryTreeNode right=null;
	
	public BinaryTreeNode(Comparable o){
		this.data=o;
	}
	/*
	 * 非递归实现
	 */
/*	public BinaryTreeNode insert(Comparable o){
		
		BinaryTreeNode currentNode=this;
		
		while(null != currentNode){
			Comparable data=(Comparable) currentNode.getData(); 
			int result=data.compareTo((Integer) o);
			
			if(result == 0){
				return currentNode;
			}else if(result > 0){
				if(null == currentNode.getLeft()){
					BinaryTreeNode insertNode=new BinaryTreeNode(o);
					currentNode.setLeft(insertNode);
					return insertNode;
				}
				currentNode=currentNode.left;
			}else{
				if(null ==currentNode.right){
					BinaryTreeNode insertNode=new BinaryTreeNode(o);
					currentNode.setRight(insertNode);
					return insertNode;
				}
				currentNode=currentNode.right;
			}
		}
		return new BinaryTreeNode(o);
	}*/
	
	/*
	 * 递归实现
	 */
	public BinaryTreeNode insert(Comparable o){
		
		Comparable data=(Comparable) this.getData();
		int result=data.compareTo(o);
		
		if(result == 0){
			return this;
		}else if(result > 0){
			if(this.getLeft()==null){
				BinaryTreeNode node=new BinaryTreeNode(o);
				this.setLeft(node);
			    return node;
			}
			return this.getLeft().insert(o);
		}else{
			if(this.getRight()==null){
				BinaryTreeNode node=new BinaryTreeNode(o);
				this.setRight(node);
				return node;
			}
			return this.getRight().insert(o);
		}
	}

	public Object getData() {
		return data;
	}

	public void setData(Comparable data) {
		this.data = data;
	}

	public BinaryTreeNode getLeft() {
		return left;
	}

	public void setLeft(BinaryTreeNode left) {
		this.left = left;
	}

	public BinaryTreeNode getRight() {
		return right;
	}

	public void setRight(BinaryTreeNode right) {
		this.right = right;
	}
	
}
