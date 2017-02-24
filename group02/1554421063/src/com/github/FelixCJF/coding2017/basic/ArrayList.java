package com.github.FelixCJF.coding2017.basic;


public class ArrayList implements List {
	
	private int size = 0;
	
	private Object[] elementData = new Object[100];
	
	public void add(Object o){
		//容量增加
		ensureCapacity(size + 1);
		//添加
		elementData[size ++] = o;
	}
	
	public void add(int index, Object o){
		
		//检查是否越界
		rangeCheck(index);
       // 进行扩容检查
       ensureCapacity(size + 1);  
       // 对数组进行复制处理，目的就是空出index的位置插入element，并将index后的元素位移一个位置
       System. arraycopy(elementData, index, elementData, index + 1,
                      size - index);
       // 将指定的index位置赋值为Object o
        elementData[index] = o;
       // 自增一位长度
        size++;
	}
	
	public Object get(int index){
		rangeCheck(index);
		return elementData[index];
	}
	
	public Object remove(int index){
		// 数组越界检查
       rangeCheck(index);
       // 取出要删除位置的元素，供返回使用
       Object oldValue = elementData[index];
       // 计算数组要复制的数量
        int numMoved = size - index - 1;
       // 数组复制，就是将index之后的元素往前移动一个位置
        if (numMoved > 0)
           System. arraycopy(elementData, index+1, elementData, index,
                          numMoved);
       // 将数组最后一个元素置空（因为删除了一个元素，然后index后面的元素都向前移动了，所以最后一个就没用了），好让gc尽快回收
       // 不要忘了size减一
       elementData[--size] = null;
        return oldValue;
	}
	
	public int size(){
		return size;
	}
	
	public Iterator iterator(){
		return new ArrayListIterator();
	}
	
	//内部类，实现Iterator
	private class ArrayListIterator implements Iterator{
		
		private int currentIndex = 0; //当前索引

		public boolean hasNext() {
			if (currentIndex >= size) {
				return false;
			}
			return true;
		}

		public Object next() {
			Object object  = elementData[currentIndex];
			currentIndex ++ ;
			return object;
		}
	}
	//扩容
	public void ensureCapacity( int minCapacity) {
       // 当前数组的长度
        int oldCapacity = elementData .length;
       // 最小需要的容量大于当前数组的长度则进行扩容
        if (minCapacity > oldCapacity) {
          // 扩容
           int newCapacity = oldCapacity + (oldCapacity >> 1);
          // 如果新扩容的数组长度还是比最小需要的容量小，则以最小需要的容量为长度进行扩容
           if (newCapacity < minCapacity)
              newCapacity = minCapacity;
           //数组复制
           Object[] elementData2 = new Object[newCapacity];
           for (int i = 0; i < oldCapacity; i++) {
        	   elementData2[i] = elementData[i];
           }
            elementData = elementData2;
       }
    }
	//检查是否越界
	private void rangeCheck(int index){
		if (index < 0 || index >= this.size) {
			throw new IndexOutOfBoundsException("index :" + index + "size :" + size);
		}
	}
}
