package basic.dataStructure.binaryTree;

/**
 * Created by macvi on 2017/4/4.
 */
public class BinaryTreeNode<T> {
//    private T data;
//    private BinaryTreeNode left;
//    private BinaryTreeNode right;
//    private BinaryTreeNode before;
//
//    private BinaryTreeNode(){}
//
//    public BinaryTreeNode(T data){
//        this.data = data;
//        this.left = null;
//        this.right = null;
//    }
//
//    public void setData(int data){
//        BinaryTreeNode node = new BinaryTreeNode(data);
//        if(compareTo(data)){
//            if(this.left == null){
//                this.left = node;
//            }else{
//                this.left.setData(data);
//            }
//        }else{
//            if(this.right == null){
//                this.right = node;
//            }else{
//                this.right.setData(data);
//            }
//        }
//    }
//
//    public BinaryTreeNode<T> getLeft() {
//        return left;
//    }
//
//    public void setLeft(BinaryTreeNode<T> left) {
//        this.left = left;
//    }
//
//    public BinaryTreeNode<T> getRight() {
//        return right;
//    }
//
//    public void setRight(BinaryTreeNode<T> right) {
//        this.right = right;
//    }
//
//    public T getData(){
//        return data;
//    }
//
//    private boolean compareTo(int d) {
//        System.out.println("data=" + this.data + ", d=" + d);
//        return (Integer)this.data > d;
//    }
//
//    private StringBuffer dataStr = new StringBuffer();
//    private int index = 0;
////    public String toString(BinaryTreeNode node) {
////        while (node.left != null || node.right != null){
////            dataStr.append(index + "层，数据=").append(node.data).append("|");
////            if(node.left != null){
////                dataStr.append(node.left.data)
////            }
////            index ++;
////        }
////
////        return dataStr.toString();
////    }
    private T data;
    private BinaryTreeNode<T> left;
    private BinaryTreeNode<T> right;

    public BinaryTreeNode(T data){
        this.data=data;
    }
    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }
    public BinaryTreeNode<T> getLeft() {
        return left;
    }
    public void setLeft(BinaryTreeNode<T> left) {
        this.left = left;
    }
    public BinaryTreeNode<T> getRight() {
        return right;
    }
    public void setRight(BinaryTreeNode<T> right) {
        this.right = right;
    }

    public BinaryTreeNode<T> insert(Object o){
        return  null;
    }
}
