package main.week01.data_structure;

import com.sun.org.apache.regexp.internal.recompile;

public class BinaryTreeNode<T extends Comparable> {
	
	private T data;
	private BinaryTreeNode left;
	private BinaryTreeNode right;
	private int size;
	
	public BinaryTreeNode(){}
	
	public BinaryTreeNode(T data)
	{
		this.data=data;
		this.left=null;
		this.right=null;
	}
	
	public T getData() {
		return data;
	}
	
	public void setData(T data) {
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
	
	/**
	 * data不允许重复
	 * @param data
	 * @return
	 */
	public BinaryTreeNode insert(T data){
		int compareResult = this.data.compareTo(data);
		if(compareResult == 0){
			return this;
		}
		if(compareResult > 0){
			if(this.left == null){
				this.left = new BinaryTreeNode(data);
				return this.left;
			}else{
				return this.left.insert(data);
			}
		}else{
			if(this.right == null){
				this.right = new BinaryTreeNode(data);
				return this.right;
			}else{
				return this.right.insert(data);
			}
		}
	}

	/**
	 * 允许节点为空
	 * @param data
	 * @return
	 */
    @SuppressWarnings("unchecked")
	public BinaryTreeNode search(T data){
		if(this.data == null){
			return null;
		}
		int compareResult = this.data.compareTo(data);
        if (compareResult > 0) {
            if (this.left == null) {
                return null;
            } else {
                return this.left.search(data);
            }
        } else if (compareResult < 0) {
            if (this.right == null) {
                return null;
            } else {
                return this.right.search(data);
            }
        } else {
            return this;
        }
		
	}
	
	private BinaryTreeNode findMin() {
        if (this.data == null) {
            return null;
        }
        if (this.left == null) {
            return this;
        }
        return this.left.findMin();
    }
	
	private BinaryTreeNode findMax() {
		if (this.data == null) {
            return null;
        }
        if (this.right == null) {
            return this;
        }
        return this.right.findMin();
    }
	
}
