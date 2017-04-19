package com.coding.basic;

import java.util.Queue;
import java.util.Stack;

import java.util.LinkedList;

/**
 二叉树
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
    private BinarySearchTreeNode<T> root;  //root node
    
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
                    BinarySearchTreeNode<T> child = new BinarySearchTreeNode<T>(data);
                    current.setLeft(child);
                    child.setParent(current);
                    return child;
                }
            }else {//当前节点数据大于root
                if(current.getRight() != null){
                    current = current.getRight();
                }else {
                    BinarySearchTreeNode<T> child = new BinarySearchTreeNode<T>(data);
                    current.setRight(child );
                    child.setParent(current);
                    return child;
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
     * 前序遍历递归实现  根节点 左子树 右子树
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
     * 中序遍历递归实现   左子树 根节点 右子树
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
     * 后序遍历递归实现  左子树 右子树 根节点
     * @param n 根节点
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

    /**
     * 非递归实现前序遍历
     */
    public void preOrderWithoutRecursion(){
        if(root == null){ //根节点为空
            return;
        }

        Stack<BinarySearchTreeNode<T>> stack = new Stack<>();  //存放未执行完的节点
        stack.push(root);  //首先push根节点
        BinarySearchTreeNode<T> current = null;

        while(!stack.isEmpty()){    //栈内还有节点
            current = stack.peek(); //得到栈顶节点
            if(current.getState() == 0){
                System.out.print(current.getData() + " ");  //打印数据
                current.setState(1);
            }else if(current.getState() == 1){
                if(current.getLeft() != null){
                    stack.push(current.getLeft());
                }
                current.setState(2);  //确认是否有左子树
            }else if(current.getState() == 2){
                if(current.getRight() != null){
                    stack.push(current.getRight());
                }
                current.setState(3);  //确认是否有右子树
            }else if(current.getState() == 3){
                stack.pop();          //删除栈顶节点(该节点已经执行完所有程序)
                current.setState(0);
            }
        }
    }

    /**
     * 非递归实现中序遍历
     */
    public void midOrderWithoutRecursion(){
        if(root == null){ //根节点为空
            return;
        }

        Stack<BinarySearchTreeNode<T>> stack = new Stack<>();  //存放未执行完的节点
        stack.push(root);  //首先push根节点
        BinarySearchTreeNode<T> current = null;

        while(!stack.isEmpty()){    //栈内还有节点
            current = stack.peek(); //得到栈顶节点
            if(current.getState() == 0){
                if(current.getLeft() != null){
                    stack.push(current.getLeft()); //确认是否有左子树
                }
                current.setState(1);
            }else if(current.getState() == 1){
                System.out.print(current.getData() + " ");  //打印数据
                current.setState(2);
            }else if(current.getState() == 2){
                if(current.getRight() != null){
                    stack.push(current.getRight());
                }
                current.setState(3);  //确认是否有右子树
            }else if(current.getState() == 3){
                stack.pop();          //删除栈顶节点
                current.setState(0);
            }
        }
    }

    /**
     * 非递归实现后序遍历
     */
    public void postOrderWithoutRecursion(){
        if(root == null){ //根节点为空
            return;
        }

        Stack<BinarySearchTreeNode<T>> stack = new Stack<>();  //存放未执行完的节点
        stack.push(root);  //首先push根节点
        BinarySearchTreeNode<T> current = null;

        while(!stack.isEmpty()){    //栈内还有节点
            current = stack.peek(); //得到栈顶节点
            if(current.getState() == 0){
                if(current.getLeft() != null){
                    stack.push(current.getLeft());
                }
                current.setState(1);
            }else if(current.getState() == 1){
                if(current.getRight() != null){
                    stack.push(current.getRight());
                }
                current.setState(2);  //确认是否有左子树
            }else if(current.getState() == 2){
                System.out.print(current.getData() + " ");  //打印数据
                current.setState(3);  //确认是否有右子树
            }else if(current.getState() == 3){
                stack.pop();          //删除栈顶节点
                current.setState(0);
            }
        }
    }

    /**
     * 层次遍历
        1.先根结点入队列
        2.从队列中取出一个元素
        3.访问该元素的节点
        4.若该元素所指节点的左右子节点非空，则将左右孩子节点分别按照指针顺序入栈
     */
    public void traveralByLevel(BinarySearchTreeNode<T> n){
        if(n == null) return;

        Queue<BinarySearchTreeNode<T>> queue = new LinkedList<BinarySearchTreeNode<T>>();
        queue.offer(n);  //入队列

        while(!queue.isEmpty()){
            BinarySearchTreeNode<T> node = queue.poll();  //出队列
            System.out.print(node.getData() + " ");
            if(node.getLeft() != null) queue.offer(node.getLeft());
            if(node.getRight() != null) queue.offer(node.getRight());
        }
    }

    //删除某个节点n
    public void delete(BinarySearchTreeNode<T> n){
        BinarySearchTreeNode<T> p = n.getParent();  //节点的父节点
        BinarySearchTreeNode<T> child;  //节点的子节点

        //该节点没有任何子节点。// 叶子结点，直接删除即可。要考虑待删除结点是root的情况。
        if(n.getLeft()==null && n.getRight()==null){
            //该节点是根节点
            if(n == root){
                root = null;
                return ;
            }
            //非根节点
            if(n == p.getLeft()){
                p.setLeft(null);
            }else if(n == p.getRight()){
                p.setRight(null);
            }
        }

        // 内部结点，把它的后继的值拷进来，然后递归删除它的后继。
        else if(n.getLeft()!=null && n.getRight()!=null){
            BinarySearchTreeNode<T> next = successor(n);  //找到n的中序后继节点
            n.setData(next.getData());
            delete(next);  //中序后继节点
        }

        //只有一个孩子的结点，把它的孩子交给它的父结点即可
        else {
            if(n.getLeft() != null){ //得到子节点
                child = n.getLeft();
            }else {
                child = n.getRight();
            }

            if(n == root){  // n是根节点的情况
                child.setParent(null);
                root = child;
                return;
            }
            //非根节点
            if(n == p.getLeft()){
                p.setLeft(child);
                child.setParent(p);
            }else{
                p.setRight(child);
                child.setParent(p);
            }

        }
    }

    //找到n的中序后继节点
    public BinarySearchTreeNode<T> successor(BinarySearchTreeNode<T> n){
            if( n == null) return null;
            if( n.getRight() == null ) return null;
            return findMin(n.getRight());
    }

    //查找n树的最小值
    public BinarySearchTreeNode<T> findMin(BinarySearchTreeNode<T> n){
        BinarySearchTreeNode<T> current = n;
        while(current.getLeft() != null){
            current = current.getLeft();
        }
        return current;
    }

    //查找n树的最大值
    public BinarySearchTreeNode<T> findMax(BinarySearchTreeNode<T> n){
        BinarySearchTreeNode<T> current = n;
        while(current.getRight() != null){
            current = current.getRight();
        }
        return current;
    }

    /*
      求树的高度(利用后序遍历)
     */
    public int postOrderGetHeight(BinarySearchTreeNode<T> n){
        int hL = 0, hR = 0, maxH = 0;

        if(n != null){
            hL = postOrderGetHeight(n.getLeft()); //求左子树深度
            hR = postOrderGetHeight(n.getRight()); //求右子树深度
            maxH = hL> hR? hL : hR ; //求左右子树深度最大的那个
            return (maxH+1);//返回树的深度
        }
        return 0;  //空树返回0
    }




}
