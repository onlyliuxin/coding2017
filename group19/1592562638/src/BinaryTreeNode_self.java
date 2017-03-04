/*范例名称：
 * 原文件名称：
 * 要点：
 * 1. 实现基本的数据结构类：二叉树

 */
public class BinaryTreeNode_self<T> {
	private T data;
	private BinaryTreeNode_self<T> left;
	private BinaryTreeNode_self<T> right;
	
	//获取节点数据
	public T getData(){
		return data;
	}
	
	//设置节点数据
	public void setData(T item){
		this.data=item;
	}
	
	//获取左节点二叉树
	public BinaryTreeNode_self<T> getLeft(){
		return left;
	}
	
	//设置左节点二叉树
	public void setLeft(BinaryTreeNode_self<T> left){
		this.left=left;
	}
	
	//获取右节点二叉树
	public BinaryTreeNode_self<T> getRight(){
		return right;
	}
	
	//设置右节点二叉树
	public void setRight(BinaryTreeNode_self<T> right){
		this.right=right;
	}
	
	//增加节点(保证左节点<根节点<右节点)
	public BinaryTreeNode_self<T> insert(T item){
		Comparable<T> co=(Comparable<T>)item;
		Comparable coData=(Comparable)data;
		BinaryTreeNode_self result=null;
		if(co.compareTo(data)>0){
			if(right==null){
				right=new BinaryTreeNode_self<>();
				right.data=item;
				result=right;
				return result;
			}
			else{
				right.insert(item);
			}
		}
		else{
			if(left==null){
				left=new BinaryTreeNode_self<>();
				left.data=item;
				result=left;
				return result;
			}
			else{
				left.insert(item);
			}
		}
		return result;
	}
}
