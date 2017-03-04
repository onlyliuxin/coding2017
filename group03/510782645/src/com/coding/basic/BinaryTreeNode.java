package com.coding.basic;

public class BinaryTreeNode {

    static class Node {
        Integer data;
        Node parent;
        Node left;
        Node right;

        public Node(Integer data, Node parent, Node left, Node right) {
            this.data = data;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }

        public String toString(){
            return "[data=" + data + "]";
        }

        public boolean equals(Object obj){
            if(this == obj){
                return true;
            }

            if(obj.getClass() == Node.class){
                Node target = (Node) obj;
                return data.equals(target.data) && left == target.left
                        && right == target.right && parent == target.parent;
            }

            return false;
        }
    }
    private Node root;

    BinaryTreeNode() {
        root = null;
    }

    BinaryTreeNode(Integer data) {
        root = new Node(data, null, null, null);
    }

    /**
     * 暂且使用Intenger作为节点数据。
     * @param o
     */
	public void insert(Integer o) {
        if (root == null) {
            root = new Node(o, null, null, null);
        } else {
            Node current = root;
            Node parent = null;
            int cmp;

            //搜索合适的叶子节点，以该叶子节点为父节点添加新节点
            do {
                parent = current;
                cmp = o.compareTo(current.data);

                //如果新节点的值大于当前节点的值
                if (cmp > 0) {
                    //以当前节点的右子节点作为当前节点
                    current = current.right;
                } else {
                    current = current.left;
                }
            } while (current != null);

            //创建新节点
            Node newNode = new Node(o, parent, null, null);

            //如果新节点的值大于父节点的值
            if (cmp > 0) {
                parent.right = newNode;
            } else {
                parent.left = newNode;
            }
        }
    }
}
