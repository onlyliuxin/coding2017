package study.coding.basic;

/**
 * @Author shane
 * @Time 2017/2/26 19:30
 * @Email stevenchenguang@gmail.com
 * @Desc Own BinaryTreeNode
 */
public class BinaryTreeNode {

    private Object data;

    private BinaryTreeNode left;

    private BinaryTreeNode right;

    public BinaryTreeNode insert(Object o) {
        if (null == data) {
            data = o;
            return this;
        }
        if (bigger(data, o)) {
            if (null == left) {
                left = new BinaryTreeNode();
                left.data = o;
            } else {
                left.insert(o);
            }
        } else if (smaller(data, o)) {
            if (null == right) {
                right = new BinaryTreeNode();
                right.data = o;
            } else {
                right.insert(o);
            }
        } else {
            throw new RuntimeException("The value has exists");
        }
        return this;
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

    private boolean bigger(Object data1, Object data2) {
        return data1.toString().compareTo(data2.toString()) > 0;
    }

    private boolean smaller(Object data1, Object data2) {
        return data1.toString().compareTo(data2.toString()) < 0;
    }

    private ArrayList list = new ArrayList();

    /**
     * 对二叉树进行遍历 结果存储到list中
     */
    private void sort(BinaryTreeNode node) {

        list.add(node.data);
        if(null != node.left){
            sort(node.left);
        }
        if(null != node.right){
            sort(node.right);
        }
    }

    @Override
    public String toString() {
        sort(this);
        return list.toString();
    }
}
