public class BinaryTreeNode {

	private Node root = null;

	public void insert(int value) {
		if(root == null) {
			root = new Node(value);
			root.leftNode = null;
			root.rightNode = null;
		} else {
			Node current = root;
			Node old = root;
			while(true) {
				if(value < current.value) {
					if(current.leftNode == null) {
						current.leftNode = new Node(value);
						break;
					}
					old = current;
					current = current.leftNode;
				} else {
					if(current.rightNode == null) {
						current.rightNode = new Node(value);
						break;
					}
					old = current;
					current = current.rightNode;
				}
			}
		}
	}

}

class Node {
	int value;
	Node leftNode;
	Node rightNode;

	public Node(int value) {
		this.value = value;
	}
}
