package cn.net.pikachu.basic;

public class BinaryTreeNode {
	
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
	
	public BinaryTreeNode insert(Object o){
		if (!(o instanceof Comparable)){
			throw new ClassCastException(o.toString());
		}
		// 根据节点大小放置元素 小的在左边 大的在右边
		Comparable c = (Comparable) o;
		if (data==null){
			data=o;
			return this;
		}else if (c.compareTo(o)>0){
			if (left==null){
				left=new BinaryTreeNode();
			}
			return left.insert(o);
		}else {
			if (right==null){
				right=new BinaryTreeNode();
			}
			return right.insert(o);
		}
	}
	public void inOrderTraversal(Visit visit,BinaryTreeNode node){
		if (node.left!=null)
		inOrderTraversal(visit,node.left);
		if (node!=null)
		visit.visit(node.data);
		if (node.right!=null)
		inOrderTraversal(visit,node.right);
	}
	public static interface Visit{
		public void visit(Object o);
	}
}
