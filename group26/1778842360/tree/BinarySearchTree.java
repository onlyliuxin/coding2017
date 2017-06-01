package tree;

import java.util.ArrayList;
import java.util.List;

import queue.Queue;
/**
 * 二叉查找树的相关操作
 * @author 阿杰
 *
 * @param <T>
 */
public class BinarySearchTree<T extends Comparable> {

	BinaryTreeNode<T> root;
	public BinarySearchTree(BinaryTreeNode<T> root){
		this.root = root;
	}
	public BinaryTreeNode<T> getRoot(){
		
		return root;
	}
	public T findMin(){
		if(root==null)
		{
			return null;
		}
		return findMin(root).data;
	}
	private BinaryTreeNode<T> findMin(BinaryTreeNode<T> t) {
		if(t==null)
		{
			return t;
		}else if(t.left==null){
			return t;
		}else{
			return findMin(t.left);
		}
	}
	public T findMax(){
		if(root==null)
		{
			return null;
		}
		return findMax(root).data;
	}
	private BinaryTreeNode<T> findMax(BinaryTreeNode<T> t) {
		
		while(t.right!=null)
		{
			t=t.right;
		}
		return t;
		
	}
	public int height() {
		return height(root);
	}
	private int height(BinaryTreeNode<T> t) {
		if(t==null)
		{
			return 0;
		}else{
			int leftChildHeight=height(t.left);
			int rightChildHeight=height(t.right);
			if(leftChildHeight>rightChildHeight)
			{
				return leftChildHeight++;
			}else{
				return rightChildHeight++;
			}
		}
	}
	public int size() {
		return size(root);
	}
	private int size(BinaryTreeNode<T> t) {
		if(t==null)
		{
			return 0;
		}
		return size(t.left)+1+size(t.right);
	}
	//那么二叉树操作同样也是一样，我们根据”中序遍历“找到要删除结点的后一个结点，然后顶上去就行了，原理跟"数组”一样一样的。
	public void remove(T e){
		remove(e,root);
	}
	@SuppressWarnings("unchecked")
	private BinaryTreeNode<T> remove(T e, BinaryTreeNode<T> t) {
		if(t==null)
		{
			return null;
		}
		int compareResult=e.compareTo(t.data);
		if(compareResult<0)
		{
			t.left=remove(e,t.left);
		}else if(compareResult>0)
		{
			t.right=remove(e,t.right);
		}else{
			if(t.left!=null && t.right!=null)
			{
				t.data=findMin(t.right).data;
				t.right=remove(t.data,t.right);
			}else{
				t=(t.left!=null)?t.left:t.right;
			}
		}
		return t;
	}
	public List<T> levelVisit(){
		List<T> result=new ArrayList<>();
		levelVisit(root,result);
		return result;
	}
	//对二叉树按层次遍历
	//即按照层次访问，通常用队列来做。访问根，访问子女，再访问子女的子女（越往后的层次越低）（两个子女的级别相同）
	private void levelVisit(BinaryTreeNode<T> node, List<T> result) {
		
		if(node==null)
		{
			return;
		}
		Queue<BinaryTreeNode<T>> queue=new Queue<>();
		queue.enQueue(node);
		while(!queue.isEmpty())
		{
			result.add(node.getData());
			BinaryTreeNode<T> tmpNode=queue.deQueue();
			if(tmpNode.left!=null)
			{
				result.add(tmpNode.left.data);
			}
			if(tmpNode.right!=null)
			{
				result.add(tmpNode.left.data);
			}
		}
		
	}
	public boolean isValid(){
		return isValid(root);
		//return false;
	}
	@SuppressWarnings("unchecked")
	private boolean isValid(BinaryTreeNode<T> node) {
		int compareResult=root.left.data.compareTo(root.data);
		if(compareResult<0)
		{
			isValid(node.left);
		}else{
			return false;
		}
		compareResult=root.right.data.compareTo(root.data);
		if(compareResult>0)
		{
			isValid(root.right);
		}
		else{
			return false;
		}
		return false;
	}
	public T getLowestCommonAncestor(T n1, T n2){
		if(n1==root.data||n2==root.data)
		{
			return root.data;
		}
		return null;
        
	}
	/**
	 * 返回所有满足下列条件的节点的值：  n1 <= n <= n2 , n 为
	 * 该二叉查找树中的某一节点
	 * @param n1
	 * @param n2
	 * @return
	 */
	public List<T> getNodesBetween(T n1, T n2){
		
		List<T> result=new ArrayList<>();
		getNodesBetween(root,n1,n2,result);
		return result;
}
	private void getNodesBetween(BinaryTreeNode<T> node, T n1, T n2, List<T> result) {
		// TODO Auto-generated method stub
		if(node==null)
		{
			return;
		}
		
	}
}
