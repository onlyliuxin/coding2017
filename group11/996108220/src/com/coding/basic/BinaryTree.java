package com.coding.basic;

import com.sun.corba.se.impl.orbutil.graph.Node;

public class BinaryTree <T extends Comparable>{
	
	private BinaryTreeNode root=null;
	private int size=0;
	/**
	 * 插入节点，保持二叉树的性质
	 * @param o
	 */
	public void insert(T o){
		if (size==0) {
			root=new BinaryTreeNode<Comparable>(o);
			}
		else{
			insert(o,root);	
			}
		size++;
		}
	
	private void insert(T o,  BinaryTreeNode<?> ptr) {
		if ((ptr.right==null&&(ptr.data.compareTo(o)<=0))){
			ptr.right=new BinaryTreeNode<Comparable>(o);	
		}
		else if (ptr.left==null&&(ptr.data.compareTo(o)>0)) {
			ptr.left=new BinaryTreeNode<Comparable>(o);

		} 
		else if (ptr.left!=null&&(ptr.data.compareTo(o)>0)) {
			insert(o, ptr.left);
		}
		else {
			insert(o, ptr.right);
		}		
	}
	private static  class BinaryTreeNode <T extends Comparable>{
		private T data;
		private BinaryTreeNode left;
		private BinaryTreeNode right;	
		private BinaryTreeNode(T o) {
			this.data=o;
			this.left=null;
			this.right=null;
		}
		private BinaryTreeNode() {
			
		}
	}
	  /**
     * 前序遍历
     */
    public void preOrder(BinaryTreeNode Node)
    {
        if (Node != null)
        {
            System.out.print(Node.data + " ");
            preOrder(Node.left);
            preOrder(Node.right);
        }
    }

    /**
     * 中序遍历
     */
    public void midOrder(BinaryTreeNode Node)
    {
        if (Node != null)
        {
            midOrder(Node.left);
            System.out.print(Node.data + " ");
            midOrder(Node.right);
        }
    }

    /**
     * 后序遍历
     */
    public void posOrder(BinaryTreeNode Node)
    {
        if (Node != null)
        {
            posOrder(Node.left);
            posOrder(Node.right);
            System.out.print(Node.data + " ");
        }
    }
    /**
     * @param key查找元素
     * @param node
     * @return 返回date的node引用
     */
    public BinaryTreeNode searchNode(T key,BinaryTreeNode node) {
    	if (node!=null) {
    		if (node.data.compareTo(key)==0) {
				return node;
			}
    		else if (node.data.compareTo(key)>0) {
    			return searchNode(key,node.left);
			}
    		else {
    			return searchNode(key,node.right);
			}	
		}
    	else{
    		return null;
    	}					
	}

	public static void main(String[] args) {
		BinaryTree tree=new BinaryTree();
		tree.insert(5);
		tree.insert(3);
		tree.insert(1);
		tree.insert(6);
		tree.insert(5);
		tree.insert(2);
		tree.preOrder(tree.root);
		System.out.println();
		tree.posOrder(tree.root);
		System.out.println();
		tree.midOrder(tree.root);
		System.out.println(tree.searchNode(1, tree.root).data);
		
		
	}
	
}
