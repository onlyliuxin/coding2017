package binarytree;

/**
 * Created by william on 2017/2/18.
 */
public class DLRSearchType implements SearchType<BinaryTree.Node> {

    @Override
    public void printByType(BinaryTree.Node root) {
        if (root != null) {
            System.out.print(root.getData()+" ");
            printByType(root.getLeft());
            printByType(root.getRight());
        }
    }
}
