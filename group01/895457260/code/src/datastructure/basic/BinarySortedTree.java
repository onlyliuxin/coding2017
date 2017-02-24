package datastructure.basic;

/**
 * Created by Haochen on 2017/2/24.
 * TODO:
 */
public class BinarySortedTree<T extends Comparable> {

    private BinaryTreeNode root = null;

    public void traverse(Visitor visitor) {
        traverse(root, visitor);
    }

    private void traverse(BinaryTreeNode node, Visitor visitor) {
        if (node == null) {
            return;
        }
        traverse(node.getLeft(), visitor);
        visitor.visit(node);
        traverse(node.getRight(), visitor);
    }

    public interface Visitor {
        void visit(BinaryTreeNode node);
    }

    //不递归的写法
    public void add(T o) {
        //根节点空，直接加入
        if (root == null) {
            root = new BinaryTreeNode();
            root.setData(o);
        } else {
            BinaryTreeNode target = root;
            //从根结点不断向下比较target和o，o小则往左，o大则往右，相等不加入
            while (true) {
                int compare = o.compareTo(target.getData());
                if (compare == 0) {//相等不加入
                    return;
                } else if (compare < 0) {//o小往左
                    if (target.getLeft() == null) {//左空则加入
                        target.setLeft(new BinaryTreeNode());
                        target.getLeft().setData(o);
                        return;
                    } else {//不空继续比较
                        target = target.getLeft();
                    }
                } else {//o大往右
                    if (target.getRight() == null) {
                        target.setRight(new BinaryTreeNode());
                        target.getRight().setData(o);
                        return;
                    } else {
                        target = target.getRight();
                    }
                }
            }
        }
    }
}
