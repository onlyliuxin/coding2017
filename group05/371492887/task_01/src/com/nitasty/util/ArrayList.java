package com.nitasty.util;

import java.util.Arrays;
import java.util.Objects;

public class ArrayList implements List {

	// 定义内部常量
	private static final int DEFAULT_CAPACITY = 10;

	private static final Object[] EMPTY_ELEMENTDATA = {};

	private int size;

	private Object[] elementData;

	/**
	 * 
	 * 无参初始化为空数组，节省空间
	 */
	public ArrayList() {
		this.elementData = EMPTY_ELEMENTDATA;
	}

	/**
	 * 
	 * @param initCapacity
	 */
	public ArrayList(int initCapacity) {
		if (initCapacity > 0) {
			elementData = new Object[initCapacity];
		} else if (initCapacity == 0) {
			elementData = EMPTY_ELEMENTDATA;
		} else {
			throw new IllegalArgumentException("非法初始化容量" + initCapacity);
		}

	}

	// TODO
	public ArrayList(List list) {
		list.toArray();

	}

	/**
	 * 容量校验
	 * 
	 * @param minCapacity
	 */
	private void ensureCapacity(int minCapacity) {

		if (elementData.length < minCapacity) {
			grow(minCapacity);
		}

	}

	/**
	 * 容量增长
	 * 
	 * @param minCapacity
	 * @return
	 */
	private void grow(int minCapacity) {
		// 老容量
		int oldCapacity = this.elementData.length;
		// 新容量为老容量的1.5倍
		int newCapacity = oldCapacity + oldCapacity >> 1;
		// 新容量与最小容量比较
		if (newCapacity < minCapacity) {
			newCapacity = minCapacity;
		}
		// 新容量不能大于int最大值
		if (newCapacity > Integer.MAX_VALUE) {
			newCapacity = Integer.MAX_VALUE;
		}
		elementData = Arrays.copyOf(elementData, newCapacity);

	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public boolean contains(Object o) {
		return indexOf(o) >= 0; //不写=会引发奇怪的bug
	}

	@Override
	public boolean add(Object o) {

		ensureCapacity(size + 1);

		elementData[size++] = o;// size比index大1

		return true;
	}

	private void rangeCheck(int index) {
		if (index < 0 || index > size)
			throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
	}

	private String outOfBoundsMsg(int index) {
		return "index:" + index + ", size:" + size;
	}

	@Override
	public boolean add(int index, Object o) {

		rangeCheck(index);
		ensureCapacity(size + 1);
		System.arraycopy(elementData, index, elementData, index + 1, size
				- index);// size出错就炸了
		elementData[index] = o;
		return true;
	}

	@Override
	public boolean addAll(Object[] o) {
		int numNew = o.length;
		ensureCapacity(size + numNew);
		System.arraycopy(o, 0, elementData, size, numNew);
		size += numNew;// size增加
		return numNew != 0;
	}

	@Override
	public boolean addAll(int index, Object[] o) {
		rangeCheck(index);
		int numNew = o.length;
		ensureCapacity(size + numNew);

		int numMoved = size - index;// 经过rangeCheck的index肯定小于size啊
		if (numMoved > 0)
			System.arraycopy(elementData, index, elementData, size + numNew,
					numNew);// 将数组原元素移到后面
		System.arraycopy(o, 0, elementData, index, numNew);// 复制将要添加的元素

		size += numNew;// 不要忘了啊啊啊！！！
		return numNew != 0;
	}

	@Override
	public Object remove(int index) {
		rangeCheck(index);
		Object oldValue = elementData[index];

		int numMoved = size - index - 1;
		if (numMoved > 0)
			System.arraycopy(elementData, index+1, elementData, index,
					numMoved);
		elementData[--size] = null;// Clear to let gc do its work

		return oldValue;
	}

	@Override
	public boolean remove(Object o) {
		int index=this.indexOf(o);
		if(index==-1){
			return false;
		}else{
			this.remove(index);
			return true;
		}
	}

	@Override
	/**
	 * 批量删除list
	 */
	public boolean removeAll(List list) {
		Objects.requireNonNull(list);
		return batchRemove(list,false);
	}
	
	/**
	 * 用于实现removeALl和retainAll
	 * @param list
	 * @param complement
	 * @return
	 */
	private boolean batchRemove(List list, boolean complement) {
		final Object[] elementData=this.elementData;
		int r=0,w=0;
		boolean modified=false;
		try{
			for(;r<size;r++){
				if(list.contains(elementData[r])==complement){
					elementData[w++]=elementData[r];
				}
			}
		}finally{
			if(r!=size){
				System.arraycopy(elementData, r, elementData, w, size-r);// 如果contains发生异常，则把数组中未进行处理的部分copy至已处理部分的后面，modify依然为false
				w+=size-r;
			}
			if(w!=size){
				for(int i=w;i<size;i++){
					elementData[i]=null;// gc
					size=w;//别忘了啊啊啊！！！
					modified=true;
				}
			}
		}
		
		return modified;
	}

	@Override
	public Object get(int index) {
		rangeCheck(index);
		return elementData[index];
	}

	@Override
	public Object set(int index, Object o) {
		rangeCheck(index);
		Object oldValue = elementData[index];
		elementData[index] = o;
		return oldValue;
	}

	@Override
	public int indexOf(Object o) {
		// ArrayList可以装载null
		if (o == null) {
			for (int i = 0; i < size; i++) {
				if (elementData[i] == null) {
					return i;
				}
			}
		} else {
			for (int i = 0; i < size; i++) {
				if (o.equals(elementData[i])) {
					return i;
				}
			}
		}
		return -1;// 没找到
	}

	@Override
	public int lastIndexOf(Object o) {
		// ArrayList可以装载null
		if (o == null) {
			for (int i = size-1; i >= 0; i--) {
				if (elementData[i] == null) {
					return i;
				}
			}
		} else {
			for (int i = size-1; i >= 0; i--) {
				if (o.equals(elementData[i])) {
					return i;
				}
			}
		}
		return -1;// 没找到
	}

	@Override
	public Iterator iterator() {
		return new Itr();
	}

	@Override
	public Object[] toArray() {
		return Arrays.copyOf(elementData, size);//不要直接返回elementData
	}

	@Override
	public void clear() {
		for(int i=0; i<size; i++){
			elementData[i]=null;
		}
		size=0;
	}
	
	private class Itr implements Iterator{
		
		int cursor;
		@Override
		public boolean hasNext() {
			return cursor!=size;
		}

		@Override
		public Object next() {
			int i=cursor;
			rangeCheck(i);
			cursor=i+1;
			return elementData[i];
		}
		
	}

}
