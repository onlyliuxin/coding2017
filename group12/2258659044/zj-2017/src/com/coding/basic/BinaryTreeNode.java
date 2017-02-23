package com.coding.basic;
public class BinaryTreeNode {
	
	private Object data;
	//根节点
	private BinaryTreeNode root;
	//父节点
	private BinaryTreeNode parent;
	private BinaryTreeNode left;
	private BinaryTreeNode right;
	//所有数据集合
	private final List datas = new ArrayList(); 
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
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
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public <T extends Comparable<? super T>> BinaryTreeNode insert(T o){
		
		BinaryTreeNode treeNode = new BinaryTreeNode();
		treeNode.data = o;
		if(root == null){
			root =  treeNode;
			root.root = treeNode;
		}else{				
			BinaryTreeNode currentNode = root;	
			while(true){
				parent = currentNode;
				if(((Comparable)currentNode.data).compareTo(o)>0){//向左放					
					currentNode = currentNode.getLeft();
					if(currentNode == null){
						parent.left = treeNode;
						treeNode.parent = parent;
						treeNode.root = root;
						break;
					}					
				}else{//向右放
					currentNode = currentNode.getRight();
					if(currentNode == null){
						parent.right = treeNode;
						treeNode.parent = parent;
						treeNode.root = root;
						break;
					}
				}
			}			
		}
		return treeNode;
	}

	/**
	 * 先序遍历
	 * @param node
	 * @return
	 */
	public List traversal(BinaryTreeNode node){
		
		if(node !=null){
			datas.add(node.data);
			traversal(node.left);
			traversal(node.right);
		}
		return datas;
	}

}
