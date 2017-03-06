
/**
 * Created by peter on 2017/2/23.
 */
public class BinaryTreeNode{
    private int data;
    private BinaryTreeNode left=null;
    private BinaryTreeNode right=null;
    public BinaryTreeNode(){

    }

    public  BinaryTreeNode(int data){
        this.data=data;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
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
