package com.coding.basic.linklist;

import com.coding.basic.Iterator;
import com.coding.basic.LinkedList;
import com.coding.basic.List;

import java.util.Objects;

public class LinkedListUtil {
	private LinkedList linkedlist = null;

	public  LinkedListUtil(LinkedList linkedlistArgs)
	{
		linkedlist = linkedlistArgs;
	}

	
	/**
	 * 把该链表逆置
	 * 例如链表为 3->7->10 , 逆置后变为  10->7->3
	 */
	public  void reverse(){
		if (linkedlist == null || linkedlist.size() == 1)
		{
			return;
		}

		int middle = linkedlist.size() / 2;
		Object dataobject;

		for (int i = 0; i < middle; i ++)
		{
		  	dataobject = linkedlist.get(i + 1);
		  	linkedlist.set(i + 1, linkedlist.get(linkedlist.size() - i));
		  	linkedlist.set(linkedlist.size() - i, dataobject);
		}

	}
	
	/**
	 * 删除一个单链表的前半部分
	 * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
	 * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10

	 */
	public  void removeFirstHalf(){
		if (linkedlist == null || linkedlist.size() == 1)
		{
			return;
		}

		int middle = linkedlist.size() / 2;

		for (int i = 0; i < middle; i++)
		{
			linkedlist.removeFirst();
		}
		
	}
	
	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * @param i
	 * @param length
	 */
	public  void remove(int i, int length){
		if (linkedlist == null ||  linkedlist.size() < length + i)
		{
			return;
		}

		for (int j = 1; j <= length; j++)
		{
			linkedlist.remove(i + j);
		}
	}
	/**
	 * 假定当前链表和listB均包含已升序排列的整数
	 * 从当前链表中取出那些listB所指定的元素
	 * 例如当前链表 = 11->101->201->301->401->501->601->701
	 * listB = 1->3->4->6
	 * 返回的结果应该是[101,301,401,601]  
	 * @param list
	 */
	public  int[] getElements(LinkedList list){
		if (list == null || list.size() == 0)
		{
			return null;
		}
		int[] result = new int[list.size()];

		for (int i = 0; i < list.size(); i++)
		{
			int index = (int)list.get(i + 1);
			if (linkedlist.get(index) != null)
			{
				result[i] = (int) linkedlist.get(index);
			}
		}

		return result;
	}
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 从当前链表中中删除在listB中出现的元素 

	 * @param list
	 */
	
	public  void subtract(LinkedList list){
		if (list == null || list.size() == 0)
		{
			return;
		}

		int count = 1;
		int i = 1;
		while (true)
		{

			int data = (int)list.get(count);

			if ((int)linkedlist.get(i) > data)
			{
				if (i == 1)
				{
					count++;
				}
				else
				{
					i--;
					continue;
				}
			}
			else if((int)linkedlist.get(i) < data)
			{
				if (i == linkedlist.size())
				{
					count ++;
				}
				else
				{
					i++;
					continue;

				}
			}
			else
			{
				linkedlist.remove(i);
				count++;
			}

			if (count == list.size() + 1) //跳出条件 list 已经被遍历查找完
			{
				break;
			}
		}

	}
	
	/**
	 * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
	 */
	public  void removeDuplicateValues(){
		//每个元素向左右查找 一旦发现一个方向的值不等于该值 立刻停止该方向搜索 搜索2 - size() - 1
		if (linkedlist == null || linkedlist.size() < 2)
		{
			return;
		}

		for (int i = 2; i < linkedlist.size(); i++)
		{
			Object data = (int)linkedlist.get(i);
			//left
			for (int j = i - 1; j > 0; j++)
			{
				if ((int)linkedlist.get(j) == data)
				{
					linkedlist.remove(j);
					continue;
				}
				else
				{
					break;
				}
			}

			//right
			for (int j = i + 1; j < linkedlist.size() + 1; j++)
			{
				if ((int)linkedlist.get(j) == data)
				{
					linkedlist.remove(j);
					continue;
				}
				else
				{
					break;
				}
			}
		}
		
	}
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 试写一高效的算法，删除表中所有值大于min且小于max的元素（若表中存在这样的元素）
	 * @param min
	 * @param max
	 */
	public  void removeRange(int min, int max){
		//遍历到比min大的地方开始删除到比max大的地方终止

		if (linkedlist == null || linkedlist.size() == 0 || max < min)
		{
			return;
		}

		for (int i = 1; i <= linkedlist.size(); i++)
		{
			if ((int)linkedlist.get(i) <= min)
			{
				continue;
			}
			else
			{
				for (int j = i; j <= linkedlist.size();j++)
				{
					if ((int)linkedlist.get(j) <= max)
					{
						linkedlist.remove(j);
					}
					else
					{
						break;
					}
				}
			}

			break;
		}

	}
	
	/**
	 * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
	 * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
	 * @param list
	 */
	public  LinkedList intersection( LinkedList list){
		//寻找list中的最小元素在的地方！ 此处作为起点遍历
		if (list == null || list.size() == 0 || linkedlist == null || linkedlist.size() == 0)
		{
			return null;
		}
		LinkedList C = new LinkedList();

		int count = 1;
		for (int i = 1; i <= linkedlist.size(); i++)
		{
			if ((int)linkedlist.get(i) < (int)list.get(count))
			{
				continue;
			}
			else
			{
				if ((int)linkedlist.get(i) == (int)list.get(count))
				{
					C.add(list.get(count));
					count++;
				}
				else
				{
					count++;
					i--;
				}
			}
			if (count == list.size() + 1)
			{
				break;
			}
		}

		return ;
	}C
}
