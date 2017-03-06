package com.coding.basic;

public class BinaryTreeNode {
	
	private Object data;
	private BinaryTreeNode left;
	private BinaryTreeNode right;
	
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
	
	public BinaryTreeNode insert(Object o){
			if(data==null){		//判断根节点是否为空
				data=o;
				left=null;
				right=null;
			}
			else{
				int insertData=(int) o;
				int nowData=(int) data;
				if(left==null || right==null){				//1.left==null right==null,2.left!=null,right==null,3.left==null.right!=null
					if(nowData>=insertData && left==null){	//判断插入数小于上一个节点,left==null
						left=new BinaryTreeNode();
						left.data=o;
						left.left=null;
						left.right=null;
					}
					else if(insertData>nowData && right==null){  //判断插入数大于上一个节点,==null
						right=new BinaryTreeNode();
						right.data=o;
						right.left=null;
						right.right=null;
					}
					else{
						BinaryTreeNode treeNode=null;			//记录比较节点
						if(nowData>=insertData ){				//如果当前及节点数据大于所插入数据
							treeNode=left;						//比较节点为左节点
						}
						else{
							treeNode=right;						//否则为右节点
						}
						BinaryTreeNode tempNode=null;			//临时节点，用于记录当比较节点的左节点或右节点为空时记录比较节点
						while(treeNode!=null){
							nowData=(int) treeNode.data;		//更改当前数值
							if(insertData<=nowData){			//如果当前及节点数据大于所插入数据
								tempNode=treeNode.left;			//临时节点为左节点
							}
							else{
								tempNode=treeNode.right;		//否则为右节点
							}
							if(tempNode==null){
								tempNode=treeNode;				//记录比较节点
								if(insertData<=nowData){		//如果当前及节点数据大于所插入数据
									treeNode=treeNode.left;		//比较节点为左节点
								}
								else{
									treeNode=treeNode.right;	//否则为右节点
								}
							}
							else{
								treeNode=tempNode;				//临时节点不为空时，比较节点赋值为临时节点
							}
						}
						if(treeNode==null){						//当比较节点为空时
							treeNode=new BinaryTreeNode();		//新建插入节点
							treeNode.data=o;
							treeNode.left=null;
							treeNode.right=null;
							int upData=(int) tempNode.data;
							if(insertData<=upData){				//当上一个节点的数据大于插入节点的数据时
								tempNode.left=treeNode;			//上一个节点的左节点赋予插入节点
							}
							else{
								tempNode.right=treeNode;
							}
						}
					}
				}
				else{	//left!=null&&right!=null
					BinaryTreeNode treeNode=null;	//跟上面判断一样
					if(nowData>=insertData ){
						treeNode=left;
					}
					else{
						treeNode=right;
					}
					BinaryTreeNode tempNode=null;
					while(treeNode!=null){
						nowData=(int) treeNode.data;
						if(insertData<=nowData){
							tempNode=treeNode.left;
						}
						else{
							tempNode=treeNode.right;
						}
						if(tempNode==null){
							tempNode=treeNode;
							if(insertData<=nowData){
								treeNode=treeNode.left;
							}
							else{
								treeNode=treeNode.right;
							}
						}
						else{
							treeNode=tempNode;
						}
					}
					if(treeNode==null){
						treeNode=new BinaryTreeNode();
						treeNode.data=o;
						treeNode.left=null;
						treeNode.right=null;
						int upData=(int) tempNode.data;
						if(insertData<=upData){
							tempNode.left=treeNode;
						}
						else{
							tempNode.right=treeNode;
						}
					}
				}
			}
		return  this;
	}
	
	
	
}
