package com.sprint.datastructure.tree;

import com.sprint.datastructure.Queue;
import java.util.ArrayList;
import java.util.List;
public class BinarySearchTree<T extends Comparable> {
	
	BinaryTreeNode<T> root;
	public BinarySearchTree(BinaryTreeNode<T> root) {
		this.root = root;
	}

	public BinaryTreeNode<T> getRoot() {
		return root;
	}

	public T findMin() {
		if (root == null) {
			return null;
		}
		return findMin(root).data;
	}

	public T findMax() {
		if (root == null) {
			return null;
		}
		return findMax(root).data;
	}

	public int height() {
		return height(root);
	}

	public int size() {
		return size(root);
	}

	public void remove(T e) {
		remove(e, root);	
	} 


	/**
	 * 层级访问
	 * @return
	 */
	public List<T> levelVisit() {
		List<T> result = new ArrayList<T>();		
		Queue<BinaryTreeNode<T>> queue = new Queue<BinaryTreeNode<T>>();
		BinaryTreeNode<T> node = root;
		if (node != null) {
			queue.enQueue(node);
			while (!queue.isEmpty()) {
				node = queue.deQueue();
				result.add(node.data);
				if (node.left != null) {
					queue.enQueue(node.left);
				}
				if (node.right != null) {
					queue.enQueue(node.right);
				}
			}
		}
		return result;
	}

	/**
	 * 判断是否属于二叉查找树
	 * 使用中序排列后,判断元素是否为升序
	 * @return
	 */
	public boolean isValid() {
		if (root == null) {
			return true;
		}	

		List<T> orderVisit = BinaryTreeUtil.inOrderVisit(root);
		for (int i = 0; i < orderVisit.size(); i++) {
			//出现中序排序后，前面的元素比后面的大
			if (orderVisit.get(i).compareTo(orderVisit.get(i+1)) > 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 获取两个节点的最小祖先
	 *
	 * @param node1 BinaryNode
	 * @param node2 BinaryNode
	 * @return
	 */
	public T getLowestCommonAncestor(T node1, T node2) {
		return getLowestCommonAncestor(root, node1, node2);
	}

	private T getLowestCommonAncestor(BinaryTreeNode<T> node, T node1, T node2) {
		if (findNode(node.left, node1)) {
			if (findNode(node.right, node2)) {
				return node.data; 
			} else {
				return getLowestCommonAncestor(node.left, node1, node2);
			}	
		} else {
			if (findNode(node.left, node2)) {
				return node.data;
			} else {
				return getLowestCommonAncestor(node.right, node1, node2);
			}
		}
	}

	/**
	 *	判断节点是否在当前二叉树中
	 *	@param root
	 *	@param node
	 *	@return
	 */
	private boolean findNode(BinaryTreeNode<T> root, T node) {
		if (root == node) {
			return true;
		}	

		if (node == null) {
			return false;
		}	

		//递归查找
		boolean foundLeft = findNode(root.left, node);
		if (foundLeft) {
			return true;
		}

		boolean foundRight = findNode(root.right, node);
		return foundRight;
	}

	 /**
     * 给定两个值， 获得处于这两个值中间的节点
     * @param n1
     * @param n2
     * @return
     */
    public List<T> getNodesBetween(T n1, T n2) {
        List<T> result = new ArrayList<>();
        getNodesBetween(root, n1, n2, result);
        return result;
    }

    private void getNodesBetween(BinaryTreeNode<T> node, T data_1, T data_2, List<T> result){
        if (node == null){
            return;
        }
        if (node.data.compareTo(data_1) > 0){
            getNodesBetween(node.left, data_1, data_2, result);
        }
        if (node.data.compareTo(data_1) >= 0 && node.data.compareTo(data_2) <= 0){
            result.add(node.getData());
        }
        if (node.data.compareTo(data_2) < 0){
            getNodesBetween(node.right, data_1, data_2, result);
        }
    }

	private BinaryTreeNode<T> findMin(BinaryTreeNode<T> p) {
		if (p == null) {
			return null;
		} else if (p.left == null) {
			return p;
		} else {
			return findMin(p.left);
		}
	}

	private BinaryTreeNode<T> findMax(BinaryTreeNode<T> p) {
		if (p == null) {
			return null;
		} else if (p.right == null) {
			return p;
		} else {
			return findMax(p.right);
		}
	}

	private int height(BinaryTreeNode<T> t) {
		if (t == null) {
			return 0;
		} else {
			int leftChildHeight = height(t.left);
			int rightChildHeight = height(t.right);
			if (leftChildHeight > rightChildHeight) {
				return leftChildHeight + 1;
			} else {
				return rightChildHeight + 1;
			}
		}
	}

	private int size(BinaryTreeNode<T> t) {
		if (t == null) {
			return 0;
		}
		return size(t.left) + 1 + size(t.right);
	}

	private BinaryTreeNode<T> remove(T x, BinaryTreeNode<T> t) {
		if (t == null) {
			return t;
		}	

		int compareResult = x.compareTo(t.data);
		
		if (compareResult < 0) {
			t.left = remove(x, t.left);
		} else if (compareResult < 0) {
			t.right = remove(x, t.right);
		} else {
			//todo	
			if (t.left != null && t.right != null) {
				t.data = findMin(t.right).data;
				t.right = remove(t.data, t.right);
			} else {
				t = (t.left != null) ? t.left : t.right;
			}
		}
		return t;
	}

}
