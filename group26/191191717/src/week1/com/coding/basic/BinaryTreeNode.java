package week1.com.coding.basic;

public class BinaryTreeNode
{
    
    private TreeNode root;// 要有一个根节点
    
    public BinaryTreeNode()
    {
        this.root = new TreeNode();
    }
    
    public BinaryTreeNode(Object o)
    {
        this.root = new TreeNode(o);
    }
    
    public TreeNode insert(Object obj, TreeNode parent)
    {
        if (parent == null)
        {
            throw new RuntimeException("父节点为null，不能添加子节点");
        }
        TreeNode newNode = new TreeNode();
        if (parent.left == null)
        {
            parent.left = newNode;
        }
        else
        {
            parent.right = newNode;
        }
        return newNode;
    }
    
    public boolean isEmpty()
    {
        return root.data == null;
    }
    
    public TreeNode root()
    {
        if (isEmpty())
        {
            throw new RuntimeException("空树，无法返回根节点");
        }
        return root;
    }
    
    /**
     * 按照欣哥的架构不好写，所以加一个静态内部类作为节点
     * 
     * @author Administrator
     *
     */
    public static class TreeNode
    {
        private Object data;
        
        private TreeNode left;
        
        private TreeNode right;
        
        public TreeNode()
        {
        }
        
        public TreeNode(Object data)
        {
            this.data = data;
        }
        
    }
}
