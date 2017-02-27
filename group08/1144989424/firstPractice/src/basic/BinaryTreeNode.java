package basic;

/**
 * 二叉树的数据结构
 * @author Wayss
 * 2017-02-25
 */

public class BinaryTreeNode {
    
    private Object data;
    private BinaryTreeNode left;
    private BinaryTreeNode right;
    
    public BinaryTreeNode(){
        
    }
    
    public BinaryTreeNode(Integer val){
        data = val;
        left = null;
        right = null;
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
    
    public BinaryTreeNode insert(Object o){
        return  null;
    }
    
}
