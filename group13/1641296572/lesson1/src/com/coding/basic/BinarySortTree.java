package com.coding.basic;

public class BinarySortTree
{
	 BinaryTreeNode root = null;
	 int size =0;
	
	public void insert(Object o)
	{
		BinaryTreeNode node = new BinaryTreeNode();
		node.setData(o);
		node.setLeft(null);
		node.setRight(null);
		
		if(null == root)
		{
			root = node;
			size++;
		}
		
		BinaryTreeNode p = root;
		BinaryTreeNode pre = p;
		while(p!= null)
		{
			if(objectCompare(o, p.getData())<0)
			{
				pre = p;
				p=p.getLeft();
			}
			else
			{
				pre = p;
				p=p.getRight();
			}
		}
		
		if(objectCompare(o, pre.getData())<0)
		{
			pre.setLeft(node);
		}
		else
		{
			pre.setRight(node);
		}
		
		size++;
	}

	private int objectCompare(Object o1, Object o2)
	{
//		return o.toString().compareTo(o2.toString());
		return (Integer) o1 - (Integer)o2;
	}
	
	public void print(BinaryTreeNode r)
	{
		if(r==null)
		{
			return;
		}
		System.out.print(r.getData() + "->");
		print(r.getLeft());
		print(r.getRight());
	}
	
}
