package com.coding.basic;

import java.util.NoSuchElementException;

public class ArrayList implements List
{

	private int size = 0;

	private Object[] elementData = new Object[3];

	public void add(Object o)
	{
		if (elementData.length == size)
		{
			expand();
		}
		elementData[size++] = o;
	}

	private void expand()
	{
		Object[] newDatas = new Object[size * 3 / 2 + 1];
		System.arraycopy(elementData, 0, newDatas, 0, size);
		elementData = newDatas;
		System.out.println("expand: from :" + size + " to " + size * 2);

	}

	public void add(int index, Object o)
	{
		if (index > size || index < 0)
		{
			throw new IndexOutOfBoundsException("index=" + index + " , size=" + size);
		}
		if (elementData.length == size)
		{
			expand();
		}

		Object[] tmps = new Object[size - index];
		System.arraycopy(elementData, index, tmps, 0, size - index);
		elementData[index] = o;
		System.arraycopy(tmps, 0, elementData, index + 1, size - index);

		size++;
	}

	public Object get(int index)
	{
		if (index >= size || index < 0)
		{
			throw new IndexOutOfBoundsException("index=" + index + " , size=" + size);
		}

		return elementData[index];
	}

	public Object remove(int index)
	{
		if (index >= size || index < 0)
		{
			throw new IndexOutOfBoundsException("index=" + index + " , size=" + size);
		}
		Object rt = elementData[index];

		Object[] tmps = new Object[size - index - 1];
		System.arraycopy(elementData, index + 1, tmps, 0, size - index - 1);
		System.arraycopy(tmps, 0, elementData, index, size - index - 1);
		elementData[--size] = null;

		return rt;
	}

	public int size()
	{
		return size;
	}

	public Iterator iterator()
	{
		return new Iterator(){
			int pos =0;
			@Override
			public boolean hasNext()
			{
				if(pos < size)
				{
					return true;
				}
				return false;
			}

			@Override
			public Object next()
			{
				if(pos < size)
				{
					throw new NoSuchElementException();
				}
				return elementData[pos++];
			}};
	}

}


