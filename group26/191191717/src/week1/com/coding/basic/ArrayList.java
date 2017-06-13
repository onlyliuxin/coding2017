package week1.com.coding.basic;

import java.util.Arrays;

public class ArrayList implements List
{
    /** ArrayList本质上是一个动态数组，跟普通数组相比，他的容量能够自动增长 **/
    // 表示当前长度
    private int size = 0;
    
    // 默认容量为100
    private Object[] elementData;
    
    // 默认的构造函数
    public ArrayList()
    {
        this(2);
    }
    
    public ArrayList(final int initCapacity)
    {
        if (initCapacity < 0)
        {
            throw new RuntimeException("初始容量不能小于0");
        }
        elementData = new Object[initCapacity];
    }
    
    /**
     * 在当前数组的最后添加元素，这里要考虑到如果超出默认数组长度，需要对数组扩展
     */
    public void add(Object o)
    {
        int oldCapacity = elementData.length;
        // 如果超过当前容量
        if ((size + 1) > oldCapacity)
        {
            int newCapacity = oldCapacity << 1;// 直接扩充2倍,并将旧数组直接copyof到新数组
            elementData = Arrays.copyOf(elementData, newCapacity);
        }
        elementData[size++] = o;
    }
    
    /**
     * 在当前数组插入元素，因此需要将后面的元素整体后移，这也显现了ArrayList相比LinkedList在增删方面效率很差
     */
    public void add(int index, Object o)
    {
        if (index > size || index < 0)
        {
            throw new RuntimeException("要插入的元素索引不能大于整体数组的长度");
        }
        int oldCapacity = elementData.length;
        if ((size + 1) > oldCapacity)
        {
            int newCapacity = oldCapacity << 1;
            System.arraycopy(elementData, index, elementData, index + 1, newCapacity - index);
            size++;
            return;
        }
        // 从index序号开始整体后移,这里用到了System.arrayCopy方法
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = o;
        size++;
    }
    
    public Object get(int index)
    {
        if (index > size || index < 0)
        {
            throw new RuntimeException("要获取的元素索引不能大于当前数组的长度");
        }
        return elementData[index];
    }
    
    public Object remove(int index)
    {
        Object oldElement = elementData[index];
        int moveLength = size - index - 1;
        if (moveLength > 0)
            // 从index位后整体向左偏移
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
     * 定义一个内部类实现Iterator接口
     */
    private class IteratorImpl implements Iterator
    {
        int cursor;// 游标，代表当前的索引位置
        
        int lastRet = -1;// 表示上一个元素的索引位置
        
        @Override
        public boolean hasNext()
        {
            // 如果当前索引大于ArrayList的实际数量则返回flase，否则true;
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
