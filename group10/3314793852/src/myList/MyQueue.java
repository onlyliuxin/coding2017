	
	package myList;
	
	public class MyQueue {
		
		private Object[] arr;
		private int head;
		private int tail;
		private int theSize;
		
		public MyQueue(){
			clear();
		}
		
		//��ա�
		public void clear() {
			 head=0;
			 tail=0;
			 theSize=0;
			 arr=new Object[3];
		}
		
		//push,������С�
		public void push(Object aDate){
			
			if(theSize!=arr.length){//ֻ���ڶ���û���������д�ſ��Բ������ݡ�
				arr[tail]=aDate;
				tail++;
				if(tail>(arr.length-1)){//��tail�Ѿ���������β��ʱ���������ͷ����Ϊ��ʱ�����µ�������뵽�����ͷ����
					tail=0;
				}
				theSize++;
			}
		}
		
		//pop,�����С�
		public Object pop(){
			Object a=null;
			if(theSize!=0){	//�������Ϊ�գ����ܽ��г����еĲ�����
				a=arr[head];
				arr[head]=null;
				head++;
				if(head>(arr.length-1)){
					head=0;
				}
				theSize--;
			}
			return a;
		}
		
		//��ӡ�����С�
		public void print(){
			for(int i=0;i<arr.length;i++){
				if(arr[i]!=null){
					System.out.println(arr[i]);
				}
			}
		}
		
	}
