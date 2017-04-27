package basic.dataStructure;

/**
 * Created by macvi on 2017/4/4.
 */
public class BinaryTreeNode {
    private int data;
    private BinaryTreeNode left;
    private BinaryTreeNode right;

    private BinaryTreeNode(){}

    public BinaryTreeNode(int data){
        this.data = data;
        this.left = null;
        this.right = null;
    }

    public void setData(int data){
        BinaryTreeNode node = new BinaryTreeNode(data);
        if(compareTo(data)){
            if(this.left == null){
                this.left = node;
            }else{
                this.left.setData(data);
            }
        }else{
            if(this.right == null){
                this.right = node;
            }else{
                this.right.setData(data);
            }
        }
    }

    public int getData(){
        return data;
    }

    private boolean compareTo(int d) {
        System.out.println("data=" + this.data + ", d=" + d);
        return this.data > d;
    }

    private StringBuffer dataStr = new StringBuffer();
    private int index = 0;
//    public String toString(BinaryTreeNode node) {
//        while (node.left != null || node.right != null){
//            dataStr.append(index + "层，数据=").append(node.data).append("|");
//            if(node.left != null){
//                dataStr.append(node.left.data)
//            }
//            index ++;
//        }
//
//        return dataStr.toString();
//    }
}
