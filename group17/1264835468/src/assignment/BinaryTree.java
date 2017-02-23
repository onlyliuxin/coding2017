package assignment;

public class BinaryTree {
	private BinaryTreeNode root;

	public BinaryTree(Comparable data) {
		root = new BinaryTreeNode(data);
	}

	public BinaryTree(BinaryTreeNode root) {
		this.root = root;
	}

	public BinaryTreeNode insert(Comparable data) {
		BinaryTreeNode node = new BinaryTreeNode(data);
		if (root == null)
			root = node;
		else
			insert(root, node);
		return node;
	}

	public BinaryTreeNode insert(BinaryTreeNode node) {
		return insert(node.getData());
	}

	private void insert(BinaryTreeNode current, BinaryTreeNode node) {

		if (current.compareTo(node) >= 0) {
			if (current.getLeft() == null)
				current.setLeft(node);
			else
				insert(current.getLeft(), node);
		}
		else {
			if (current.getRight() == null)
				current.setRight(node);
			else
				insert(current.getRight(), node);
		}
	}

	@Override
	public String toString() {
		return new BFSNodeQueue().toString();
	}

	/**
	 * 广度优先遍历节点队列
	 * 
	 * @author Administrator
	 *
	 */
	private class BFSNodeQueue {
		private MyQueue nodeQueue;

		public BFSNodeQueue() {
			nodeQueue = new MyQueue();
			enQueue(root);
		}

		public boolean isEmpty() {
			return nodeQueue.isEmpty();
		}

		public void enQueue(BinaryTreeNode node) {
			if (node != null) nodeQueue.enQueue(node);
		}

		// 出队同时把子节点入队
		public BinaryTreeNode deQueue() {
			BinaryTreeNode first = (BinaryTreeNode) nodeQueue.deQueue();
			enQueue(first.getLeft());
			enQueue(first.getRight());
			return first;
		}

		@Override
		public String toString() {
			StringBuilder stringBuilder = new StringBuilder();
			while (!nodeQueue.isEmpty()) {
				BinaryTreeNode binaryTreeNode = deQueue();
				stringBuilder.append(binaryTreeNode + "\n");
			}
			return stringBuilder.toString();
		}
	}

	public static void main(String[] args) {
		BinaryTree binaryTree = new BinaryTree(4);
		binaryTree.insert(7);
		binaryTree.insert(8);
		System.out.println(binaryTree);
	}
}
