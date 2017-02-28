package binarytree;

import java.util.LinkedList;

/**
 * Created by william on 2017/2/18.
 */
public class LFSearchType implements SearchType<BinaryTree.Node> {
    private LinkedList<BinaryTree.Node> queue = new LinkedList<>();

    @Override
    public void printByType(BinaryTree.Node root) {
        if (root == null)
            return;
        queue.offer(root);
        while (!queue.isEmpty()) {
            BinaryTree.Node curNode = queue.poll();
            System.out.print(curNode.getData() + " ");
            if (curNode.getLeft() != null)
                queue.offer(curNode.getLeft());
            if (curNode.getRight() != null)
                queue.offer(curNode.getRight());
        }

    }

}
