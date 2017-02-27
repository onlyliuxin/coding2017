package com.ikook.basic_data_structure;

/**
 * @author ikook;  QQ号码: 935542673
 */
public class MyBinarySearchTree {

	public static Node root;

	public MyBinarySearchTree() {
		root = null;
	}
	
	/**
	 * 插入操作
	 * @param id
	 */
	public void insert(int id) {
		Node newNode = new Node(id); 
		if (root == null) {
			root = newNode;
			return;
		}
		Node current = root; //当前节点
		Node parent = null;  //父节点，即上一个节点
		while (true) {
			parent = current;
			if (id < current.data) {
				current = current.left;
				if (current == null) {
					parent.left = newNode;
					return;
				}
			} else {
				current = current.right;
				if (current == null) {
					parent.right = newNode;
					return;
				}
			}
		}
	}

	/**
	 * 查找操作
	 * @param id
	 * @return
	 */
	public boolean find(int id) {
		Node current = root;
		while (current != null) {
			if (current.data == id) {
				return true;
			} else if (current.data > id) {
				current = current.left;
			} else {
				current = current.right;
			}
		}
		return false;
	}

	/**
	 * 删除操作
	 * @param id
	 * @return
	 */
	public boolean delete(int id) {
		if (root == null) // 根节点为空，则树为空，返回false。
			return false;
		else {
			Node parent = root;
			Node current = root;
			boolean isLeftChild = false; // 是否在左子树，默认为false：即不在。
			// 找到删除点以及是否在左子树
			while (current.data != id) {
				parent = current;
				if (current.data > id) {
					isLeftChild = true;
					current = current.left;
				} else {
					isLeftChild = false;
					current = current.right;
				}
				if (current == null) {
					return false;
				}
			}
			
			// 如果删除节点的左节点为空，右节点也为空。
			if (current.left == null && current.right == null) {
				if (current == root) {
					root = null;
				}
				if (isLeftChild == true) {
					parent.left = null;
				} else {
					parent.right = null;
				}
			}
			// 如果删除节点只有一个子节点，则该节点为左节点或者右节点。
			else if (current.right == null) {
				if (current == root) {
					root = current.left;
				} else if (isLeftChild) {
					parent.left = current.left;
				} else {
					parent.right = current.left;
				}
			} else if (current.left == null) {
				if (current == root) {
					root = current.right;
				} else if (isLeftChild) {
					parent.left = current.right;
				} else {
					parent.right = current.right;
				}
			} 
			// 如果删除节点左节点右节点都不为空。
			else if (current.left != null && current.right != null) {
				
				// 寻找删除节点的后继者：这说明已经发现最小元素在右子树中
				Node successor = getSuccessor(current);
				if (current == root) {
					root = successor;
				} else if (isLeftChild) {
					parent.left = successor;
				} else {
					parent.right = successor;
				}
				successor.left = current.left;
			}
			return true;
		}
	}

	/**
	 * 获取删除节点的后继者：删除节点的后继者是在其右节点树中最小的节点
	 * @param deleleNode
	 * @return
	 */
	private Node getSuccessor(Node deleleNode) {
		Node successsor = null;
		Node successsorParent = null;
		Node current = deleleNode.right;
		while (current != null) {
			successsorParent = successsor;
			successsor = current;
			current = current.left;
		}
		// 检查后继者是否有右节点
		// 如果有右节点树，则将其添加到successorParent(后继者父节点)的左节点。
		if (successsor != deleleNode.right) {
			successsorParent.left = successsor.right;
			successsor.right = deleleNode.right;
		}
		return successsor;
	}

	/**
	 * 显示二叉树
	 * @param root
	 * @param sb
	 */
	private void display(Node root, StringBuilder sb) {
		if (root != null) {
			display(root.left, sb);
			sb.append(root.data + " ");
			display(root.right, sb);
		}
	}

	/**
	 * 中序遍历：左子树->根节点->右子树
	 * @param root
	 * @return
	 */
	public String inorderTraverse(Node root) {
		StringBuilder sb = new StringBuilder();
		this.display(root, sb);
		return sb.toString();
	}
}

/**
 * 用于表示节点
 * @author ikook
 */
class Node {

	int data;
	Node left;
	Node right;

	public Node(int data) {
		this.data = data;
		left = null;
		right = null;
	}
}