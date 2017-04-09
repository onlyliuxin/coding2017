package week1_0306;

import java.util.Comparator;

import week1_0306.BinaryTree.BinaryTreeNode;

public class BinaryTree
{
	private BinaryTreeNode root;
	
	private BinaryTreeNode pointer;
	
	public BinaryTreeNode getRoot() {
		return root;
	}

	public void setRoot(BinaryTreeNode root) {
		this.root = root;
	}
	
	public BinaryTreeNode getPointer() {
		return pointer;
	}

	public void setPointer(BinaryTreeNode pointer) {
		this.pointer = pointer;
	}

	public BinaryTree()
	{
		root=new BinaryTreeNode();
		pointer=root;
	}
	
	public BinaryTreeNode insert(Object o,Comparator c)
	{
		
		pointer= root.insert(o, c);
		return pointer;
	}
	
	public void printTree()
	{
		root.printNode();
	}
	
	public class BinaryTreeNode 
	{
		
		private Object data;
		private BinaryTreeNode left;
		private BinaryTreeNode right;

		public Object getData() {
			return data;
		}

		public void setData(Object data) {
			this.data = data;
		}

		public BinaryTreeNode getLeft() {
			pointer=left;
			return left;
		}

		public void setLeft(BinaryTreeNode left) {
			this.left = left;
		}

		public BinaryTreeNode getRight() {
			pointer=right;
			return right;
		}

		public void setRight(BinaryTreeNode right) {
			this.right = right;
		}



		public BinaryTreeNode insert(Object o, Comparator c) //建有规律的树+插入
		{		
			if(this.data == null)
			{
				this.data = o;
				return this;
			}
			
			int i = c.compare(this.data,o);
			
			if( i > 0 )
			{	
				if(this.left == null)
				{
					this.left=new BinaryTreeNode();
					this.left.data=o;
					return this.left;
				}
				else
					return this.left.insert(o, c);
			}
			else if(i < 0)
			{
				if(this.right == null)
				{
					this.right=new BinaryTreeNode();
					this.right.data = o;
					return this.right;
				}
					
				else
					return this.right.insert(o, c);
			}
			else
			{
				return this;
			}
				
		}
		
		public void printNode()
		{
			ScoreRecord s=(ScoreRecord)(this.getData());
			System.out.println(s.getName()+"  "+s.getId());
			if(this.getLeft()!=null)
				this.getLeft().printNode();
			if(this.getRight()!=null)
				this.getRight().printNode();
			else return;
		}

	}

}
