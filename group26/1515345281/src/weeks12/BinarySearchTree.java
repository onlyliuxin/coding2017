package weeks12;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;

public class BinarySearchTree<T extends Comparable> {
	
	BinaryTreeNode<T> root;
	
	public BinarySearchTree(BinaryTreeNode<T> root){
		this.root = root;
	}
	public BinaryTreeNode<T> getRoot(){
		return root;
	}
	
	public T findMin(){
		return findMin(root).data;
	}
	
	public T findMax(){	
		return findMax(root).data;
	}
	
	public int height() {		
		return height(root);
	}
	
	public int size() {
		
		return size(root);
	}
	
	public void remove(T e){
		
		remove(e,root);	
	}	
	
	public List<T> levelVisit(){
		
		List<T> list=new ArrayList<T>();
		
		if(root == null){
			return list;
		}
		
		Queue<BinaryTreeNode<T>> queue=new LinkedList<>();
		
		queue.add(root);
		
		while(!queue.isEmpty()){
						
			BinaryTreeNode<T> currNode=queue.poll();
			list.add(currNode.data);
			
			if(currNode.left != null){
				queue.add(currNode.left);
			}
			
			if(currNode.right != null){
				queue.add(currNode.right);
			}
		}
		
	    return list;
	}
	
	public boolean isValid(){
		T prev=findMin();
		return isValid(root,prev);
	}
	
	public T getLowestCommonAncestor(T n1, T n2){
		
		return getLowestCommonAncestor(root,n1,n2).data;
        
	}

	/**
	 * 返回所有满足下列条件的节点的值：  n1 <= n <= n2 , n 为
	 * 该二叉查找树中的某一节点
	 * @param n1
	 * @param n2
	 * @return
	 */
	public List<T> getNodesBetween(T n1, T n2){
		BinaryTreeNode<T> commonAncestor=getLowestCommonAncestor(root,n1, n2);
		
		List<T> list=new ArrayList<>();
		list.add(commonAncestor.data);
		
		//添加n1与公共祖先的路径
		BinaryTreeNode<T> currNode1=commonAncestor;
		while(currNode1.data != n1){
			if(currNode1.data .compareTo(n1) > 0 ){
				currNode1=currNode1.left;
			}else{
				currNode1=currNode1.right;
			}
			list.add(currNode1.data);
		}
		
		//添加n2与公共祖先的路径
		BinaryTreeNode<T> currNode2=commonAncestor;
		while(currNode2.data != n2){
			if(currNode2.data .compareTo(n2) > 0 ){
				currNode2=currNode2.left;
			}else{
				currNode2=currNode2.right;
			}
			list.add(currNode2.data);
		}
		
		return list;
	}
	
	
	private BinaryTreeNode<T> getLowestCommonAncestor(BinaryTreeNode<T> root, T n1, T n2) {
		
		T min=(n1.compareTo(n2) < 0) ? n1:n2;
		T max=(n1.compareTo(n2) > 0) ? n1:n2;
		
		BinaryTreeNode<T>  result=root;
		
		while(result.data.compareTo(min) < 0 ||result.data.compareTo(max) > 0){
			if(result.data.compareTo(min) < 0){
				result=root.right;
			}
			
			if(result.data.compareTo(max) > 0){
				result=root.left;
			}
		}
		
		return result;
	}
	
	/*该函数判断二叉树root是否是一棵二叉搜索树，且其结点值都大于prev*/  
	private boolean isValid(BinaryTreeNode<T> root,T prev) {
		
		if(root == null){
			return true;
		}

		if(isValid(root.left,prev)){// 如果左子树是二叉搜索树，且结点值都大于prev  
			if(root.data .compareTo(prev) >0){		
				prev=root.data;
				return isValid(root.right,prev);//如果右子树是二叉搜索树，且结点值都大于prev
			}
		}
		
		return false;
	
	}
	
	private BinaryTreeNode<T> remove(T e, BinaryTreeNode<T> root) {
		if(root == null){
			return root;
		}
		
		int compareResult=e.compareTo(root.data);
		
		if(compareResult > 0){
			root.right=remove(e,root.right);
		}else if(compareResult < 0){
			root.left=remove(e,root.left);
		}else{
			if(root.left != null && root.right != null){
				root.data=findMin(root.right).data;
				root.right=remove(root.data,root.right);				
			}else{
				root=  (root.left != null ) ? root.left : root.right;
			}
		}
		return root;
	}
	
	private BinaryTreeNode<T> findMin(BinaryTreeNode<T> root) {
		
		/**非递归实现**/	 
		if(root == null){
			return null;
		}
		
		BinaryTreeNode<T> currNode=root;
		
		if(currNode.left == null){
			return currNode;
		}
		
		while(currNode.left != null){
			currNode=currNode.left;
		}
		
		return currNode;	
		
		/**递归实现**/
		
		/*if(root == null){
			return null;
		}else if(root.left == null){
			return root;
		}else{
			return findMin(root.left);
		}*/
		
	}
	
	private BinaryTreeNode<T> findMax(BinaryTreeNode<T> root) {
		
		BinaryTreeNode<T> currNode=root;
		
		if(currNode.right == null){
			return currNode;
		}
		
		while(currNode.right != null){
			currNode=currNode.right;
		}
		
		return currNode;
	}
	
	private int height(BinaryTreeNode<T> root) {
		
		/**递归实现**/
		if(root == null){
			return 0;
		}else{
			int leftChildHeight=height(root.left);
			int rightChildHeight=height(root.right);
			
			if(leftChildHeight > rightChildHeight){
				return leftChildHeight+1;
			}else{
				return rightChildHeight+1;
			}
		}
	
	}
	
	private int size(BinaryTreeNode<T> root) {

		if(root == null){
			return 0;
		}
		
		return size(root.left) + 1 + size(root.right);
	}	
}
