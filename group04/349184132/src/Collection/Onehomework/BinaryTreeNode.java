package Collection.Onehomework;




public class BinaryTreeNode {
	
	private Object data;
	private BinaryTreeNode left;
	private BinaryTreeNode right;

	private BinaryTreeNode root;

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
		BinaryTreeNode newNode =null;
		if(o==null)
			throw new NullPointerException("数据不能为空");

		if(root==null){
			root = new BinaryTreeNode();
			root.setData(o);
		}else {
			 newNode = new BinaryTreeNode();
			 BinaryTreeNode nowNode = root;
			 int val = (int)root.getData();
			 nowNode.setData(o);
			while(true) {

				if ((int)newNode.getData()< val) {
					if(nowNode.left==null){
						nowNode.setLeft(newNode);
						break;
					} else {
						nowNode = nowNode.left;
					}
				} else if((int)newNode.getData()> val){
					if (nowNode.right==null ) {
					nowNode.setRight(newNode);
						break;
					} else{
					nowNode = newNode.right;

					}
				}else {

					throw new IllegalArgumentException("已存在元素结点");
				}
			}
		}
		return newNode;
	}

	public static void main(String[] args) {

	}



}
