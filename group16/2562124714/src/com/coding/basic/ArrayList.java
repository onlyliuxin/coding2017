package com.coding.basic;

import java.util.Objects;

public class ArrayList implements List {
	
	private int size = 0;

	private int Scale; //每次扩展大小
	
	private Object[] elementData = new Object[100];

	public ArrayList()
	{
		this.Scale = 10;
	}

	public ArrayList(int i)
	{
		this.Scale = i;
	}

	public void add(Object o){
		if (this.size == elementData.length)
		{
			DoEnlage();
		}
		elementData[size] = o;
		this.size++;
	}

	private void DoEnlage()
	{
		if (this.Scale >= 1 && this.Scale <= 10000)
		{
			Object[] NewElementData = new Object[this.elementData.length + this.Scale];
			System.arraycopy(this.elementData,0,NewElementData,0,this.elementData.length);

			this.elementData = NewElementData;
		}

	}

	//index从1开始 位置1，2，3，4，5，6
	public void add(int index, Object o){
		if (this.size == elementData.length)
		{
			DoEnlage();
		}
		int i = 0;
		//遍历赋值
		for(i = this.size; i >= index;i--)
		{
			this.elementData[i] = this.elementData[i - 1];
		}

		this.elementData[i] = o;
		this.size++;

	}
	
	public Object get(int index){
		if (index >= 1 && index <= this.size)
		{
			return this.elementData[index - 1];
		}
		else {
			return null;
		}


	}
	
	public Object remove(int index){
		if (index >= 1 && index <= this.size)
		{
			int i = 0;
			Object DelElement = this.elementData[index - 1];
			for(i = index; i <= this.size; i++)
			{
				this.elementData[i - 1] = this.elementData[i];
			}
			this.elementData[i] = null;
			this.size--;

			return DelElement;

		}
		else {
			return null;
		}
	}
	
	public int size(){
		return this.size;
	}
	
	public Iterator iterator(){
		return null;
	}

	public Object[] ToArray()
	{
		Object [] Array = new Object[this.size];
		if(this.size == 0)
		{
			return new Object[0];
		}

		//使用System.arraycopy()来复制数组是更优的办法 zwj 20170309
		for (int i = 0 ; i < this.size; i ++)
		{
			Array[i] = this.elementData[i];
		}

		return Array;
	}
	
}
