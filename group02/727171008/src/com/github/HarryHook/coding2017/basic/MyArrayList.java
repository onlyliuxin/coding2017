/*
 * created by Harry 2017-2-20 18:53:38
 * 实现简单的ArrayList,具有基本的增删改查功能
 */
package com.github.HarryHook.coding2017.basic;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class MyArrayList implements List 
{	
	private int size = 0;    //数组元素个数
	
	private Object[] elementData = new Object[10];  //初始化数组大小为10
			
	//将元素添加到数组尾部
	public void add(Object o)
	{   //需要判断数组空间是否够用
		ensureCapacity(size + 1);
		elementData[size++] = o;
	}
	//在指定位置添加元素
	public void add(int index, Object o)
	{
		//判断下标记是否越界		
		if (index > size || index < 0)
            throw new IndexOutOfBoundsException(
            "Index: " + index + ", Size: " + size);
		ensureCapacity(size + 1);
		//判断当前位置是否有元素，没有元素添加到当前位置；若有，当前元素及之后元素向右移
		if(elementData[index] == null)
		{
			elementData[index] = o;
		}
		else
		{
			for(int i=elementData.length-1; i>index; i--)  
			{
				elementData[i] = elementData[i-1];
			} 
			elementData[index] = o;
		}  		
		size++;
		
		/*
		   //判断索引位置是否正确
        if (index > size || index < 0)
            throw new IndexOutOfBoundsException(
            "Index: " + index + ", Size: " + size);
        //扩容检测
        ensureCapacity(size+1);  
        /*
         * 对源数组进行复制处理（位移），从index + 1到size-index。
         * 主要目的就是空出index位置供数据插入，
         * 即向右移动当前位于该位置的元素以及所有后续元素。 
         
        System.arraycopy(elementData, index, elementData, index + 1,
                 size - index);
        //在指定位置赋值
        elementData[index] = 0;
        size++;
        
        */
	}
	
	public Object get(int index)
	{	
		//若index超出size应该抛出异常
		if(index >= size)
			 throw new IndexOutOfBoundsException( "Index: " + index + ", Size: " + size);
		return  elementData[index];
		
	}
	
	public Object remove(int index)
	{	//涉及到元素移位
		Object oldValue = elementData[index];
		for(int i=index; i<elementData.length-1; i++)
			elementData[i] = elementData[i+1];		
		elementData[--size] = null;
		
		return oldValue;
	}
	
	//判断是否需要给数组扩容
	public void ensureCapacity(int minCapacity)
	{  
	    
	    int oldCapacity = elementData.length;  
	    if (minCapacity > oldCapacity) 
	    {  
	        //Object oldData[] = elementData;   //防止copyof()执行的过程中新内存或者其他进程分配内存时占用旧内存
	        int newCapacity = (oldCapacity * 3)/2 + 1;  //增加50%+1
	            if (newCapacity < minCapacity)  
	                newCapacity = minCapacity;  
	      // minCapacity is usually close to size, so this is a win:  
	        elementData = Arrays.copyOf(elementData, newCapacity);  
	    }  
	 }
	
	//返回数组的大小
	public int size()
	{
		return size;
	}
	
	public Iterator iterator()
	{
		return new MyArrayListIterator();
	}
	
	private class MyArrayListIterator implements Iterator
	{
		private int cursor = 0;      //记录索引位置         
		public boolean hasNext()
		{
			return cursor != size;    
		}
		public Object next() 
		{  
			  try { 
		                Object next = get(cursor);  
		                cursor++;
		                return next;

	            } catch (IndexOutOfBoundsException e)
			  	{
	                throw new NoSuchElementException();
	            }
		
		}
	}
	
	public static void main(String[] args)
	{
		MyArrayList myArrays = new MyArrayList();
		myArrays.add(3);
		myArrays.add(0, 11);
		myArrays.add(1, 2);
		myArrays.add(3, 5);
		myArrays.add(2, 1);
		myArrays.add(7);
		Print(myArrays);
		
		for(int i = 0; i < 19; i++)
			myArrays.add(i, 55);
		
		System.out.println("获取指定位置元素： " + myArrays.get(2));
		System.out.println("删除指定位置元素： " + myArrays.remove(1));
		System.out.println("当前元素个数：" + myArrays.size());
		
		Print(myArrays);
			
	}
	public static void Print(MyArrayList myArrays)
	{
		Iterator it = myArrays.iterator();	
		System.out.println("对链表中的元素进行打印：");
		while(it.hasNext())
			System.out.print(it.next() + " ");
		System.out.println("");
		System.out.println("当前元素个数： " + myArrays.size()); 

	}
	
}

