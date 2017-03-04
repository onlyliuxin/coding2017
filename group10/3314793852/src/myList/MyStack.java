	package myList;
	
	public class MyStack {
		
		private int top;			//ջ��
		private Object[] arr;		//ջ���ݵĴ洢����
		private int theSize;			//ջ�ĵ�ǰ����
		
		public MyStack(){
			clear();
		}
		
		//��ȡ��ǰջ������
		public int size(){
			return theSize;
		}
		
		//ջ��գ�top��ֵΪ-1��Ȼ�����¶���һ���µ����顣
		public void clear() {
			top=-1;
			theSize=0;
			arr=new Object[100];
		}
		
		//push����ջ��
		public void push(Object aData){
			top++;
			arr[top]=aData;
			theSize++;
		}
		
		//pop����ջ��
		public Object pop(){
			Object a;
			a=arr[top];
			arr[top]=null;
			top--;
			theSize--;
			return a;
		}
		
		//��ӡ��ջ�е�����
		public void print(){
			for(int i=0;i<theSize;i++){
				System.out.println(arr[i]);
			}
		}
	}
