package binarytree;

/**
 * Created by william on 2017/2/18.
 */
public class LDRSearchType implements SearchType<BinaryTree.Node> {
    @Override
    public void printByType(BinaryTree.Node root) {
        if (root != null) {
            printByType(root.getLeft());
            System.out.print(root.getData() + " ");
            printByType(root.getRight());
        }
    }
}
