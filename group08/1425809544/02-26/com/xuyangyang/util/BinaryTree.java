/**
 * Created by 呢喃 on 2017/2/26.
 */
public class BinaryTree {


    private Node root;

    private static class Node {
        Node left;
        Node right;
        int data;

        Node(int newData){
            left = null;
            right = null;
            data = newData;
        }
    }

    public BinaryTree(){
        root = null;
    }


public void insert(int data){
     root = insert( root,data);
}

private Node insert(Node node,int data){

    if (node == null){
        node = new Node(data);
    }
    else {
        if (data<= node.data){
            node.left = insert(node.left,data);
        }else {
            node.right = insert(node.right,data);
        }
    }
    return node;
}

    public void bulidTree(int[] data){
        for (int i= 0;i<data.length;i++){
            insert(data[i]);
        }
    }

    public void printTree(){
        printTree(root);
        System.out.println();
    }
    private void printTree(Node node){
        if (node == null)
            return;
        printTree(node.left);
        System.out.println(node.data+"");
        printTree(node.right);
    }



}
