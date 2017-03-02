package com.louisly.java;
import com.louisly.java.LYArrayList;
import com.louisly.java.LYObject;
import com.louisly.java.LYArrayLink;
import com.louisly.java.LYBinaryTree;

public class main {

	public static void main(String[] args) {
		
//		testBinaryTree();
		testArrayLink();
//		testArrayList();
	}
	
	public static void testBinaryTree() {
		LYBinaryTree tree = new LYBinaryTree();
		tree.addObject(new LYObject(5));
		tree.addObject(new LYObject(7));
		tree.addObject(new LYObject(2));
		tree.addObject(new LYObject(1));
		tree.addObject(new LYObject(6));
		tree.addObject(new LYObject(4));
		tree.addObject(new LYObject(8));
	}
	
	public static void testArrayLink() {
		// ����20��Ԫ��
		LYArrayLink list = new LYArrayLink();
		for (int i = 0; i < 20; i++) {
			LYObject object = new LYObject(i);
			list.addObject(object);
		}
		
		System.out.print("��ǰ�����й���Ԫ�ظ�����" + list.size() + "\n");
		
		// ��ӡĿǰ���ڵ�ÿ��Ԫ��
		for (int i = 0; i < list.size(); i++) {
			LYObject obj = (LYObject)list.get(i);
			System.out.print(obj.i + "\n");
		}
		
		System.out.print("======\n");
		
		// �Ƴ����߸�Ԫ��
		list.removeAtIndex(7);
		
		System.out.print("��ǰ�����й���Ԫ�ظ�����" + list.size() + "\n");
		
		// �ٴ�ӡĿǰ��ʣ����ЩԪ��
		for (int i = 0; i < list.size(); i++) {
			LYObject obj = (LYObject)list.get(i);
			System.out.print(obj.i + "\n");
		}
	}
	
	public static void testArrayList() {
		// ����20��Ԫ��
		LYArrayList list = new LYArrayList();
		for (int i = 0; i < 20; i++) {
			LYObject object = new LYObject(i);
			list.addObject(object);
		}
		
		System.out.print("��ǰ�����й���Ԫ�ظ�����" + list.size() + "\n");
		
		for (int i = 0; i < list.size(); i++) {
			LYObject obj = (LYObject)list.get(i);
			System.out.print(obj.i + "\n");
		}
		
		System.out.print("======\n");
		
		
		list.removeAtIndex(7);
		System.out.print("��ǰ�����й���Ԫ�ظ�����" + list.size() + "\n");
		
		for (int i = 0; i < list.size(); i++) {
			LYObject obj = (LYObject)list.get(i);
			System.out.print(obj.i + "\n");
		}
	}

}
