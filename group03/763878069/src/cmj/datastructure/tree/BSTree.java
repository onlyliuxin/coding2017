package cmj.datastructure.tree;

/**
 * 二叉搜索树
 * 
 * @author think
 *
 */
public class BSTree {
	private Node root;// 根结点

	public BSTree() {
		root = null;
	}

	public void insert(Node node, int data) {
		if (null == root) {
			root = new Node(data);
		} else {
			if (data < node.data) {
				if (null == node.left) {

					node.left = new Node(data);
				} else {
					insert(node.left, data);
				}
			} else {
				if (node.right == null) {
					node.right = new Node(data);
				} else {
					insert(node.right, data);
				}
			}
		}
	}

	/**
	 * 前序遍历
	 * 
	 * @param node
	 */
	public void preOrder(Node node) {
		if (node != null) {
			System.out.println(node.data);
			preOrder(node.left);
			preOrder(node.right);
		}
	}

	/**
	 * 中序遍历
	 * 
	 * @param node
	 */
	public void inOrder(Node node) {
		if (node != null) {
			inOrder(node.left);
			System.out.println(node.data);
			inOrder(node.right);
		}
	}

	/**
	 * 后序遍历
	 * 
	 * @param node
	 */
	public void postOrder(Node node) {
		if (node != null) {
			postOrder(node.left);
			postOrder(node.right);
			System.out.println(node.data);
		}
	}

	// 删除节点分三种方式删除节点
	// 1、删除没有子节点的节点，直接让该节点的父节点的左节点或右节点指向空
	// 2、删除有一个子节点的节点，直接让该节点的父节点指向被删除节点的剩余节点
	// 3、删除有三个节点的子节点，找到要删除节点的后继节点， 用该节点替代删除的节点
	public boolean delete(int data) {
		// 首先查找节点，并记录该节点的父节点引用
		Node current = root;
		Node parent = root;
		boolean isLeftNode = true;
		while (current.data != data) {
			parent = current;
			if (data < current.data) {
				isLeftNode = true;
				current = current.left;
			} else {
				isLeftNode = false;
				current = current.right;
			}
		}
		if (current == null) {
			System.out.println("没有找到要删除的节点！");
			return false;
		}
		// 下面分三种情况删除节点
		if (current.left == null && current.right == null) { // 要删除的节点没有子节点
			if (current == root) { // 根节点就删除整棵树
				root = null;
			} else if (isLeftNode) { // 如果是左节点，做节点指向空
				parent.left = null;
			} else { // 如果是右节点，右节点指向空
				parent.right = null;
			}
		} else if (current.left == null) { // 要删除的节点只有右节点
			if (current == root) {
				root = current.right;
			} else if (isLeftNode) {
				parent.left = current.right;
			} else {
				parent.right = current.right;
			}
		} else if (current.right == null) { // 要删除的节点只有左节点
			if (current == root) {
				root = current.left;
			} else if (isLeftNode) {
				parent.left = current.left;
			} else {
				parent.right = current.left;
			}
		} else { // 要删除的节点有两个节点
			Node successor = findSuccessor(current);
			if (current == root) {
				root = successor;
			} else if (isLeftNode) {
				parent.left = successor;
			} else {
				parent.right = successor;
			}
			successor.left = current.left;
		}
		return true;
	}

	/**
	 * 找结点(x)的后继结点。即，查找"二叉树中数据值大于该结点"的"最小结点"。
	 * 
	 * @param delNode——要删除的结点
	 * @return
	 */
	private Node findSuccessor(Node delNode) {
		Node parent = delNode;
		Node successor = delNode;
		Node current = delNode.right;
		/* 找到要删除结点的右子树的最左叶子结点，就是比要删除的数据大的最小结点 */
		while (current != null) {
			parent = successor;
			successor = current;
			current = current.left;
		}

		if (successor != delNode.right) {
			parent.left = successor.right;
			successor.right = delNode.right;
		}
		return successor;
	}

	/**
	 * 内部结点类
	 * 
	 * @author think
	 *
	 */
	private class Node {
		private Node left;
		private Node right;
		private int data;

		public Node(int data) {
			this.left = null;
			this.right = null;
			this.data = data;
		}
	}

	public static void main(String[] args) {
		int[] a = { 2, 4, 12, 45, 21, 6, 111, 1, 23, 45 };
		BSTree bTree = new BSTree();
		for (int i = 0; i < a.length; i++) {
			bTree.insert(bTree.root, a[i]);
		}
		bTree.preOrder(bTree.root);
		bTree.inOrder(bTree.root);
		bTree.postOrder(bTree.root);
	}

}
