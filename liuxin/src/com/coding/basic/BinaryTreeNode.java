package com.coding.basic;

public class BinaryTreeNode 
{
	public static TreeNode root; //根节点
	public BinaryTreeNode() 
	{
        this.root = null;
    }
    public TreeNode insert (int key) 
    {
		// 新增节点
		TreeNode newNode = new TreeNode(key);
		// 当前节点
		TreeNode current = root;
		// 上个节点
		TreeNode parent  = null;
		// 如果根节点为空
		if (current == null) 
		{
			root = newNode;
			return newNode;
		}
		while (true) 
		{
            parent = current;
            if (key < current.value) 
            {
                current = current.left;
                if (current == null) 
                {
                    parent.left = newNode;
                    return newNode;
                }
            } else 
            {
                current = current.right;
                if (current == null) 
                {
                    parent.right = newNode;
                    return newNode;
                }
            }
        }
    }
    
}
	 
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public BinaryTreeNode getLeft() {
		return left;
	}
	public void setLeft(BinaryTreeNode left) {
		this.left = left;
	}
	public BinaryTreeNode getRight() {
		return right;
	}
	public void setRight(BinaryTreeNode right) {
		this.right = right;
	}
	
	public BinaryTreeNode insert(Object o){
		return  null;
	}
	
}

class TreeNode     //树的节点
{
    int value;
    TreeNode left;
    TreeNode right;

	public TreeNode(int value) 
	{
        this.value = value;
        left  = null;
        right = null;
    }
}

public class BinaryTreeNodeTest {

    public static void main(String[] args) {
        BinaryTreeNode b = new BinaryTreeNode();
        b.insert(3);b.insert(8);b.insert(1);b.insert(4);b.insert(6);
        b.insert(2);b.insert(10);b.insert(9);b.insert(20);b.insert(25);

        // 打印二叉树
        b.toString(b.root);
        System.out.println();

        // 是否存在节点值10
        TreeNode node01 = b.search(10);
        System.out.println("是否存在节点值为10 => " + node01.value);
        // 是否存在节点值11
        TreeNode node02 = b.search(11);
        System.out.println("是否存在节点值为11 => " + node02);

        // 删除节点8
        TreeNode node03 = b.delete(8);
        System.out.println("删除节点8 => " + node03.value);
        b.toString(b.root);


    }
}
