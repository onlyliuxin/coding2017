package simpleArrayList;

import java.util.ArrayList;
import java.util.Arrays;

public class SimpleArrayList {
	//存储array的容器
	private Object[] elementData;
	
	//默认容量
	public static final int default_capacity = 10;
	
	//array中元素的个数
	private int size;
	
	//默认构造函数
	public SimpleArrayList(){
		//构造大小默认为10的数组
		this.elementData = new Object[default_capacity];
	}
	
	//带参构造函数,自己指定容量大小
	public SimpleArrayList(int initialCapacity) {
		if(initialCapacity <= 0){
			//参数无效
			throw new IllegalArgumentException("IllegalArgument Error");
		}
		if(initialCapacity < default_capacity)
		{
			initialCapacity = default_capacity;
		}
		
		this.elementData = new Object[initialCapacity];
	}
	
	//在索引处添加元素
	public boolean Add(int index,Object o)
	{
		if(index > size || index <0)
		{
			return false;
		}
		
		//查找元素
		for (int i = 0; i < size; i++) {
			if(o.equals(elementData[i]))
			{
				//移动元素的个数
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
	
	//添加元素
	//返回值:是否添加成功
	public boolean Add(Object obj)
	{
		if (null == obj) {
			throw new IllegalArgumentException("invalid Argument!");
		}
		//给array扩容
		ensureCapacityInternal(size + 1);
		
		//数组尾部元素 = 新值
		elementData[size++] = obj;
		
		return true;
	}
	
	public void clear()
	{
		//将elementData中元素指向NULL,促使垃圾回收机制启动
		for (int i = 0; i < elementData.length; i++) {
			elementData[i] = null;
		}
		
		//array元素个数清零
		size = 0;
	}
	
	//移除array中的元素
	public boolean remove(Object obj)
	{
		//遍历elementData,查找元素
		for (int index = 0; index < size; index++) {
			if(obj.equals(elementData[index]))//是否相等
			{
				 fastRemove(index);
                 return true;
			}
		}
		return false;
	}
	
	private void fastRemove(int index) {
		//移动元素的个数
        int numMoved = size - index - 1;
        if (numMoved > 0)
        	//将index后数据以此向前移动
            System.arraycopy(elementData, index+1, elementData, index,numMoved);
        elementData[--size] = null;
    }
	
	//返回元素总大小
	 public int size() {
	        return size;
	 }
	 
	 public Object get(int index) {
		    //校验参数
	        if(index > size || index < 0)
	        	throw new IllegalArgumentException();

	        return elementData[index];
	    }
	
	//涉及到最大扩容空间问题,暂时先不考虑
	private void ensureCapacityInternal(int minCapacity) {
		//扩容情况,当前容器容量无法存下新数据时,是否超过了当前数组的长度
		System.out.println("element data length is "+elementData.length);
       if(minCapacity - elementData.length > 0)
       {
    	   //扩容为以前容量的1.5倍
    	   int oldCapacity = elementData.length;
    	   int newCapacity = oldCapacity *3/2;
    	   
    	   //内部创建新数组,把elementData的元素拷贝到新数组
    	   elementData = Arrays.copyOf(elementData, newCapacity);
       }
    }
}
