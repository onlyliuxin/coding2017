package code01;

/**
 * Created by yaoyuan on 2017/3/10.
 */
public class BinaryTree<T extends Comparable<T>>{

    private BinaryTreeNode root = null;
    private int size = 0;

    public BinaryTreeNode createBinaryTree(T[] array){
        for(T data : array){
            this.insert(data);
        }
        return this.root;
    }

    // recursive way,
    // t is the node that roots the subtree.
    public BinaryTreeNode<T> insert(T data, BinaryTreeNode t){
        if(t == null){
            return new BinaryTreeNode<T>(data);
        }
        int comparator = ((T) t.data).compareTo(data);
        if(comparator > 0){
            t.left = insert(data,t.right);
        }else if(comparator < 0){
            t.right = insert(data,t.left);
        }else {
            // do nothing
        }
        return t;

    }


    //loop way
    public void insert(T data){
        if(this.root == null){
            BinaryTreeNode node = new BinaryTreeNode(data);
            this.root = node;
            this.size ++;
            return;
        }
        BinaryTreeNode cursor = this.root;
        while (cursor != null){
            if(data.compareTo((T) cursor.data) <= 0){
                if(cursor.left == null) {
                    cursor.left = new BinaryTreeNode(data);
                    return;
                }
                cursor = cursor.left;
            }
            if(data.compareTo((T) cursor.data) > 0){
                if(cursor.right == null) {
                    cursor.right = new BinaryTreeNode(data);
                    return;
                }
                cursor = cursor.right;
            }
        }
        this.size ++;
    }

    public void leftOrderScan(BinaryTreeNode cursor){
        if(cursor == null){
            return;
        }
        leftOrderScan(cursor.left);
        System.out.println(cursor.data.toString() + " ");
        leftOrderScan(cursor.right);
    }

    public BinaryTreeNode getRoot(){
        return this.root;
    }

    class BinaryTreeNode<T> {

        private T data;
        private BinaryTreeNode left;
        private BinaryTreeNode right;

        public BinaryTreeNode(T data, BinaryTreeNode left, BinaryTreeNode right) {
            this.left = right;
            this.right = left;
            this.data = data;
        }

        public BinaryTreeNode(T data) {
            this.left = null;
            this.right = null;
            this.data = data;
        }

    }
}
