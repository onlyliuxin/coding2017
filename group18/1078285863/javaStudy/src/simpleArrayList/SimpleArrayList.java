package simpleArrayList;

import java.util.ArrayList;
import java.util.Arrays;

public class SimpleArrayList {
	//�洢array������
	private Object[] elementData;
	
	//Ĭ������
	public static final int default_capacity = 10;
	
	//array��Ԫ�صĸ���
	private int size;
	
	//Ĭ�Ϲ��캯��
	public SimpleArrayList(){
		//�����СĬ��Ϊ10������
		this.elementData = new Object[default_capacity];
	}
	
	//���ι��캯��,�Լ�ָ��������С
	public SimpleArrayList(int initialCapacity) {
		if(initialCapacity <= 0){
			//������Ч
			throw new IllegalArgumentException("IllegalArgument Error");
		}
		if(initialCapacity < default_capacity)
		{
			initialCapacity = default_capacity;
		}
		
		this.elementData = new Object[initialCapacity];
	}
	
	//�����������Ԫ��
	public boolean Add(int index,Object o)
	{
		if(index > size || index <0)
		{
			return false;
		}
		
		//����Ԫ��
		for (int i = 0; i < size; i++) {
			if(o.equals(elementData[i]))
			{
				//�ƶ�Ԫ�صĸ���
				int nMove = size -index -1;
				if(nMove > 0){
					System.arraycopy(elementData, index, elementData, index+1,nMove);
					elementData[index] = null;
				}
				else {
					return false;
				}
			}
		}
		
		return true;
	}
	
	//���Ԫ��
	//����ֵ:�Ƿ���ӳɹ�
	public boolean Add(Object obj)
	{
		if (null == obj) {
			throw new IllegalArgumentException("invalid Argument!");
		}
		//��array����
		ensureCapacityInternal(size + 1);
		
		//����β��Ԫ�� = ��ֵ
		elementData[size++] = obj;
		
		return true;
	}
	
	public void clear()
	{
		//��elementData��Ԫ��ָ��NULL,��ʹ�������ջ�������
		for (int i = 0; i < elementData.length; i++) {
			elementData[i] = null;
		}
		
		//arrayԪ�ظ�������
		size = 0;
	}
	
	//�Ƴ�array�е�Ԫ��
	public boolean remove(Object obj)
	{
		//����elementData,����Ԫ��
		for (int index = 0; index < size; index++) {
			if(obj.equals(elementData[index]))//�Ƿ����
			{
				 fastRemove(index);
                 return true;
			}
		}
		return false;
	}
	
	private void fastRemove(int index) {
		//�ƶ�Ԫ�صĸ���
        int numMoved = size - index - 1;
        if (numMoved > 0)
        	//��index�������Դ���ǰ�ƶ�
            System.arraycopy(elementData, index+1, elementData, index,numMoved);
        elementData[--size] = null;
    }
	
	//����Ԫ���ܴ�С
	 public int size() {
	        return size;
	 }
	 
	 public Object get(int index) {
		    //У�����
	        if(index > size || index < 0)
	        	throw new IllegalArgumentException();

	        return elementData[index];
	    }
	
	//�漰��������ݿռ�����,��ʱ�Ȳ�����
	private void ensureCapacityInternal(int minCapacity) {
		//�������,��ǰ���������޷�����������ʱ,�Ƿ񳬹��˵�ǰ����ĳ���
		System.out.println("element data length is "+elementData.length);
       if(minCapacity - elementData.length > 0)
       {
    	   //����Ϊ��ǰ������1.5��
    	   int oldCapacity = elementData.length;
    	   int newCapacity = oldCapacity *3/2;
    	   
    	   //�ڲ�����������,��elementData��Ԫ�ؿ�����������
    	   elementData = Arrays.copyOf(elementData, newCapacity);
       }
    }
}
