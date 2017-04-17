package me.lzb.basic;

/**
 * 左边比父节点小，右边比父节点大
 * Created by LZB on 2017/3/11.
 */
public class BinaryTreeNode {

	private int data;
	private BinaryTreeNode left;
	private BinaryTreeNode right;

	public BinaryTreeNode(int data){
	    this.data = data;
    }

	public int getData() {
		return data;
	}


    //这层满了就下一层继续add,直到找到空位
    public void add(int d){
        BinaryTreeNode b = new BinaryTreeNode(d);
        if(compareTo(b)){
            //比父节点小，左边
            if(this.left == null){
                this.left = b;
            }else {
                this.left.add(d);
            }

        }else {//相等不考虑
            //比父节点大，右边
            if(this.right == null){
                this.right = b;
            }else {
                this.right.add(d);
            }

        }
    }


	public boolean compareTo(BinaryTreeNode node){
		if(this.data > node.getData()){
			return true;
		}
		return false;
	}


}
