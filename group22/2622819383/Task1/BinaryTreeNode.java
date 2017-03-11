//代码参考自《数据结构与算法分析》
public class BinaryTreeNode {
	
    private Object data;    
    private BinaryTreeNode left;
    private BinaryTreeNode right;    

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
    
    private BinaryTreeNode parrent;
    private BinaryTreeNode hot;  //表示search(Object o)方法返回的命中节点的父亲
    
    public BinaryTreeNode(Object o, BinaryTreeNode p) {
        data = o;
        parrent = p;
    }
    //在以v为根的二叉树中查找关键码o，返回命中的节点（真实存在得或者虚拟存在的）
    public static BinaryTreeNode search(BinaryTreeNode v, Object o, BinaryTreeNode hot) {
        int vData = (int)v.getData();
        int searched = (int)o;
        if (v == null || vData == searched) return v; 
        
        hot = v;
        return search(searched < vData ? v.getLeft() : v.getRight(), o, hot);
    }    

    public BinaryTreeNode insert(Object o){
        BinaryTreeNode node = search(this, o, this.parrent);
        if (node != null) return node;
        
        node = new BinaryTreeNode(o, hot);
        if ((int)o < (int)hot.getData()) hot.setLeft(node);
        else hot.setRight(node);
        return node;
    }

}
