package myList;

public class Test {

	public static void main(String[] args) {
		MyBinarySearchTree my=new MyBinarySearchTree();
//		//建立二叉树
		my.insert(5);
		my.insert(2);
		my.insert(7);
		my.insert(1);
		my.insert(6);
		
//		//后序遍历输出
//		my.getData();
		
		//加入4和8
		my.insert(4);
		my.insert(8);
		
		//后序遍历输出
		my.getData();
		
	}

}
