package cn.fyl.first;

public class BinaryTreeNode {
	private Object data;						//保存数据
	private BinaryTreeNode left;		//左子树
	private BinaryTreeNode right;		//右子树
	private BinaryTreeNode root;		//根节点
	
	public BinaryTreeNode getRoot() {
		return root;
	}
	public void setRoot(BinaryTreeNode root) {
		this.root = root;
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
	
	//插入功能，插入的值按比父类结点的值大，插入右子树；小，插入左子树
	public BinaryTreeNode insert(Object o,BinaryTreeNode t){
		if(t == null){
			BinaryTreeNode temp = new BinaryTreeNode();		//新建插入值的结点
			temp.setData(o);
			return temp;
		}
		boolean temp = (int)o > (int)t.getData();			//暂存插入的值跟结点的值比较大小结果	
		if(temp){																//ture时（插入的值大于结点的值大），所以插到右子树
			System.err.println(temp);
			t.right =insert(o, t.right);
		}
		else{
			System.out.println(temp);
			t.left = insert(o, t.left);
		}
		return  t;
	}
	
	public static void main(String[] args) {
		BinaryTreeNode root = new BinaryTreeNode();
		BinaryTreeNode left1 = new BinaryTreeNode();
		BinaryTreeNode right1 = new BinaryTreeNode();
		BinaryTreeNode left2 = new BinaryTreeNode();
		BinaryTreeNode left3 = new BinaryTreeNode();
		root.setData(5);
		root.setLeft(left1);         left1.setData(2);
		root.setRight(right1);     right1.setData(7);
		left1.setLeft(left2);         left2.setData(1);
		right1.setLeft(left3);	     left3.setData(6);
		BinaryTreeNode temp = root.insert(4, left1);
		System.out.println(left1.getRight().getData());
	}
	
}
