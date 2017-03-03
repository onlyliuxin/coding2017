package com.ikook.basic_data_structure;

/**
 * @author ikook   QQ号码: 935542673
 */
public interface MyIterator {
	
	public boolean hasNext();  // 判断是否有元素没有被遍历
	
	public Object next();  // 返回游标当前位置的元素并将游标移动到下一个位置
	
	public void remove();  // 删除游标左边的元素，在执行完 next 之后该操作只能执行一次

}
