package my.collection.linearTest;

import my.collection.linear.MyBinaryTreeNode;

public class MyBinaryTreeNodeTest {

	public static void main(String[] args) {
		MyBinaryTreeNode myTree = new MyBinaryTreeNode();
		
		myTree.insert(new Integer(8));
		myTree.insert(new Integer(12));
		myTree.insert(new Integer(6));
		myTree.insert(new Integer(3));
		myTree.insert(new Integer(7));
		
		System.out.println(myTree.getData().toString());	//8
		System.out.println(myTree.getLeft().getData().toString());	//6
		System.out.println(myTree.getLeft().getData().toString());	//3
		System.out.println(myTree.getRight().getData().toString());	//12
		

	}

}
