package tree;

public class BinarySearchTree<T extends Comparable> {
	
	BinaryTreeNode<T> root;
	
	public BinarySearchTree(BinaryTreeNode<T> root){
		this.root = root;
	}
	
	public BinaryTreeNode<T> getRoot(){
		return root;
	}
	
	public T findMin(){
		return findMinNode(root).data;
	}
	
	public BinaryTreeNode<T> findMinNode(BinaryTreeNode<T> node){//找到最小的结点
		if(root == null){
			return null;
		}
		
		BinaryTreeNode<T> point = node;
		while(point.left != null){
			point = point.left;
		}
		return point;
	}
	
	public T findMax(){
		return findMaxNode(root).data;
	}
	
	public BinaryTreeNode<T> findMaxNode(BinaryTreeNode<T> node){//找到最大结点
		if(root == null){
			return null;
		}
		
		BinaryTreeNode<T> point = node;
		while(point.right != null){
			point = point.right;
		}
		return point;
	}
	
	
	public int height() {
		if(root == null){
			return -1;
		}
		if(root.left == null && root.right == null){
			return 1;
		}
		BinarySearchTree<T> leftTree = new BinarySearchTree<T>(root.left);
		BinarySearchTree<T> rightTree = new BinarySearchTree<T>(root.right);
		int height = max(leftTree.height(), rightTree.height()) + 1;
	    return height;
	}
	
	// 方法二 
	public int height2(){
		return height2(root);
	}
	
	private int height2(BinaryTreeNode<T> node) {
		if(node == null){
			return 0;
		}else{
			int leftHeight = height2(node.left);
			int rightHeight = height2(node.right);
			return max(leftHeight, rightHeight) + 1;
		}
	}

	private int max(int leftHeight, int rightHeight) {
		return leftHeight > rightHeight? leftHeight : rightHeight;
	}
	
	// 树中结点个数
	public int size() {
		if(root == null){
			return -1;
		}
		if(root.left == null && root.right == null){//这种情况为叶子结点，返回1
			return 1;
		}
		if(root.left == null || root.right == null){//这种情况为有一个子节点，返回2
			return 2;
		}
		BinarySearchTree<T> leftTree = new BinarySearchTree<T>(root.left);
		BinarySearchTree<T> rightTree = new BinarySearchTree<T>(root.right);
		int size = leftTree.size() + rightTree.size() + 1;
		return size;
	}
	
	public int size2(){
		return size2(root);
	}
	
	private int size2(BinaryTreeNode<T> node) {
		if(node == null){
			return 0;
		}
		int size = size2(node.left) + size2(node.right) + 1;
		return size;
	}

	public void remove(T e){
		remove(e, root);
	}

	private void remove(T e, BinaryTreeNode<T> node) {
		if(node == null){
			return ;
		}
		
		int value = e.compareTo(node.data);
		
		if(value == 0){
			if(node.left != null && node.right != null){//左右结点均不为null
				BinaryTreeNode<T> temp = findMinNode(node.right);
				node.data  = temp.data;
				temp = null;
			}else if(node.left == null){// 左结点为null
				node.data = node.right.data;
				node.right = null;
			}else if(node.right == null){// 右结点为null
				node.data = node.left.data;
				node.left = null;
			}else{// 左右结点均为null
				node = null;
			}
		}else if(value > 0){
			remove(e, node.right);
		}else if(value < 0){
			remove(e, node.left);
		}
		
	}

}