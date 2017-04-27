package basic;

/**
 * Created by zhouliang on 2017-03-11.
 */
class BinaryTree {
    private TreeNode root;

    class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;
        public TreeNode(int val) {
            this.val = val;
        }
    }

    /**
     * 递归创建二叉树
     * @param node
     * @param data
     */
    public void buildTree(TreeNode node,int data){
        if(root == null){
            root = new TreeNode(data);
        }else{
            if(data < node.val){
                if(node.left == null){
                    node.left = new TreeNode(data);
                }else{
                    buildTree(node.left,data);
                }
            }else{
                if(node.right == null){
                    node.right = new TreeNode(data);
                }else{
                    buildTree(node.right,data);
                }
            }
        }
    }

    /**
     * 前序遍历
     * @param node
     */
    public void preOrder(TreeNode node){
        if(node != null){
            System.out.println(node.val);
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    /**
     * 中序遍历
     * @param node
     */
    public void inOrder(TreeNode node){
        if(node != null){
            inOrder(node.left);
            System.out.println(node.val);
            inOrder(node.right);
        }
    }

    /**
     * 后序遍历
     * @param node 一般是传入根节点
     */
    public void postOrder(TreeNode node){
        if(node != null){
            postOrder(node.left);
            postOrder(node.right);
            System.out.println(node.val);
        }
    }


    /**
     * 分层打印
     * @param root 树的根节点
     * @return 分层后的数组
     */
    public int[][] printTree(TreeNode root) {
        if(root == null){
            return null;
        }
        java.util.LinkedList<TreeNode> queue = new java.util.LinkedList<TreeNode>();
        TreeNode last = root;
        TreeNode nlast = null ;
        TreeNode currentNode = null;
        java.util.ArrayList<java.util.ArrayList<Integer>> lists = new java.util.ArrayList<java.util.ArrayList<Integer>>();
        java.util.ArrayList<Integer> list = new java.util.ArrayList<Integer>();
        queue.add(last);
        while(!queue.isEmpty()){
            currentNode = queue.poll();
            list.add(currentNode.val);

            if(currentNode.left!=null){
                queue.add(currentNode.left);
                nlast = currentNode.left;
            }
            if(currentNode.right!=null){
                queue.add(currentNode.right);
                nlast = currentNode.right;
            }
            if(currentNode == last){
                lists.add(list);
                last = nlast;
                list = new java.util.ArrayList<Integer>();
            }
        }

        int[][] result = new int[lists.size()][];
        for(int i = 0 ; i < lists.size() ; i++){
            result[i] = new int[lists.get(i).size()];
            for(int j = 0 ; j < lists.get(i).size() ; j++){
                result[i][j] = lists.get(i).get(j);
            }
        }
        return result;
    }
}
