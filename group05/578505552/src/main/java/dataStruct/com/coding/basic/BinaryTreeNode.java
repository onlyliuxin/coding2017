package dataStruct.com.coding.basic;

/**
 * Created by songbao.yang on 2017/2/21.
 *
 */
public class BinaryTreeNode {
	
	private Integer data;
	private BinaryTreeNode left;
	private BinaryTreeNode right;
	
	public Object getData() {
		return data;
	}
	public void setData(Integer data) {
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
	
	public BinaryTreeNode insert(Integer o){
		if (o == null){
		    throw new IllegalArgumentException("can not insert null");
        }

        BinaryTreeNode newNode = new BinaryTreeNode();
        newNode.data = o;
        newNode.left = null;
        newNode.right = null;

        BinaryTreeNode cursor = this;
        BinaryTreeNode pre = cursor;
        while (cursor != null){
            pre = cursor;
            if (o.compareTo(cursor.data) < 0){
                cursor = cursor.left;
            } else {
                cursor = cursor.right;
            }
        }

        if (o.compareTo(pre.data) < 0){
            pre.left = newNode;
        } else {
            pre.right = newNode;
        }
        return this;
    }
}
