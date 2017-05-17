package dataStructure_8_Queue;

public class CircleQueue <E>{

	private final static int DEFAULT_SIZE = 5;
	
	// ������������ѭ�����е�Ԫ��
	private Object [] elementData = new Object[DEFAULT_SIZE];
	
	// ��ͷ
	private int front = 0;
	// ��β
	private int rear = 0;
	
	
	public Object getElementData(int index){
		return elementData[index];
	}
	public boolean isEmpty(){
		return front==rear;
	}
	
	public int size(){
		return rear-front;
	}
	
	public void enQueue(E data){
		if((rear+1)%DEFAULT_SIZE == front){
			return;
		}else{
			rear = (++rear)%DEFAULT_SIZE;
			elementData[rear] = data;
		}
	}
	
	public E deQueue(){
		front = (++front)%DEFAULT_SIZE;
		return (E) elementData[front];
	}
}
