/*�������ƣ�
 * ԭ�ļ����ƣ�
 * Ҫ�㣺
 * 1. ʵ�ֻ��������ݽṹ�ࣺQueue

 */
public class Queue_self<T> {
	private LinkedList_self<T> data;
	private int size;
	
	//��ʼ������
	public Queue_self(){
		data=new LinkedList_self<T>();
	}
	
	//�����
	public void enQueue(Object item){
		data.add((T)item);
		size++;
	}
	
	//������
	public T deQueue(){
		size--;
		return data.remove(0);
	}
	
	//�Ƿ�Ϊ�ն���
	public boolean isEmpty(){
		return (size==0);
	}
	
	//���г���
	public int size(){
		return size;
	}
}
