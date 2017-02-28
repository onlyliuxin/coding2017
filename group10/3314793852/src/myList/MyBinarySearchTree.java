	
	package myList;
	
	public class MyBinarySearchTree {
		
		private BinaryNode root;		//根节点
		
		//节点BinaryNode
		private static class BinaryNode{
			
			Object element;				//节点数据
			BinaryNode left;			//该节点的左节点
			BinaryNode right;			//该节点的右节点
			
			BinaryNode(Object theElement){
				this(theElement, null, null);
			}
			
			BinaryNode(Object element,BinaryNode left,BinaryNode right){
				this.element=element;
				this.left=left;
				this.right=right;
			}
		}
		
		public MyBinarySearchTree(){
			this.root=null;
		}
		
		//清空二叉树。
		public void makeEmpty(){
			root=null;
		}
		
		//判断是否为空。
		public boolean isEmpty(){
			return this.root==null;
		}
		
		//判断是否存在一个数。
		public boolean contains(Object x,BinaryNode aNode){
			
			//首先判断该二叉树是否为空，就是没有找到符合的子节点。
			if(aNode==null){
				return false;
			}
			
			//和当前的节点进行比较。
			Integer comparaResult=(Integer)aNode.element-(Integer)x;
			
			//当数据小于当前节点的数据时，则该数据应该在当前节点的左孩子节点中。
			if(comparaResult>0){
				return contains(x,aNode.left);
			}	
			else if(comparaResult<0){//当数据大于当前节点的数据时，则该数据应该在当前节点的右孩子节点中。
				return contains(x,aNode.right);
			}
			else{	//当数据等于当前节点的数据时，则该数据应该在当前节点中。
				return true;
			}
		}
		
		//插入数据。
		public void insert(Object x){
			root=insert(x,root);
		}
		
		public BinaryNode insert(Object x,BinaryNode aNode){
			
			if(aNode==null){//当前为新的数据节点，因为是叶子节点，所以左右节点为null.
				return new BinaryNode(x,null,null);
			}
			
			//和当前的节点进行比较。
			Integer comparaResult=(Integer)aNode.element-(Integer)x;
			
			//当数据小于当前节点的数据时，则该数据应该在当前节点的左孩子节点中。
			if(comparaResult>0){
				aNode.left= insert(x,aNode.left);
			}	
			else if(comparaResult<0){//当数据大于当前节点的数据时，则该数据应该在当前节点的右孩子节点中。
				aNode.right=insert(x,aNode.right);
			}
			else{	//当数据等于当前节点的数据时，则该数据应该在当前节点中,则不做任何操作。
				;
			}
			return aNode;
		}
		
		//打印出二叉树。
		public void getData(){
			getData(root);
		}
		public void getData(BinaryNode root){
			if (root != null) { 
			//左孩子
				this.getData(root.left);
			
			//右孩子
				this.getData(root.right);
			//父节点
				this.print(root);
			}
			
		}
		
		//打印节点。
		public void print(BinaryNode root){
			System.out.println(
					(Integer)(root.element)
					);
		}
	}
