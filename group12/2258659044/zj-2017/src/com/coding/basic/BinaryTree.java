package com.coding.basic;

public class BinaryTree {
	
	//根节点
	private BinaryTreeNode root;	
	    
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public <T extends Comparable<? super T>> BinaryTreeNode insert(T o){
		
		BinaryTreeNode treeNode = new BinaryTreeNode();
		treeNode.setData(o);
		if(root == null){
			root =  treeNode;
		}else{				
			BinaryTreeNode currentNode = root;
			BinaryTreeNode parent;
			while(true){
				parent = currentNode;
				if(((Comparable)currentNode.getData()).compareTo(o)>0){//向左放					
					currentNode = currentNode.getLeft();
					if(currentNode == null){
						parent.setLeft(treeNode);
						treeNode.setParent(parent);
						break;
					}					
				}else{//向右放
					currentNode = currentNode.getRight();
					if(currentNode == null){
						parent.setRight(treeNode);
						treeNode.setParent(parent);
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
	public List traversalBefore(BinaryTreeNode node){
		//所有数据集合
	    List datas = new ArrayList();
	    return traversal(node,datas);
	}
	private List traversal(BinaryTreeNode node,List datas){
		
		if(node !=null){
			datas.add(node.getData());
			traversal(node.getLeft(),datas);
			traversal(node.getRight(),datas);
		}
		return datas;
	}

	public BinaryTreeNode getRoot() {
		return root;
	}
		
}
