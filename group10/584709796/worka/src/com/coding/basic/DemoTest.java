package com.coding.basic;



public class DemoTest {

//1.数组测试	
	public void Test_ArrayList(){
		ArrayList array=new ArrayList();
		//0.末尾插入
		array.add(1);
		array.add(2);
		array.add(3);
		array.add(4);
		array.add(5);
		array.add(6);
		System.out.println("数组原始数据");
		array.printArray();
		
		//1.索引插入
		array.add(2,22);
		System.out.println("索引插入遍历");
		array.printArray();
		System.out.println("数组长度"+array.getSize());
		
		//2.索引获取元素
		System.out.println("2.索引获取元素");
		System.out.println(array.get(4));
		
		//3.按索引移除元素
		System.out.println("3.按索引移除元素");
		array.remove(1);
		array.printArray();
	}

//2.单链表测试	
	public void Test_LinkedList(){
	
		LinkedList LinkList=new LinkedList();
		//1.头插法
		System.out.println("线性表原始数据");
		LinkList.add(0);
		LinkList.add(1);
		LinkList.add(2);
		LinkList.add(3);
		LinkList.add(4);
		LinkList.add(5);
		LinkList.PrintLinkList();
		
		//2.索引插入
		/*System.out.println("2.索引插入");
		LinkList.add(2,11);
		LinkList.PrintLinkList();*/
		
		//3.索引获取元素
		/*System.out.println("3.索引获取元素");
		LinkList.get(1);*/
		
		//4.索引移除元素
		System.out.println("4.索引移除元素");
		LinkList.remove(2);
		LinkList.PrintLinkList();
		System.out.println("linklist长度：	"+LinkList.size());
	}
	
	public static void main(String[] args) {
		DemoTest Dt=new DemoTest();
//		Dt.Test_ArrayList();
		Dt.Test_LinkedList();
		
	}
}
