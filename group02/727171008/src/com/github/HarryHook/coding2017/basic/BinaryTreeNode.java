/*
 * Created by Harry 2017-2-23 10:50:39
 * 实现二叉树,并按二叉查找树插入节点
 * 
 */
package com.github.HarryHook.coding2017.basic;

public class BinaryTreeNode 
{
	private Integer data;      
	private BinaryTreeNode left;
	private BinaryTreeNode right;

	//中序遍历二叉树
	public void inOrder(BinaryTreeNode node)
	{
		if(node != null)
		{
			inOrder(node.left);
			System.out.print(" " + node.data);
			inOrder(node.right);
		}	
	}
	
	//获取给节点的值
	public Integer getData() 
	{
		return data;
	}
	//给一个节点赋值
	public void setData(Integer data)
	{
		this.data = data;
	}
	//获取左节点
	public BinaryTreeNode getLeft() 
	{
		return left;
	}
	//指定左节点
	public void setLeft(BinaryTreeNode left)
	{
		this.left = left;
	}
	
	//获取右节点
	public BinaryTreeNode getRight()
	{
		return right;
	}
	//指定右节点
	public void setRight(BinaryTreeNode right)
	{
		this.right = right;
	}	

	//在二叉树中插入一个节点，需要判断
	public BinaryTreeNode insert(Integer obj)
	{
		// 新增节点
        BinaryTreeNode newNode = new BinaryTreeNode();
        // 当前节点,保留根的值
        BinaryTreeNode current = this;    
        // 上个节点
        BinaryTreeNode parent  = null;
        // 如果根节点为空
        if  (current.data == null) 
        {	
        	newNode.setData(obj);
        	newNode.setLeft(null);
        	newNode.setRight(null);
            return newNode;
        }else
        {
        	 while (true) 
             {
                 parent = current;
                 if (obj < current.data) 
                 {
                     current = current.left;
                     if (current == null) 
                     {
                    	 newNode.setData(obj);
                     	 newNode.setLeft(null);
                     	 newNode.setRight(null);
                         parent.left = newNode;
                         return newNode;
                     }
                 } else 
                 {
                     current = current.right;
                     if (current == null) 
                     {
                    	 newNode.setData(obj);
                     	 newNode.setLeft(null);
                     	 newNode.setRight(null);
                         parent.right = newNode;
                         return newNode;
                     }
                 }
             } 
        }   
	}

	public static void main(String[] args)
	{
		BinaryTreeNode BTN = new BinaryTreeNode();
		
		BTN = BTN.insert(5);
		System.out.print(BTN.getData() + " ");
		System.out.print(BTN.insert(2).getData() + " ");
		System.out.print(BTN.insert(1).getData() + " ");
		System.out.print(BTN.insert(4).getData() + " ");
		System.out.print(BTN.insert(6).getData() + " ");
		System.out.print(BTN.insert(8).getData() + " ");
		System.out.println("");
		System.out.println("中序遍历二叉树： ");
		BTN.inOrder(BTN);	
	}
	
}
