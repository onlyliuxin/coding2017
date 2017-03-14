/**
 * Created by wangtiegang on 2017/2/25.
 */
public class MyBinaryTreeNode {
    private Node root;

    private static class Node{
        Object data;
        Node right;
        Node left;

        public Node(Object obj,Node right,Node left){
            this.data = obj;
            this.right = right;
            this.left = left;
        }
    }

    public Object insert(Object o){
        if(root == null){
            root = new Node(o,null,null);
        }else{
            
        }
        return  null;
    }
}
