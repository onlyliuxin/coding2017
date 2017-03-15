package week1.com.coding.basic;

import java.util.Arrays;

public class ArrayList implements List
{
    /** ArrayList��������һ����̬���飬����ͨ������ȣ����������ܹ��Զ����� **/
    // ��ʾ��ǰ����
    private int size = 0;
    
    // Ĭ������Ϊ100
    private Object[] elementData;
    
    // Ĭ�ϵĹ��캯��
    public ArrayList()
    {
        this(2);
    }
    
    public ArrayList(final int initCapacity)
    {
        if (initCapacity < 0)
        {
            throw new RuntimeException("��ʼ��������С��0");
        }
        elementData = new Object[initCapacity];
    }
    
    /**
     * �ڵ�ǰ�����������Ԫ�أ�����Ҫ���ǵ��������Ĭ�����鳤�ȣ���Ҫ��������չ
     */
    public void add(Object o)
    {
        int oldCapacity = elementData.length;
        // ���������ǰ����
        if ((size + 1) > oldCapacity)
        {
            int newCapacity = oldCapacity << 1;// ֱ������2��,����������ֱ��copyof��������
            elementData = Arrays.copyOf(elementData, newCapacity);
        }
        elementData[size++] = o;
    }
    
    /**
     * �ڵ�ǰ�������Ԫ�أ������Ҫ�������Ԫ��������ƣ���Ҳ������ArrayList���LinkedList����ɾ����Ч�ʺܲ�
     */
    public void add(int index, Object o)
    {
        if (index > size || index < 0)
        {
            throw new RuntimeException("Ҫ�����Ԫ���������ܴ�����������ĳ���");
        }
        int oldCapacity = elementData.length;
        if ((size + 1) > oldCapacity)
        {
            int newCapacity = oldCapacity << 1;
            System.arraycopy(elementData, index, elementData, index + 1, newCapacity - index);
            size++;
            return;
        }
        // ��index��ſ�ʼ�������,�����õ���System.arrayCopy����
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = o;
        size++;
    }
    
    public Object get(int index)
    {
        if (index > size || index < 0)
        {
            throw new RuntimeException("Ҫ��ȡ��Ԫ���������ܴ��ڵ�ǰ����ĳ���");
        }
        return elementData[index];
    }
    
    public Object remove(int index)
    {
        Object oldElement = elementData[index];
        int moveLength = size - index - 1;
        if (moveLength > 0)
            // ��indexλ����������ƫ��
            System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        elementData[--size] = null;
        return oldElement;
    }
    
    public int size()
    {
        return size;
    }
    
    public Iterator iterator()
    {
        return new IteratorImpl();
    }
    
    /**
     * ����һ���ڲ���ʵ��Iterator�ӿ�
     */
    private class IteratorImpl implements Iterator
    {
        int cursor;// �α꣬����ǰ������λ��
        
        int lastRet = -1;// ��ʾ��һ��Ԫ�ص�����λ��
        
        @Override
        public boolean hasNext()
        {
            // �����ǰ��������ArrayList��ʵ�������򷵻�flase������true;
            return cursor >= size ? false : true;
        }
        
        @Override
        public Object next()
        {
            lastRet = cursor;
            Object object = elementData[lastRet];
            cursor++;
            return object;
        }
        
    }
}
