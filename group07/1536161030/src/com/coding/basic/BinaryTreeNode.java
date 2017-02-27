package com.coding.basic;

public class BinaryTreeNode <Object extends Comparable<Object>> {

    private Object data;
    
    private BinaryTreeNode left;
    
    private BinaryTreeNode right;
    
    public BinaryTreeNode(Object data){
		this.data = data;
		this.left=null;
		this.right =null;
	}
    
    public BinaryTreeNode root;
    
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
    /*
     * 二叉树 b 插入对象  o 父节点 pnode
     * 若b为空树，插入节点作为根
     * o 等于根节点  直接返回
     * o 小于根节点  pnode = 左子树
     * else pnode = 右子树
     * 
     */
    public BinaryTreeNode insert(Object o) {
    	BinaryTreeNode current = root;
    	
        if(current == null){
        	return new BinaryTreeNode(o);
        }
    	if (o.compareTo((Object) current.data)<0){
    		if(current.left !=null){
    			current = current.left;
    		}else{
    			current.left = new BinaryTreeNode(o);
    			return current;
    		}
    	}else if(o.compareTo((Object) current.data)==0){
    		return current;
    	}else{
    		if(current.right !=null){
    			current = current.right;
    		}else{
    			current.right = new BinaryTreeNode(o);
    			return current;
    		}
    	}
    	return current;
    }
}
