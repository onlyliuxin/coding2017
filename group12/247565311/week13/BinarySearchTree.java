package week13;


import java.util.ArrayList;
import java.util.List;

import structure.week1.Queue;
class BinaryTreeNode<T> implements Comparable{
	public T data;
	BinaryTreeNode left,right;
	public BinaryTreeNode(T args){
		data = args;
		left = null;
		right = null;
	}
	@Override
	public int compareTo(Object arg0) {
		return ((Integer)data).intValue()-Integer.parseInt(arg0.toString());
	}
}
public class BinarySearchTree<T extends Comparable> {
	BinaryTreeNode<T> root;
	public BinarySearchTree(BinaryTreeNode<T> root){
		this.root = root;
	}
	public BinaryTreeNode<T> getRoot(){
		return root;
	}
	public T findMin(){
		return findMin(root);
	}
	private T findMin(BinaryTreeNode node){
		if(node == null) return null;
		if(node.left==null) return (T)(node.data);
		return findMin(node.left);
	}
	public T findMax(){
		return findMax(root);
	}
	private T findMax(BinaryTreeNode node){
		if(node == null) return null;
		if(node.right==null) return (T)(node.data);
		return findMax(node.right);
	}
	public int height() {
	    return height(root);
	}
	private int height(BinaryTreeNode node){
		if(node == null) return 0;
		return 1 + Math.max(height(node.left), height(node.right));
	}
	public int size() {
		return size(root);
	}
	private int size(BinaryTreeNode node){
		if(node == null) return 0;
		return 1 + size(node.left)+size(node.right);
	}
	public void remove(T e){
		if(e.compareTo(root.data)==0){
			BinaryTreeNode newNode = null;
			if(root.left == null){
				if(root.right ==null) root =null;
				else{
					//if()
				}
			}
		}else if(e.compareTo(root.data)<0){
			
		}else{
			
		}
	}
	private BinaryTreeNode delMax(BinaryTreeNode node){
		if(node == null) return null;
		if(node.right == null) return null;
		if(node.right.right == null){
			BinaryTreeNode res = node.right;
			node.right = null;
			return res;
		}
		return delMax(node.right);
	}
	
    private BinaryTreeNode delMin(BinaryTreeNode node){
		if(node == null) return null;
		if(node.left == null) return null;
		if(node.left.left == null){
			BinaryTreeNode res = node.left;
			node.left = null;
			return res;
		}
		return delMin(node.left);
	}
	public List<T> levelVisit(){
		List<T> res = new ArrayList<T>();
		if(root == null) return res;
		Queue<BinaryTreeNode> temp = new Queue<BinaryTreeNode>();
		temp.push(root);
		while(temp.size()>0){
			BinaryTreeNode node = temp.pop();
			res.add((T) node.data);
			if(node.left!=null) temp.push(node.left);
			if(node.right!=null) temp.push(node.right);
		}
		return res;
	}
	public boolean isValid(){
		return isValid(root);
	}
	private boolean isValid(BinaryTreeNode node){
		if(node == null) return true;
		if((node.left==null ||((T)node.left.data).compareTo(node.data)<=0)
			&&(node.right==null ||((T)node.right.data).compareTo(node.data)>=0)) return true;
		return false;
	}
	public T getLowestCommonAncestor(T n1, T n2){
		BinaryTreeNode ancestor = dfsLowestCommonAncestor(n1,n2,root);
		return (T) ancestor.data;
	}
	private BinaryTreeNode dfsLowestCommonAncestor(T n1,T n2,BinaryTreeNode node){
		if(n1.compareTo(node.data) == 0 || n2.compareTo(node.data) == 0) return node;
		if(n1.compareTo(node.data)<0 && n2.compareTo(node.data) <0) return dfsLowestCommonAncestor(n1,n2,node.left);
		if(n1.compareTo(node.data)>0 && n2.compareTo(node.data) >0) return dfsLowestCommonAncestor(n1,n2,node.right);
	    return node;
	}
	/**
	 * 返回所有满足下列条件的节点的值：  n1 <= n <= n2 , n 为
	 * 该二叉查找树中的某一节点
	 * @param n1
	 * @param n2
	 * @return
	 */
	public List<T> getNodesBetween(T n1, T n2){
		List<T>res = new ArrayList<T>();
		dfsNodeBetween(n1,n2,res,root);
		return res;
	}
	private void dfsNodeBetween(T n1,T n2,List<T>list,BinaryTreeNode node){
		if(node == null) return ;
		if(n2.compareTo(node.data)>=0 && n1.compareTo( node.data) <=0) list.add((T) node.data);
		dfsNodeBetween(n1,n2,list,node.left);
		dfsNodeBetween(n1,n2,list,node.right);
	}
}
