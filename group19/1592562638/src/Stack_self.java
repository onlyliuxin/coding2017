/*�������ƣ�
 * ԭ�ļ����ƣ�
 * Ҫ�㣺
 * 1. ʵ�ֻ��������ݽṹ�ࣺStack

 */
public class Stack_self<T> {
	private ArrayList_self<T> elementData=new ArrayList_self<T>();
	private int size=0;
	
	//��ջ
	public void push(Object item){
		elementData.add((T)item);
		size++;
	}
	
	//��ջ
	public Object pop(){
		if(size>0){
			size--;
			return elementData.remove(size);
		}
		else{
			return null;
		}
	}
	
	//ȡ��ջ��Ԫ��
	public Object peek(){
		return elementData.get(size-1);
	}
	
	//�ж��Ƿ�Ϊ��
	public boolean isEmpty(){
		return (size==0);
	}
	
	//����size
	public int size(){
		return size;
	}
}
