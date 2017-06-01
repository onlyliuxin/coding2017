package tree;

import java.util.ArrayList;
import java.util.List;
import queue.Queue;

public class BinarySearchTree<T extends Comparable> {

	BinaryTreeNode<T> root;

	public BinarySearchTree(BinaryTreeNode<T> root) {
		this.root = root;
	}

	public BinaryTreeNode<T> getRoot() {
		return root;
	}

	public T findMin() {

		BinaryTreeNode<T> node = getRoot();
		while (node.getLeft() != null) {
			node = node.getLeft();
		}

		return node.getData();
	}

	public T findMax() {

		BinaryTreeNode<T> node = getRoot();
		while (node.getRight() != null) {
			node = node.getRight();
		}

		return node.getData();
	}

	public void remove(T e) {

		if (e == null) {
			return;
		}

		remove(this.root, e);

	}

	private void remove(BinaryTreeNode<T> node, T e) {

		if (node == null) {
			return;
		}

		int compare = node.data.compareTo(e);
		if (compare > 0) {
			remove(node.getLeft(), e);
			return;
		}

		if (compare < 0) {
			remove(node.getRight(), e);
			return;
		}

		if (compare == 0) {
			if (node.getLeft() == null && node.getRight() == null) {
				node = null;
				return;
			}

			if (node.getRight() != null) {
				BinaryTreeNode<T> pNode = node.getRight().findMinNode();
				node.data = pNode.data;
				pNode = null;
				return;
			}

			if (node.getRight() == null && node.getLeft() != null) {

				BinaryTreeNode<T> pNode = node.getLeft().findMaxNode();
				node.data = pNode.data;
				pNode = null;
				return;

			}

		}

	}

	public int height() {

		return getHeight(getRoot());
	}

	private int getHeight(BinaryTreeNode<T> node) {

		if (node == null) {
			return 0;
		}

		return Math.max(getHeight(node.getLeft()), getHeight(node.getRight())) + 1;

	}

	public int size() {

		BinaryTreeNode<T> node = getRoot();
		List<T> nodes = BinaryTreeUtil.inOrderVisit(node);

		return nodes.size();
	}

	public List<T> levelVisit() {
		BinaryTreeNode<T> root = this.root;

		List<T> result = new ArrayList<T>();

		Queue<BinaryTreeNode<T>> queue = new Queue<>();

		if (root == null) {
			return result;
		}

		queue.enQueue(root);

		while (!queue.isEmpty()) {
			BinaryTreeNode<T> curr = queue.deQueue();
			result.add(curr.data);

			if (curr.getLeft() != null) {
				queue.enQueue(curr.getLeft());
			}
			if (curr.getRight() != null) {
				queue.enQueue(curr.getRight());
			}

		}

		return result;
	}

	public boolean isValid() {

		return isValidBST(root);

	}

	boolean firstCompare = true;
	T prev = null;

	public boolean isValidBST(BinaryTreeNode<T> root) {
		if (root == null)
			return true;
		return isValidBST(root.left) && compare(root.data) && isValidBST(root.right);
	}

	public boolean compare(T e) {

		if (firstCompare) {
			firstCompare = false;
			prev = e;
			return true;
		}

		if (e.compareTo(prev) <= 0) {
			return false;
		}

		prev = e;
		return true;
	}

	public T getLowestCommonAncestor(T n1, T n2) {

		int compare = n1.compareTo(n2);
		if (compare == 0) {
			throw new RuntimeException("Arguments could not be the same.");
		}

		T min = compare > 0 ? n2 : n1;
		T other = compare > 0 ? n1 : n2;

		T result = findCommonAncestor(root, min, other);

		return result;

	}

	private T findCommonAncestor(BinaryTreeNode<T> curr, T min, T other) {

		if (curr.data.compareTo(min) == 0 || curr.data.compareTo(other) == 0) {
			return curr.data;
		}

		if (curr.data.compareTo(min) < 0 && curr.data.compareTo(other) < 0) {

			return findCommonAncestor(curr.getRight(), min, other);
		}

		if (curr.data.compareTo(min) > 0 && curr.data.compareTo(other) > 0) {

			return findCommonAncestor(curr.getLeft(), min, other);
		}

		return curr.data;

	}

	private void saveNodeBetween(BinaryTreeNode<T> curr, T min, T other, List<T> result) {

		if (curr.data.compareTo(min) == 0 || curr.data.compareTo(other) == 0) {
			find++;
			return;
		}

		if (curr.data.compareTo(other) > 0) {
			if (curr.getLeft() != null) {
				saveNodeBetween(curr.getLeft(), min, other, result);
			}
			return;
		}

		if (curr.data.compareTo(min) > 0 && curr.data.compareTo(other) < 0) {
			result.add(curr.data);
			if (curr.getLeft() != null) {
				saveNodeBetween(curr.getLeft(), min, other, result);
			}
			if (curr.getRight() != null) {
				saveNodeBetween(curr.getRight(), min, other, result);
			}
			return;
		}

		if (curr.data.compareTo(min) < 0) {
			System.out.println("2 case " + curr.data);
			if (curr.getRight() != null) {
				saveNodeBetween(curr.getRight(), min, other, result);
			}
			return;
		}

	}

	int find = 0;

	public List<T> getNodesBetween(T n1, T n2) {

		List<T> result = new ArrayList<T>();

		int compare = n1.compareTo(n2);
		if (compare == 0) {
			throw new RuntimeException("Arguments could not be the same.");
		}

		find = 0;

		T min = compare > 0 ? n2 : n1;
		T other = compare > 0 ? n1 : n2;

		saveNodeBetween(root, min, other, result);
		if (find == 2) {
			return result;
		}

		return null;
	}

}
