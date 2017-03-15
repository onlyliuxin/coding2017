package week1.com.coding.basic;

public class BinaryTreeNode
{
    
    private TreeNode root;// Ҫ��һ�����ڵ�
    
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
            throw new RuntimeException("���ڵ�Ϊnull����������ӽڵ�");
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
            throw new RuntimeException("�������޷����ظ��ڵ�");
        }
        return root;
    }
    
    /**
     * ��������ļܹ�����д�����Լ�һ����̬�ڲ�����Ϊ�ڵ�
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
