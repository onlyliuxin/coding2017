package assignment;

//
public class BinaryTree<T extends Comparable<? super T>> implements Iterable<BinaryTreeNode<T>> {
	private BinaryTreeNode<T> root;

	public BinaryTree(T data) {
		root = new BinaryTreeNode<T>(data);
	}

	public BinaryTree(BinaryTreeNode<T> root) {
		this.root = root;
	}

	public BinaryTreeNode<T> insert(T data) {
		BinaryTreeNode<T> node = new BinaryTreeNode<T>(data);
		if (root == null)
			root = node;
		else
			insert(root, node);
		return node;
	}

	public BinaryTreeNode<T> insert(BinaryTreeNode<T> node) {
		return insert(node.getData());
	}

	private void insert(BinaryTreeNode<T> current, BinaryTreeNode<T> node) {

		if (current.getData().compareTo(node.getData()) > 0) {
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
		private MyQueue<BinaryTreeNode<T>> nodeQueue;

		public BFSNodeQueue() {
			nodeQueue = new MyQueue<>();
		}

		public boolean isEmpty() {
			return nodeQueue.isEmpty();
		}

		public void enQueue(BinaryTreeNode<T> node) {
			if (node != null) nodeQueue.enQueue(node);
		}

		// 出队同时把子节点入队
		public BinaryTreeNode<T> deQueue() {
			if (!isEmpty()) {
				BinaryTreeNode<T> first = nodeQueue.deQueue();
				enQueue(first.getLeft());
				enQueue(first.getRight());
				return first;
			}
			throw new QueueIsEmptyException();
		}

		// 把所有出队节点放进另一个队列中
		public MyQueue<BinaryTreeNode<T>> getResult() {
			prepare();
			MyQueue<BinaryTreeNode<T>> result = new MyQueue<>();
			while (!isEmpty()) {
				result.enQueue(deQueue());
			}
			return result;
		}

		private void prepare() {
			clearQueue();
			enQueue(root);
		}

		public void clearQueue() {
			while (!isEmpty()) {
				deQueue();
			}
		}

		@Override
		public String toString() {
			StringBuilder stringBuilder = new StringBuilder();

			Iterator<BinaryTreeNode<T>> iterator = iterator();
			while (iterator.hasNext()) {
				stringBuilder.append(iterator.next() + "\n");
			}
			return stringBuilder.toString();
		}
	}

	@Override
	public Iterator<BinaryTreeNode<T>> iterator() {
		return new BFSIterator();
	}

	private class BFSIterator implements Iterator<BinaryTreeNode<T>> {
		MyArrayList<BinaryTreeNode<T>> list;
		Iterator<BinaryTreeNode<T>> iterator;

		public BFSIterator() {
			MyQueue<BinaryTreeNode<T>> BFSQueue = new BFSNodeQueue().getResult();
			list = new MyArrayList<>();
			while (!BFSQueue.isEmpty()) {
				list.add(BFSQueue.deQueue());
			}
			iterator = list.iterator();
		}

		@Override
		public boolean hasNext() {
			return iterator.hasNext();
		}

		@Override
		public BinaryTreeNode<T> next() {
			return iterator.next();
		}
	}

	public static void main(String[] args) {
		BinaryTree<Integer> binaryTree = new BinaryTree<>(5);
		binaryTree.insert(6);
		binaryTree.insert(7);
		binaryTree.insert(4);
		Iterator<BinaryTreeNode<Integer>> iterator = binaryTree.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
		System.out.println(binaryTree);
	}
}
