package basic;

/**
 * 自己实现的BinaryTreeNode
 * Created by zhouliang on 2017-03-10.
 */
class BinaryTreeNode {
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

    public BinaryTreeNode insert(Object o){
        if(data==null && left==null && right==null){
            this.setData(o);
            this.setLeft(null);
            this.setRight(null);
            return this;
        }else{
            BinaryTreeNode temp =  this;
            BinaryTreeNode node = new BinaryTreeNode();
            while(true){
                if((Integer)o > (Integer)temp.getData()){
                    if(temp.getRight() == null){
                        node.setData(o);
                        node.setLeft(null);
                        node.setRight(null);

                        temp.setRight(node);
                        return this;
                    }else{
                        temp = temp.getRight();
                    }
                }else{
                    if(temp.getLeft() == null){
                        node.setData(o);
                        node.setLeft(null);
                        node.setRight(null);

                        temp.setLeft(node);
                        return this;
                    }else{
                        temp = temp.getLeft();
                    }
                }
            }
        }
    }
}
