/*�������ƣ�
 * ԭ�ļ����ƣ�
 * Ҫ�㣺
 * 1. ʵ�ֻ��������ݽṹ�ࣺ������

 */
public class BinaryTreeNode_self<T> {
	private T data;
	private BinaryTreeNode_self<T> left;
	private BinaryTreeNode_self<T> right;
	
	//��ȡ�ڵ�����
	public T getData(){
		return data;
	}
	
	//���ýڵ�����
	public void setData(T item){
		this.data=item;
	}
	
	//��ȡ��ڵ������
	public BinaryTreeNode_self<T> getLeft(){
		return left;
	}
	
	//������ڵ������
	public void setLeft(BinaryTreeNode_self<T> left){
		this.left=left;
	}
	
	//��ȡ�ҽڵ������
	public BinaryTreeNode_self<T> getRight(){
		return right;
	}
	
	//�����ҽڵ������
	public void setRight(BinaryTreeNode_self<T> right){
		this.right=right;
	}
	
	//���ӽڵ�(��֤��ڵ�<���ڵ�<�ҽڵ�)
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
