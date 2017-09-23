package com.coderising.dp.composite;

public class testComposite {
	public static void main(String[] args) {
		Picture root = new Picture();
		Picture node = new Picture();
		node.addList(new Text());
		node.addList(new Line());
		node.addList(new Square());
		root.addList(node);
		root.addList(new Line());
		root.addList(new Rectangle());
		root.draw();
	}
}
