public class BinaryTreeNode{
	public BinaryTreeNode left;
	public BinaryTreeNode right;
	public int value;
	public BinaryTreeNode(int arg_value){
		value = arg_value;
	}
	public void add(int arg_value){
		if(arg_value<value){
			if(left == null){
				left = new BinaryTreeNode(arg_value);
			}else{
				left.add(arg_value);
			}
		}else if(arg_value>value){
			if(right == null){
				right = new BinaryTreeNode(arg_value);
			}else{
				right.add(arg_value);
			}
		}
	}
}