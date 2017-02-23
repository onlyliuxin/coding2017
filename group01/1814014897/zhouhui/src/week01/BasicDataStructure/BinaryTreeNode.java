package week01.BasicDataStructure;

public  class BinaryTreeNode{
	
		private Object data;
		private BinaryTreeNode left;
		private BinaryTreeNode right;
	
		public BinaryTreeNode(Object data){
			this.data = data;
			left = null;
			right = null;
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
		
		public BinaryTreeNode insert(Object o){
				if((Integer)o < (Integer)this.data)
				{
					if(this.left == null){
						BinaryTreeNode node = new BinaryTreeNode(o);
						this.setLeft(node);
						return node;
					}else{
						return this.left.insert(o);
					}
				}else{
					if(this.right == null){
						BinaryTreeNode node = new BinaryTreeNode(o);
						this.setRight(node);
						return node;
					}else{
						return this.right.insert(o);
					}
				}
		}
	}
			
			
