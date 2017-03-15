package com.coding.basic;

/**
 * 二叉树数据结构
 * BinaryTreeNode
 * @author greenhills
 * 2017年2月25日 下午9:51:05
 */
public class BinaryTreeNode implements Comparable<Object>{
	
	private int height=0; //当前树高度
	private Object data;        //当前节点数据
	private BinaryTreeNode left;  //小于当前节点数据data的节点
	private BinaryTreeNode right; //大于当前节点数据data的节点
	
	public BinaryTreeNode() {
	}

	public BinaryTreeNode(Object data) {
		this.data = data;
	}

	public BinaryTreeNode insert(Object o){
		BinaryTreeNode newNode=new BinaryTreeNode(o);
		BinaryTreeNode that = findNode(o);
		int result=that.compareTo(o);
		
		if(result<0){//节点数据小于插入数据，进右树
			that.setRight(newNode);
		}else if(result>0){ //当前节点数据大于插入数据，进左树
			that.setLeft(newNode);
		}else{
			return null;
		}
		newNode.height++; //树高度加1
		return  newNode;
	}
	
	private BinaryTreeNode findNode(Object data){
		int result=this.compareTo(data);
		BinaryTreeNode that = new BinaryTreeNode(); //空节点
		if(result<0){ //当前节点数据小于插入数据，进右树
			if(this.right==null){
				that = this;
			}else{
				that = findNode(this.getRight());
			}
		}else if(result>0){ //当前节点数据大于插入数据，进左树
			if(this.left==null){
				that = this;
			}else{
				that = findNode(this.getLeft());
			}
		}else{
			System.out.println("无效数据");
		}
		return that;
	}
	
	public int getTreeMaxHeight(){
		int h=0;
		//TODO
		
		return h;
	}
	
	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

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

	@Override
	public int compareTo(Object o) {
		if(this.data==null || o==null)  return 0;
		return Double.valueOf(this.data.toString()).compareTo(Double.valueOf(o.toString()));
	}
}
