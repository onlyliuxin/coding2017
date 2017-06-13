package com.coding.DataStructure;

public class Tree {
	private Object data;
	private Tree left;
	private Tree right;

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Tree getLeft() {
		return left;
	}

	public void setLeft(Tree left) {
		this.left = left;
	}

	public Tree getRight() {
		return right;
	}

	public void setRight(Tree right) {
		this.right = right;
	}

	public Tree insert(Object o) {
		if (data == null) {
			setData(o);
		} else {
			Integer i = (Integer) o;
			if (i.compareTo((Integer) data) == -1) {
				if(right == null)
					right = new Tree();
					return right.insert(i);
			} else if (i.compareTo((Integer) data) == 1) {
				if(left == null)
					left = new Tree();
					return left.insert(i);
			}
			return null;
		}
		return null;
	}

}
