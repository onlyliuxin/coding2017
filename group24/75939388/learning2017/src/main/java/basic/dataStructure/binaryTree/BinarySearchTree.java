package basic.dataStructure.binaryTree;

import java.util.List;

public class BinarySearchTree<T extends Comparable> {
	
	BinaryTreeNode<T> root;

	public BinarySearchTree(BinaryTreeNode<T> root){
		this.root = root;
	}

	public BinaryTreeNode<T> getRoot(){
		return root;
	}

	public T findMin(){
		List<T> list = BinaryTreeUtil.preOrderVisit(root);
		T min = list.get(0);
		for(T t : list){
			min = min.compareTo(t) == -1 ? min : t;
		}
		return min;
	}
	public T findMax(){
		List<T> list = BinaryTreeUtil.preOrderVisit(root);
		T max = list.get(0);
		for(T t : list){
			max = max.compareTo(t) == 1 ? max : t;
		}
		return max;
	}

	public int height() {
		return calHeight(root);
	}

	private int calHeight(BinaryTreeNode<T> root){
		if(root == null) return 0;

		int left = calHeight(root.left);
		int right = calHeight(root.right);

		return (left > right ? left : right) + 1;

	}

	public int size() {
		List<T> list = BinaryTreeUtil.preOrderVisit(root);
		return list.size();
	}

	public void remove(T e){
		find(root, (Integer) e);
	}

	private BinaryTreeNode<T> find(BinaryTreeNode<T> node, int value){
		if(node.getData().compareTo(value) == 0){
			return node;
		}

		return null;
	}

	private void noLeafRemove(){

	}

	private void oneChildRemove(){

	}
}

