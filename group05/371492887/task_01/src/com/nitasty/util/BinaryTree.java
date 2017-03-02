package com.nitasty.util;
/**
 * 
 * @author DYJ  ����д������ʱ���Ҿ���һ������һ���ڲ�ͣ��˵666666
 *
 * @param <E> ������ʵ����Comparable����
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
			throw new NullPointerException();//�׸�ʲô��������أ�TODO
		return (E) findMin(root).data; //ΪʲôҪ��ת�ͣ�
	}
	


	public <E> E findMax(){
		if(isEmpty())
			throw new NullPointerException();//�׸�ʲô��������أ�TODO
		return (E) findMax(root).data;//ΪʲôҪ��ת�ͣ�
	}

	public void insert(E x){
		root=insert(x,root);//ΪɶҪ��root�ӷ���ֵ����Ϊ�ⷽ���ǵݹ��
	}
	
	public void remove(E x){
		root=remove(x,root);//ΪɶҪ��root�ӷ���ֵ����Ϊ�ⷽ���ǵݹ��
	}
	
	//��ӡ����data
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
			return contains(x,t.left);//�ݹ�������������Ƚ�
		}else if(compareResult>0){
			return contains(x,t.right);//�ݹ�������������Ƚ�
		}else{
			return true;
		}
	}
	
	private BinaryNode<E> findMin(BinaryNode<E> t) {
		if(t==null)
			return null;
		else if(t.left==null)
			return t;
		return findMin(t.left);//�ݹ������С������
	}
	private BinaryNode<E> findMax(BinaryNode<E> t) {
		if(t==null)
			return null;
		else if(t.right==null){
			return t;
		}
		return findMax(t.right);//�ݹ�������������
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
			;//�ظ��ˣ���ʱɶ�����ɣ����ԼӸ��ռ������ظ���
		return t;//����ԭ��������
	}
	
	/**
	 * ���ү��̫����
	 * @param x
	 * @param t
	 * @return
	 */
	private BinaryNode<E> remove(E x, BinaryNode<E> t) {
		// TODO Auto-generated method stub
		if(t==null)
			return t; //x�����ڣ�ɶ������
		int compareResult=x.compareTo(t.data);
		
		if(compareResult<0)
			t.left=remove(x,t.left);
		else if(compareResult>0)
			t.right=remove(x,t.right);
		else if(t.left!=null && t.right!=null){ //����������
			t.data=findMin(t.right).data;
			t.right=remove(t.data,t.right);//������ݹ��ˡ����Ҳ�
		}
		else
			t=(t.left!=null)? t.left:t.right; //ֻ��һ�����ӣ������ӷ���ɾ���Ľڵ㴦��û�к�����Ϊ�գ�������Ҫ���е�������
		return t; //�ݹ�����
	}
	
	private int height(BinaryNode<E> t){
		if(t==null)
			return -1;
		else
			return 1+Math.max(height(t.left),height(t.right)); //����ݹ�̫��������ˣ�����
	}
	
	private void printTree(BinaryNode t){//�������
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

		//�½��ڵ������data,left,right
		BinaryNode(E data, BinaryNode<E> left, BinaryNode<E> right) {
			this.data = data;
			this.left = left;
			this.right = right;
		}
		
		//left��right����Ϊnull
		BinaryNode(E data){
			this(data, null, null);
		}
		
	}
}
