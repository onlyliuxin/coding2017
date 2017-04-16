package test01.tree;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        Tree<String> tree = new Tree();
        tree.addNode(null, "string");
        tree.addNode(tree.getNode("string"), "hello");
        tree.addNode(tree.getNode("string"), "world");
        tree.addNode(tree.getNode("hello"), "sinny");
        tree.addNode(tree.getNode("hello"), "fredric");
        tree.addNode(tree.getNode("world"), "Hi");
        tree.addNode(tree.getNode("world"), "York");
        tree.showNode(tree.root);
        
        System.out.println("end of the test");
	}

}
