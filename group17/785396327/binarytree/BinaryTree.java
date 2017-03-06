package binarytree;

/**
 * Created by william on 2017/2/16.
 */
public class BinaryTree {
    private Node root;

    class Node {
        private Node left;
        private Node right;
        private Comparable data;

        public Node(Node left, Node right, Comparable data) {
            this.left = left;
            this.right = right;
            this.data = data;
        }

        private void add(Comparable data) {
            if (this.data.compareTo(data) >= 0)
                if (this.left == null)
                    this.left = new Node(null, null, data);
                else
                    left.add(data);
            else if (this.data.compareTo(data) < 0)
                if (this.right == null)
                    this.right = new Node(null, null, data);
                else
                    this.right.add(data);
        }

        public Comparable getData() {
            return this.data;
        }

        public Node getLeft() {
            return this.left;
        }

        public Node getRight() {
            return this.right;
        }
    }

    public void add(Comparable data) {
        if (this.root == null)
            root = new Node(null, null, data);
        else this.root.add(data);
    }

    public void printByType(SearchType<Node> type) {
        type.printByType(this.root);
    }
}
