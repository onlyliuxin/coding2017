package com.my.list;

/**
 * 实现ArrayList
 *
 */
public class ArrayList {
	//初始化容量
	private static final int INIT = 5;
	//增长因子
	private static final double GROWTH = 0.5;
	
	//初始化数组
	Object[] baseArr = new Object[INIT];
	//当前下标
	int currentIndex = 0;
	//最大下标
	int maxIndex = INIT-1;
	
	/**
	 * 添加元素
	 * @param obj
	 * @return
	 */
	public Object add(Object obj){
		if(judgeCapacity()){
			baseArr = arrayDilatation(baseArr, GROWTH);
			maxIndex = baseArr.length-1;
		}
		baseArr[currentIndex] = obj;
		++currentIndex;
		return obj;
	}
	
	/**
	 * 指定下标添加元素
	 * @param index
	 * @param obj
	 * @return
	 */
	public Object add(int index , Object obj){
		if (index < 0 || index > currentIndex) {
			throw new IndexOutOfBoundsException();
		}
		Object[] dest = new Object[currentIndex+1];
		System.arraycopy(baseArr, 0, dest, 0, index);
		dest[index] = obj;
		System.arraycopy(baseArr, index, dest, index+1, currentIndex-index);
		++currentIndex;
		baseArr = dest;
		return obj;
	}
	
	/**
	 * 指定下标删除元素 
	 * @param index
	 * @return
	 */
	public Object remove(int index){
		if (index < 0 || index >= currentIndex) {
			throw new IndexOutOfBoundsException();
		}
		Object object = baseArr[index];
		Object[] dest = new Object[currentIndex];
		System.arraycopy(baseArr, 0, dest, 0, index);
		System.arraycopy(baseArr, index+1, dest, index, currentIndex-index-1);
		--currentIndex;
		baseArr = dest;
		return object;
	}
	
	/**
	 * 根据下标获取元素
	 * @param index
	 * @return
	 */
	public Object get(int index){
		
		return baseArr[index];
	}
	
	/**
	 * 获取集合大小
	 * @return
	 */
	public int size(){
		return currentIndex;
	}
	
	/**
	 * 判断容量是否需要增加
	 * @return
	 */
	public boolean judgeCapacity(){
		if(currentIndex > maxIndex){
			return true;
		}
		return false;
	}
	
	/**
	 * 数组扩容
	 * @param objArr
	 * @param groth
	 * @return
	 */
	public static Object[] arrayDilatation(Object[] objArr , double groth){
		   int length = objArr.length;
		   int newLength = (int) (length * groth + length);
		   Object[] baseArr = new Object[newLength];
		   System.arraycopy(objArr, 0, baseArr, 0, length);
		   return baseArr;
	}
	

}















