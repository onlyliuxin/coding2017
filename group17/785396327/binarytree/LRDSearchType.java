package binarytree;

/**
 * Created by william on 2017/2/18.
 */
public class LRDSearchType implements SearchType<BinaryTree.Node> {
    @Override
    public void printByType(BinaryTree.Node root) {
        if (root != null) {
            printByType(root.getLeft());
            printByType(root.getRight());
            System.out.print(root.getData() + " ");
        }
    }
}
