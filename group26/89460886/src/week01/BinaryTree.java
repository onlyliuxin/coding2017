package list;

/**
 * @author jiaxun
 */
public class BinaryTree {

    private Node root;

    public BinaryTree() {
        root = null;
    }

    public void insert(int value) {
        if (root == null) {
            root = new Node(value);
        } else {
            Node newNode = new Node(value);
            Node curr = root;
            Node prev = null;
            boolean right = true;

            while (curr != null) {
                prev = curr;
                if (curr.getData() > value) {
                    curr = curr.getRight();
                    right = true;
                } else if (curr.getData() < value) {
                    curr = curr.getLeft();
                    right = false;
                } else {
                    prev = null;
                    break;
                }
            }
            if (prev != null) {
                if (right) {
                    prev.setRight(newNode);
                } else {
                    prev.setLeft(newNode);
                }
            }
        }
    }

    public boolean isEmpty() {
        return root == null;
    }

    private static class Node {
        int data;
        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
        }

        public int getData() {
            return data;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }
    }

}
