	
	package myList;
	
	public class MyBinarySearchTree {
		
		private BinaryNode root;		//���ڵ�
		
		//�ڵ�BinaryNode
		private static class BinaryNode{
			
			Object element;				//�ڵ�����
			BinaryNode left;			//�ýڵ����ڵ�
			BinaryNode right;			//�ýڵ���ҽڵ�
			
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
		
		//��ն�������
		public void makeEmpty(){
			root=null;
		}
		
		//�ж��Ƿ�Ϊ�ա�
		public boolean isEmpty(){
			return this.root==null;
		}
		
		//�ж��Ƿ����һ������
		public boolean contains(Object x,BinaryNode aNode){
			
			//�����жϸö������Ƿ�Ϊ�գ�����û���ҵ����ϵ��ӽڵ㡣
			if(aNode==null){
				return false;
			}
			
			//�͵�ǰ�Ľڵ���бȽϡ�
			Integer comparaResult=(Integer)aNode.element-(Integer)x;
			
			//������С�ڵ�ǰ�ڵ������ʱ���������Ӧ���ڵ�ǰ�ڵ�����ӽڵ��С�
			if(comparaResult>0){
				return contains(x,aNode.left);
			}	
			else if(comparaResult<0){//�����ݴ��ڵ�ǰ�ڵ������ʱ���������Ӧ���ڵ�ǰ�ڵ���Һ��ӽڵ��С�
				return contains(x,aNode.right);
			}
			else{	//�����ݵ��ڵ�ǰ�ڵ������ʱ���������Ӧ���ڵ�ǰ�ڵ��С�
				return true;
			}
		}
		
		//�������ݡ�
		public void insert(Object x){
			root=insert(x,root);
		}
		
		public BinaryNode insert(Object x,BinaryNode aNode){
			
			if(aNode==null){//��ǰΪ�µ����ݽڵ㣬��Ϊ��Ҷ�ӽڵ㣬�������ҽڵ�Ϊnull.
				return new BinaryNode(x,null,null);
			}
			
			//�͵�ǰ�Ľڵ���бȽϡ�
			Integer comparaResult=(Integer)aNode.element-(Integer)x;
			
			//������С�ڵ�ǰ�ڵ������ʱ���������Ӧ���ڵ�ǰ�ڵ�����ӽڵ��С�
			if(comparaResult>0){
				aNode.left= insert(x,aNode.left);
			}	
			else if(comparaResult<0){//�����ݴ��ڵ�ǰ�ڵ������ʱ���������Ӧ���ڵ�ǰ�ڵ���Һ��ӽڵ��С�
				aNode.right=insert(x,aNode.right);
			}
			else{	//�����ݵ��ڵ�ǰ�ڵ������ʱ���������Ӧ���ڵ�ǰ�ڵ���,�����κβ�����
				;
			}
			return aNode;
		}
		
		//��ӡ����������
		public void getData(){
			getData(root);
		}
		public void getData(BinaryNode root){
			if (root != null) { 
			//����
				this.getData(root.left);
			
			//�Һ���
				this.getData(root.right);
			//���ڵ�
				this.print(root);
			}
			
		}
		
		//��ӡ�ڵ㡣
		public void print(BinaryNode root){
			System.out.println(
					(Integer)(root.element)
					);
		}
	}
