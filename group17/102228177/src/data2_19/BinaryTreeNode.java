package data2_19;

public class BinaryTreeNode implements Comparable<BinaryTreeNode>{
	
	private Object data;
	private BinaryTreeNode left;
	private BinaryTreeNode right;
	
	public BinaryTreeNode(Object o) {
		this.data = o;
	}
	
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
	
	@Override
	public int compareTo(BinaryTreeNode o) {
		return (this.data.hashCode() < o.data.hashCode()) ? -1 :
			((this.data.hashCode() == o.data.hashCode()) ? 0 : 1);
	}
	
	public BinaryTreeNode insert(Object o){
		BinaryTreeNode node = new BinaryTreeNode(o);
		insertNode(this,node);
		return node;
	}

	private void insertNode(BinaryTreeNode parentNode, BinaryTreeNode node) {
		//父节点大于添加元素
		if(parentNode.compareTo(node) == 1){
			if(parentNode.left == null){
				parentNode.left = node;
				return;
			}
			insertNode(parentNode.left, node);
		}
		//父节点小于添加元素
		else 
		if(parentNode.compareTo(node) == -1){
			if(parentNode.right == null){
				parentNode.right = node;
				return;
			}
			insertNode(parentNode.right, node);
		}else{
			throw new RuntimeException("No duplicate vertex allowed!");
		}
	}
	
	public static void main(String[] args) {
		BinaryTreeNode tree = new BinaryTreeNode(5);
		tree.insert(2);
		tree.insert(23);
		tree.insert(7);
		tree.insert(1);
	}

}