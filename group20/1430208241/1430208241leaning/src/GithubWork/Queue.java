package GithubWork;

public class Queue {
     private int maxSize;
     private Object[] array;//���Ԫ������
     private int front;//ǰһ��Ԫ������
     private int rear;//��һ��Ԫ������
     private int items=0;//Ԫ�ظ���
	//������󲢳�ʼ��
     public Queue(int s){
       	maxSize=s;
       	array=new Object[maxSize];
       	front=0;
       	rear=-1;
       	
	}
     public void enQueue(Object o){
		  if(rear==maxSize-1){
			  rear=-1;
			}
		  array[++rear]=o;
		  items++;
				 
	}
	
	public Object deQueue(){
		Object temp =array[front++];
		if(front==maxSize){
			front=0;
		}
		items--;
		return temp;
	}
	
	public boolean isEmpty(){
		
		return items==0;
	}
	
	public int size(){
		return array.length;
	}
}
