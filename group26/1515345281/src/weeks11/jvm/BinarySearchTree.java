package weeks11.jvm;
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
		
		/**非递归层次遍历实现**/
		/*if(root == null){
			return 0;
		}
		
		Queue<BinaryTreeNode<T>> queue=new LinkedList<>();
		
		queue.add(root);
		int height=0;
		int levelSize=queue.size();
		
		while(!queue.isEmpty()){
						
			BinaryTreeNode<T> currNode=queue.poll();
			levelSize--;
			
			if(currNode.left != null){
				queue.add(currNode.left);
			}
			
			if(currNode.right != null){
				queue.add(currNode.right);
			}
			
			if(levelSize == 0){
				height++;
				levelSize=queue.size();
			}	
			
		}
		
	    return height;*/
	}
	
	private int size(BinaryTreeNode<T> root) {

		if(root == null){
			return 0;
		}
		
		return size(root.left) + 1 + size(root.right);
	}
	
}
