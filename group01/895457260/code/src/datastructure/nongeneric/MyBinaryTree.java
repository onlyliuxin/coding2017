package datastructure.nongeneric;

/**
 * Created by Haochen on 2017/2/15.
 * TODO:
 */
public class MyBinaryTree<T extends Comparable> {
    private static class Node {
        Object data;
        Node left;
        Node right;
    }

    private Node root = null;

    //不递归的写法
    public void add(T o) {
        //根节点空，直接加入
        if (root == null) {
            root = new Node();
            root.data = o;
        } else {
            Node target = root;
            //从根结点不断向下比较target和o，o小则往左，o大则往右，相等不加入
            while (true) {
                int compare = o.compareTo(target.data);
                if (compare == 0) {//相等不加入
                    return;
                } else if (compare < 0) {//o小往左
                    if (target.left == null) {//左空则加入
                        target.left = new Node();
                        target.left.data = o;
                        return;
                    } else {//不空继续比较
                        target = target.left;
                    }
                } else {//o大往右
                    if (target.right == null) {
                        target.right = new Node();
                        target.right.data = o;
                        return;
                    } else {
                        target = target.right;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        MyBinaryTree<Integer> tree = new MyBinaryTree<>();
        tree.add(5);
        tree.add(2);
        tree.add(1);
        tree.add(7);
        tree.add(6);
        tree.add(4);
        tree.add(8);
        tree.add(8);
    }
}
