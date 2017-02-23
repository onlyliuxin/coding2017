package com.coding.basic;

/**
 * 二叉树
 	5
   / \
  2   7
 / \  /\
1  4 6  8
 
 1.前序遍历: 5 2 1 4 7 6 8
 2.中序遍历: 1 2 4 5 6 7 8
 3.后序遍历: 1 4 2 6 8 7 5
 */
public class BinarySearchTree<T extends Comparable<? super T>> {
    private BinarySearchTreeNode<T> root;  //根节点
    
    public BinarySearchTreeNode<T> getRoot() {
		return root;
	}

	public void setRoot(BinarySearchTreeNode<T> root) {
		this.root = root;
	}

	/**
     * 二叉树插入节点
     * @param data 节点元素
     * @return 插入的节点
     */
    public BinarySearchTreeNode<T> insert(T data){
        if(root == null){
            root = new BinarySearchTreeNode<T>(data);
            return root;
        }
        //root非空
        BinarySearchTreeNode<T> current = root;
        while(true){
            //当前节点的数据小于data
            if(current.getData().compareTo(data) >= 0){
                if(current.getLeft() != null){
                    current = current.getLeft();
                }else {
                    current.setLeft(new BinarySearchTreeNode<T>(data));
                    return current.getLeft();
                }
            }else {//当前节点数据大于root
                if(current.getRight() != null){
                    current = current.getRight();
                }else {
                    current.setRight(new BinarySearchTreeNode<T>(data));
                    return current.getRight();
                }
            }
        }
    }

    /**
     * 查找元素data
     * @param data 要查找的元素
     * @return 返回true找到、false未找到
     */
    public boolean contains(T data){
        if(root == null){
            return false;
        }
        BinarySearchTreeNode<T> current = root;
        while(true){
            if(current.getData().compareTo(data) > 0){
                if(current.getLeft() != null){
                    current = current.getLeft();
                }else{
                    return false;
                }
            }else if(current.getData().compareTo(data) < 0){
                if(current.getRight() != null){
                    current = current.getRight();
                }else {
                    return false;
                }
            }else {
                return true;
            }
        }
    }
    
    /**
     * 前序遍历递归实现
     * @param n 根节点
     */
    public void preOrder(BinarySearchTreeNode<T> n){
    	System.out.print(n.getData()+" ");
    	if(n.getLeft() != null){
    		preOrder(n.getLeft());
    	}
    	if(n.getRight() != null){
    		preOrder(n.getRight());
    	}
    }
    
    /**
     * 中序遍历递归实现
     * @param n 根节点
     */
    public void midOrder(BinarySearchTreeNode<T> n){
    	if(n.getLeft() != null){
    		midOrder(n.getLeft());
    	}
    	System.out.print(n.getData()+" ");
    	if(n.getRight() != null){
    		midOrder(n.getRight());
    	}
    }
    
    /**
     * 后序遍历递归实现
     * @param n
     */
    public void postOrder(BinarySearchTreeNode<T> n){
    	if(n.getLeft() != null){
    		postOrder(n.getLeft());
    	}
    	if(n.getRight() != null){
    		postOrder(n.getRight());
    	}
    	System.out.print(n.getData()+" ");
    }
    
    
}
