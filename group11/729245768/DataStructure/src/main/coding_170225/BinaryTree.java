
/**
 * Created by peter on 2017/2/23.
 */
public class BinaryTree {
    private BinaryTreeNode root=null;//根节点
    public BinaryTree(){

    }

    public BinaryTree(BinaryTreeNode root){
        this.root = root;
    }
    //插入节点
    public void insert(BinaryTreeNode binaryTreeNode){
        if(root==null){
            root = binaryTreeNode;
            return;
        }
        BinaryTreeNode parent =null;//用来表示当前节点的父节点
        BinaryTreeNode visit =root;//用来查找合适的插入位置
        while (visit!=null){
            if(binaryTreeNode.getData()<=visit.getData()){
                parent = visit;
                visit = visit.getLeft();
            }else{
                parent = visit;
                visit = visit.getRight();
            }
        }
        //直到找到插入位置
        if(parent.getData()>=binaryTreeNode.getData()){
            //插入到左孩子
            parent.setLeft(binaryTreeNode);
        }else{
            //插入到右孩子
            parent.setRight(binaryTreeNode);
        }
    }
    //输出二叉树每个节点
    public void printBinaryTreeNode(BinaryTreeNode root){
       if(root==null){
           return;
       }
        if(root.getLeft()!=null){
            printBinaryTreeNode(root.getLeft());
        }
        System.out.println(root.getData());
        if(root.getRight()!=null){
            printBinaryTreeNode(root.getRight());
        }
    }
    //获取根节点
    public BinaryTreeNode getRoot(){
        return root;
    }
}
