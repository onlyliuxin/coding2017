package myList;

public class Test {

	public static void main(String[] args) {
		MyBinarySearchTree my=new MyBinarySearchTree();
//		//����������
		my.insert(5);
		my.insert(2);
		my.insert(7);
		my.insert(1);
		my.insert(6);
		
//		//����������
//		my.getData();
		
		//����4��8
		my.insert(4);
		my.insert(8);
		
		//����������
		my.getData();
		
	}

}
