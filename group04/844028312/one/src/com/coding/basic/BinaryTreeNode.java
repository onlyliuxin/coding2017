package com.coding.basic;

public class BinaryTreeNode {
	
	private Object data;
	private BinaryTreeNode left;
	private BinaryTreeNode right;
	
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public BinaryTreeNode getLeft() {
		return left;
	}
	public void setLeft(BinaryTreeNode left) {
		this.left = left;
	}
	public BinaryTreeNode getRight() {
		return right;
	}
	public void setRight(BinaryTreeNode right) {
		this.right = right;
	}
	
	public BinaryTreeNode insert(Object o){
			if(data==null){		//�жϸ��ڵ��Ƿ�Ϊ��
				data=o;
				left=null;
				right=null;
			}
			else{
				int insertData=(int) o;
				int nowData=(int) data;
				if(left==null || right==null){				//1.left==null right==null,2.left!=null,right==null,3.left==null.right!=null
					if(nowData>=insertData && left==null){	//�жϲ�����С����һ���ڵ�,left==null
						left=new BinaryTreeNode();
						left.data=o;
						left.left=null;
						left.right=null;
					}
					else if(insertData>nowData && right==null){  //�жϲ�����������һ���ڵ�,==null
						right=new BinaryTreeNode();
						right.data=o;
						right.left=null;
						right.right=null;
					}
					else{
						BinaryTreeNode treeNode=null;			//��¼�ȽϽڵ�
						if(nowData>=insertData ){				//�����ǰ���ڵ����ݴ�������������
							treeNode=left;						//�ȽϽڵ�Ϊ��ڵ�
						}
						else{
							treeNode=right;						//����Ϊ�ҽڵ�
						}
						BinaryTreeNode tempNode=null;			//��ʱ�ڵ㣬���ڼ�¼���ȽϽڵ����ڵ���ҽڵ�Ϊ��ʱ��¼�ȽϽڵ�
						while(treeNode!=null){
							nowData=(int) treeNode.data;		//���ĵ�ǰ��ֵ
							if(insertData<=nowData){			//�����ǰ���ڵ����ݴ�������������
								tempNode=treeNode.left;			//��ʱ�ڵ�Ϊ��ڵ�
							}
							else{
								tempNode=treeNode.right;		//����Ϊ�ҽڵ�
							}
							if(tempNode==null){
								tempNode=treeNode;				//��¼�ȽϽڵ�
								if(insertData<=nowData){		//�����ǰ���ڵ����ݴ�������������
									treeNode=treeNode.left;		//�ȽϽڵ�Ϊ��ڵ�
								}
								else{
									treeNode=treeNode.right;	//����Ϊ�ҽڵ�
								}
							}
							else{
								treeNode=tempNode;				//��ʱ�ڵ㲻Ϊ��ʱ���ȽϽڵ㸳ֵΪ��ʱ�ڵ�
							}
						}
						if(treeNode==null){						//���ȽϽڵ�Ϊ��ʱ
							treeNode=new BinaryTreeNode();		//�½�����ڵ�
							treeNode.data=o;
							treeNode.left=null;
							treeNode.right=null;
							int upData=(int) tempNode.data;
							if(insertData<=upData){				//����һ���ڵ�����ݴ��ڲ���ڵ������ʱ
								tempNode.left=treeNode;			//��һ���ڵ����ڵ㸳�����ڵ�
							}
							else{
								tempNode.right=treeNode;
							}
						}
					}
				}
				else{	//left!=null&&right!=null
					BinaryTreeNode treeNode=null;	//�������ж�һ��
					if(nowData>=insertData ){
						treeNode=left;
					}
					else{
						treeNode=right;
					}
					BinaryTreeNode tempNode=null;
					while(treeNode!=null){
						nowData=(int) treeNode.data;
						if(insertData<=nowData){
							tempNode=treeNode.left;
						}
						else{
							tempNode=treeNode.right;
						}
						if(tempNode==null){
							tempNode=treeNode;
							if(insertData<=nowData){
								treeNode=treeNode.left;
							}
							else{
								treeNode=treeNode.right;
							}
						}
						else{
							treeNode=tempNode;
						}
					}
					if(treeNode==null){
						treeNode=new BinaryTreeNode();
						treeNode.data=o;
						treeNode.left=null;
						treeNode.right=null;
						int upData=(int) tempNode.data;
						if(insertData<=upData){
							tempNode.left=treeNode;
						}
						else{
							tempNode.right=treeNode;
						}
					}
				}
			}
		return  this;
	}
	
	
	
}
