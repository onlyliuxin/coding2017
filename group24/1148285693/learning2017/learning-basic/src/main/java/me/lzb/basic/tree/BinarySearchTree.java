package me.lzb.basic.tree;

/**
 * @author LZB
 */
public class BinarySearchTree<T extends Comparable<T>> {
    BinaryTreeNode<T> root;

    public BinarySearchTree(BinaryTreeNode<T> root) {
        this.root = root;
    }

    public BinaryTreeNode<T> getRoot() {
        return root;
    }

    public T findMin() {
        if (this.root == null) {
            throw new RuntimeException("empty tree");
        }
        return getMinNode(this.root).getData();
    }

    public T findMax() {
        if (this.root == null) {
            throw new RuntimeException("empty tree");
        }
        return getMaxNode(this.root).getData();
    }

    private BinaryTreeNode<T> getMinNode(BinaryTreeNode<T> node) {
        if (node == null) {
            return node;
        }

        if (node.getLeft() == null) {
            return node;
        }
        return getMinNode(node.getLeft());
    }

    private BinaryTreeNode<T> getMaxNode(BinaryTreeNode<T> node) {
        if (node == null) {
            return node;
        }

        if (node.getRight() == null) {
            return node;
        }

        return getMaxNode(node.getRight());
    }


    public int height() {
        return getHeight(root, 0);
    }

    private int getHeight(BinaryTreeNode<T> node, int h) {
        if (node == null) {
            return h;
        }

        int lh = getHeight(node.getLeft(), h + 1);
        int rh = getHeight(node.getRight(), h + 1);

        return lh > rh ? lh : rh;
    }


    public int size() {
        return getSize(root, 1);
    }

    private int getSize(BinaryTreeNode<T> node, int s) {
        if (node == null) {
            return s - 1;
        }

        s = getSize(node.getLeft(), s + 1);
        s = getSize(node.getRight(), s + 1);

        return s;
    }

    public void remove(T t) {
        BinaryTreeNode<T> node = getFatherNode(root, t);
        removeNode(node, t);
    }


    private void removeNode(BinaryTreeNode<T> father, T t) {
        if (father == null) {
            return;
        }

        //移除左子节点
        if (father.getLeft() != null && father.getLeft().getData().equals(t)) {
            BinaryTreeNode<T> remove = father.getLeft();
            BinaryTreeNode<T> l = remove.getLeft();
            BinaryTreeNode<T> r = remove.getRight();

            //被删除节点没有子节点，直接删除
            if (l == null && r == null) {
                father.setLeft(null);
                return;
            }

            //被删除节点有一个子节点，用非空子节点顶替被删除节点
            if (l == null) {
                father.setLeft(r);
                return;
            }

            if (r == null) {
                father.setLeft(l);
                return;
            }


            //被删除节点有两个子节点
            //右子树的最小节点，顶替被删除位置
            BinaryTreeNode<T> rm = getMinNode(r);
            BinaryTreeNode<T> rmf = getFatherNode(r, rm.getData());
            rmf.setLeft(null);
            father.setLeft(rm);
            rm.setLeft(l);
            rm.setRight(r);


            return;

        }


        //移除右子节点
        if (father.getRight() != null && father.getRight().getData().equals(t)) {
            BinaryTreeNode<T> remove = father.getRight();
            BinaryTreeNode<T> l = remove.getLeft();
            BinaryTreeNode<T> r = remove.getRight();

            if (l == null && r == null) {
                father.setRight(null);
                return;
            }

            if (l == null) {
                father.setRight(r);
                return;
            }


            if (r == null) {
                father.setRight(l);
                return;
            }


            BinaryTreeNode<T> rm = getMinNode(r);
            BinaryTreeNode<T> rmf = getFatherNode(r, rm.getData());
            rmf.setLeft(null);
            father.setRight(rm);
            rm.setLeft(l);
            rm.setRight(r);
            return;
        }


    }


    private BinaryTreeNode<T> getFatherNode(BinaryTreeNode<T> node, T t) {
        if (node == null) {
            return node;
        }
        if (node.getLeft() != null && node.getLeft().getData().equals(t)) {
            return node;
        }

        if (node.getRight() != null && node.getRight().getData().equals(t)) {
            return node;
        }
        if (t.compareTo(node.getData()) > 0) {
            return getFatherNode(node.getRight(), t);
        } else {
            return getFatherNode(node.getLeft(), t);
        }
    }

    private BinaryTreeNode<T> getNode(BinaryTreeNode<T> node, T t) {
        if (node == null) {
            return node;
        }


        if (node.getData().equals(t)) {
            return node;
        }

        if (t.compareTo(node.getData()) > 0) {
            return getNode(node.getRight(), t);
        } else {
            return getNode(node.getLeft(), t);
        }

    }


}
