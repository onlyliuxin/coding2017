package com.nitasty.util;
/**
 * 
 * @author DYJ  照着写这个类的时候我就像一条咸鱼一样在不停的说666666
 *
 * @param <E> 必须是实现了Comparable的类
 */
public class BinaryTree<E extends Comparable<? super E>> {
	
	private BinaryNode<E> root;
	
	public BinaryTree(){
		this.root=null;
	}
	
	public BinaryTree(BinaryNode root) {
		this.root = root;
	}

	public void makeEmpty(){
		root=null;
	}
	
	public boolean isEmpty(){
		return root==null;
	}
	
	public int getHeight(){
		return height(root);
	}

	public boolean contains(E x){
		return contains(x,root);
	}


	public <E> E findMin(){
		if(isEmpty())
			throw new NullPointerException();//抛个什么错误合适呢？TODO
		return (E) findMin(root).data; //为什么要求转型？
	}
	


	public <E> E findMax(){
		if(isEmpty())
			throw new NullPointerException();//抛个什么错误合适呢？TODO
		return (E) findMax(root).data;//为什么要求转型？
	}

	public void insert(E x){
		root=insert(x,root);//为啥要用root接返回值，因为这方法是递归的
	}
	
	public void remove(E x){
		root=remove(x,root);//为啥要用root接返回值，因为这方法是递归的
	}
	
	//打印树的data
	public void printTree(){
		if(isEmpty())
			System.out.println("Empty tree");
		else
			printTree(root);
	}
	
	public void printTreeStructure(){
		if(isEmpty())
			System.out.println("Empty tree");
		else
			printTreeStructure(root,0);
	}
	
	private void printTreeStructure(BinaryNode<E> t,int i) {
		StringBuffer buff=new StringBuffer();
		if(t!=null){
			for(int j=0;j<i;j++)
				buff.append("=");
			System.out.println(buff.append(t.data));
			printTreeStructure(t.left,i+1);
			printTreeStructure(t.right,i+1);
		}
	}

	private boolean contains(E x, BinaryNode<E> t) {
		if(t==null)
			return false;
		int compareResult=x.compareTo(t.data);
		
		if(compareResult<0){
			return contains(x,t.left);//递归继续往左子树比较
		}else if(compareResult>0){
			return contains(x,t.right);//递归继续往右子树比较
		}else{
			return true;
		}
	}
	
	private BinaryNode<E> findMin(BinaryNode<E> t) {
		if(t==null)
			return null;
		else if(t.left==null)
			return t;
		return findMin(t.left);//递归查找最小左子树
	}
	private BinaryNode<E> findMax(BinaryNode<E> t) {
		if(t==null)
			return null;
		else if(t.right==null){
			return t;
		}
		return findMax(t.right);//递归查找最大右子树
	}
	
	
	private BinaryNode<E> insert(E x, BinaryNode<E> t) {
		
		if(t==null)
			return new BinaryNode<E>(x);
		int compareResult=x.compareTo(t.data);
		if(compareResult<0)
			t.left=insert(x,t.left);
		else if(compareResult>0)
			t.right=insert(x,t.right);
		else
			;//重复了，暂时啥都不干，可以加个空间来存重复数
		return t;//返回原来的子树
	}
	
	/**
	 * 你大爷的太绕了
	 * @param x
	 * @param t
	 * @return
	 */
	private BinaryNode<E> remove(E x, BinaryNode<E> t) {
		// TODO Auto-generated method stub
		if(t==null)
			return t; //x不存在，啥都不干
		int compareResult=x.compareTo(t.data);
		
		if(compareResult<0)
			t.left=remove(x,t.left);
		else if(compareResult>0)
			t.right=remove(x,t.right);
		else if(t.left!=null && t.right!=null){ //有两个孩子
			t.data=findMin(t.right).data;
			t.right=remove(t.data,t.right);//又尼玛递归了··我擦
		}
		else
			t=(t.left!=null)? t.left:t.right; //只有一个孩子，将孩子放在删除的节点处（没有孩子则为空），最终要运行到这行来
		return t; //递归走起
	}
	
	private int height(BinaryNode<E> t){
		if(t==null)
			return -1;
		else
			return 1+Math.max(height(t.left),height(t.right)); //这个递归太尼玛酷炫了！！！
	}
	
	private void printTree(BinaryNode t){//中序遍历
		if(t!=null){
			printTree(t.left);
			System.out.println(t.data);
			printTree(t.right);
		}
	}
	
	
	
	public static class BinaryNode<E>{
		private E data;
		private BinaryNode<E> left;
		private BinaryNode<E> right;

		//新建节点必须有data,left,right
		BinaryNode(E data, BinaryNode<E> left, BinaryNode<E> right) {
			this.data = data;
			this.left = left;
			this.right = right;
		}
		
		//left和right可以为null
		BinaryNode(E data){
			this(data, null, null);
		}
		
	}
}
