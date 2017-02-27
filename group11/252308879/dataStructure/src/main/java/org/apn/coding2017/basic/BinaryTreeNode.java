package org.apn.coding2017.basic;

/**
 * Created by QiPan on 2017/2/23.
 */
public class BinaryTreeNode<Key extends Comparable<Key>, Value> {

    private Node root;

    private class Node {
        private Key key;
        private Value value;
        private Node left, right;  //指向子树的链接
        private int N;             // 以该节点为根的子树节点总数
        public Node(Key key, Value value, int N) {
            this.key = key;
            this.value = value;
            this.N = N;
        }
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) return 0;
        else return x.N;
    }
    
    public Value get(Key key){
        return get(root, key);
    }

    private Value get(Node x, Key key) {
        // 如果根节点也是Null 那么返回null
        if (x == null){
            return null;
        }
        // 拿需要查询的key 与 根节点的 key 比较
        int cmp = key.compareTo(x.key);
        if (cmp < 0){ // 如果小于0 那么去左子树上查询,并且递归调用
            return get(x.left, key);
        }else if (cmp > 0){// 如果大于0 那么去右子树上查询,并且递归调用
            return get(x.right, key);
        }else {
            return x.value;
        }
    }

    public void push(Key key, Value value){
        // 查询key, 找到则更新它的值，否则就创建
        root = put(root, key, value);
    }

    private Node put(Node x, Key key, Value value) {
        // 如果key 存在于以 x 为根节点的子树中 则更新它的值
        // 否则将以key 和 value 为键值对的新节点插入到该子树中

        if (x == null){
            return new Node(key, value, 1);
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0 ){
            x.left  = put(x.left, key, value);
        }else if (cmp > 0){
            x.right = put(x.right, key, value);
        }else { // 存在更新值
            x.value = value;
        }
        // 重新统计节点总数
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

}
