package tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import com.sun.jmx.remote.internal.ArrayQueue;

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
	
	public List<T> levelVisit(){
		Queue<BinaryTreeNode<T>> queue = new ArrayDeque<BinaryTreeNode<T>>();
		List<T> result = new ArrayList<T>();
		queue.add(root);
		while(!queue.isEmpty()){
			BinaryTreeNode<T> node = queue.remove();
			result.add(node.getData());
			if(node.getLeft() != null){
				queue.add(node.getLeft());
			}
			if(node.getRight() != null){
				queue.add(node.getRight());
			}
		}
		
		return result;
	}
	
	public boolean isValid(){
		boolean b = isValid(root);
		int value1 = findMaxNode(root.left).data.compareTo(root.data);
		int value2 = findMinNode(root.right).data.compareTo(root.data);
		boolean b2 = value1 < 0 && value2 > 0;
		return b & b2;
	}
	
	public boolean isValid(BinaryTreeNode<T> node){
		int value1 = 1;
		int value2 = -1;
		if(node.left != null){
			value1 = node.data.compareTo(node.left.data);
		}
		if(node.right != null){
			value2 = node.data.compareTo(node.right.data);
		}
		
		if(value1 > 0 && value2 < 0){
			boolean b1 = true;
			boolean b2 = true;
			if(node.left != null){
				b1 = isValid(node.left);
			}
			if(node.right != null){
				b2 = isValid(node.right);
			}
			return b1 & b2;
		}else{
			return false;
		}
	}
	
	public T getLowestCommonAncestor(T n1, T n2){
		List<T> list1 = getAncestor(n1);
		List<T> list2 = getAncestor(n2);
		T result = list1.remove(0);
		list2.remove(0);
		for(int i = 0; i < min(list1.size(), list2.size()); i++){
			if(list1.get(i).compareTo(list2.get(i)) == 0){
				result = list1.get(i);
			}
			list1.remove(i);
			list2.remove(i);
		}
		
		return result;
        
	}
	
	private int min(int num1, int num2){
		return num1 < num2 ? num1 : num2;
	}
	
	// 获取某个结点的所有祖先结点(包含了该结点本身)，从最远祖先到最近祖先排列
	public List<T> getAncestor(T t){
		List<T> ancestorList = new ArrayList<T>();
		ancestorList.add(root.data);
		getAncestor(t, ancestorList, root);
		
		return ancestorList;
	}
	
	private List<T> getAncestor(T t, List<T> ancestorList, BinaryTreeNode<T> node){
		if(t.compareTo(node.data) < 0){
			ancestorList.add(node.left.data);
			getAncestor(t, ancestorList, node.left);
		}else if(t.compareTo(node.data) > 0){
			ancestorList.add(node.right.data);
			getAncestor(t, ancestorList, node.right);
		}else{
			return ancestorList;
		}
		return ancestorList;
	}
	
	public List<T> getNodesBetween(T n1, T n2){
		List<T> list = levelVisit();
		for(int i = 0; i < list.size(); i++){
			if(list.get(i).compareTo(n1) <= 0 || list.get(i).compareTo(n2) >= 0){
				list.remove(i);
				i--;
			}
		}
		return list;
	}

}