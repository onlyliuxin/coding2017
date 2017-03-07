package com.coding.basic;

/**
 * 二叉树
 * 
 * @author cm
 */
public class BinaryTree {

	private BinaryTreeNode root;

	public BinaryTreeNode insert(Object o) {
		BinaryTreeNode binaryTreeNode = new BinaryTreeNode(o);
		if (null == root) {
			root = new BinaryTreeNode(o);
		} else {
			boolean flag = false;
			BinaryTreeNode cursorNode = root;
			while (!flag) {
				if (binaryTreeNode.compareTo(cursorNode) < 0) {
					if (cursorNode.getLeft() == null) {
						cursorNode.setLeft(binaryTreeNode);
						flag = true;
					} else {
						cursorNode = cursorNode.getLeft();
					}
				} else {
					if (cursorNode.getRight() == null) {
						cursorNode.setRight(binaryTreeNode);
						flag = true;
					} else {
						cursorNode = cursorNode.getRight();
					}
				}
			}
		}
		return binaryTreeNode;
	}

	public LinkedList inOrder() {
		LinkedList linkedList = new LinkedList();
		sortLeft(linkedList, root);
		sortRight(linkedList, root);
		return linkedList;
	}

	private void sortRight(LinkedList linkedList, BinaryTreeNode binaryTreeNode) {
		Queue queue = getRightList(binaryTreeNode);
		while (!queue.isEmpty()) {
			BinaryTreeNode queueNode = (BinaryTreeNode) queue.deQueue();
			sortLeft(linkedList, queueNode);
		}
	}

	private void sortLeft(LinkedList linkedList, BinaryTreeNode binaryTreeNode) {
		Stack stack = getLeftList(binaryTreeNode);
		while (!stack.isEmpty()) {
			BinaryTreeNode stackNode = (BinaryTreeNode) stack.pop();
			linkedList.add(stackNode.getData());
			Queue queue = getRightList(stackNode);
			while (!queue.isEmpty()) {
				BinaryTreeNode queueNode = (BinaryTreeNode) queue.deQueue();
				sortLeft(linkedList, queueNode);
			}
		}
		linkedList.add(binaryTreeNode.getData());
	}

	private Stack getLeftList(BinaryTreeNode binaryTreeNode) {
		Stack stack = new Stack();
		while (binaryTreeNode.getLeft() != null) {
			binaryTreeNode = binaryTreeNode.getLeft();
			stack.push(binaryTreeNode);
		}
		return stack;
	}

	private Queue getRightList(BinaryTreeNode binaryTreeNode) {
		Queue queue = new Queue();
		while (binaryTreeNode.getRight() != null) {
			binaryTreeNode = binaryTreeNode.getRight();
			queue.enQueue(binaryTreeNode);
		}
		return queue;
	}

	private class BinaryTreeNode implements Comparable<BinaryTreeNode> {
		private Object data;
		private BinaryTreeNode left;
		private BinaryTreeNode right;

		public Object getData() {
			return data;
		}

		public void setData(Object data) {
			this.data = data;
		}

		public BinaryTreeNode getLeft() {
			return left;
		}

		public void setLeft(BinaryTreeNode left) {
			this.left = left;
		}

		public BinaryTreeNode getRight() {
			return right;
		}

		public void setRight(BinaryTreeNode right) {
			this.right = right;
		}

		public BinaryTreeNode(Object o) {
			setData(o);
		}

		@Override
		public int compareTo(BinaryTreeNode binaryTreeNode) {
			Integer currVal = (Integer) root.getData();
			Integer compVal = (Integer) binaryTreeNode.getData();
			if (currVal < compVal)
				return -1;
			else if (currVal == compVal)
				return 0;
			else
				return 1;
		}
	}
}
