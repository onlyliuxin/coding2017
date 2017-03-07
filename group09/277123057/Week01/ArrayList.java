package Week01;
/*
 * time:2017-2-20 21:51 created
 * 
 */
public class ArrayList implements List{
	
	private int size = 0;
	//���ٵĿռ�ֻ��100��
	private Object[] elementData = new Object[100];
	
	//��ĩλ����
	public void add(Object o){
		elementData[size++] = o;
	}
	
	//��ǰλ����Ԫ�أ��������ƶ���ǰλ�ڸ�λ�õ�Ԫ�ؼ����к���Ԫ��
	public void add(int index, Object o){
		System.arraycopy(elementData, index, elementData, index+1, size-index);
		elementData[index] = o;
		size++;
	}
	
	public Object get(int index){
		return elementData[index];
	}
	 //�Ƴ���������ָ����Ԫ��,�ұ�Ԫ������
	public Object remove(int index){
		int numMoved = size - index - 1; 
		if (numMoved > 0)
			System.arraycopy(elementData, index+1, elementData, index, numMoved);
		elementData[--size] = null;
		return elementData[index];
	}
	
	public int size(){
		return size;
	}
	
	public Iterator iterator(){
		return new ArrayListIterator();
	}
	
	private class ArrayListIterator implements Iterator{
		private int pos = 0;
		
		public boolean hashNext() {
			return pos < size();
		}
		
		public Object next() {
			return elementData[pos++];
		}
	}
}
