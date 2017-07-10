package com.coding.basic.tree;

import java.util.ArrayList;
import java.util.List;

import com.coding.basic.queue.Queue;


public class BinarySearchTree<T extends Comparable> {
	
	BinaryTreeNode<T> root;
	public BinarySearchTree(BinaryTreeNode<T> root){
		this.root = root;
	}
	public BinaryTreeNode<T> getRoot(){
		return root;
	}
	public T findMin(){
		if(root == null){
			return null;
		}
		return findMin(root).data;
	}
	public T findMax(){
		if(root == null){
			return null;
		}
		return findMax(root).data;
	}
	public int height() {
	    return height(root);
	}
	public int size() {
		return size(root);
	}
	public void remove(T e){
		remove(e, root);
	}
	
	private BinaryTreeNode<T> remove(T x, BinaryTreeNode<T> t){
		if(t == null){
			return t;
		}
		int compareResult = x.compareTo(t.data);
		
		if(compareResult< 0 ){			
			t.left = remove(x,t.left);			
			
		} else if(compareResult > 0){			
			t.right = remove(x, t.right);
			
		} else {
			if(t.left != null && t.right != null){
			
				t.data = findMin(t.right).data;
				t.right = remove(t.data,t.right);
				
			} else{
				t = (t.left != null) ? t.left : t.right;
			}
		}
		return t;
	}
	
	private BinaryTreeNode<T> findMin(BinaryTreeNode<T> p){
		if (p==null){
			return null;
		} else if (p.left == null){
			return p;
		} else{
			return findMin(p.left);
		}
	}
	private BinaryTreeNode<T> findMax(BinaryTreeNode<T> p){
		if (p==null){
			return null;
		}else if (p.right==null){
			return p;
		} else{
			return findMax(p.right);
		}
	}
	private int height(BinaryTreeNode<T> t){
	    if (t==null){
	        return 0;
	    }else {
	        int leftChildHeight=height(t.left);
	        int rightChildHeight=height(t.right);
	        if(leftChildHeight > rightChildHeight){
	        	return leftChildHeight+1;
	        } else{
	        	return rightChildHeight+1;
	        }
	    }
	}
	private int size(BinaryTreeNode<T> t){
		if (t == null){
			return 0;
		}
		return size(t.left) + 1 + size(t.right);
       
   }
	
	public List<T> levelVisit(){		
		List<T> result = new ArrayList<T>();
		if(root == null){
			return result;
		}
		Queue<BinaryTreeNode<T>> queue = new Queue<BinaryTreeNode<T>>();  
		BinaryTreeNode<T> node = root;		
		queue.enQueue(node); 
        while (!queue.isEmpty()) {  
            node = queue.deQueue(); 
            result.add(node.data); 
            if (node.left != null){
                queue.enQueue(node.left);  
            }
            if (node.right != null){
                queue.enQueue(node.right);  
            }
        }   
		return result;
	}
	public boolean isValid(){
		return isValid(root);
	}
	public T getLowestCommonAncestor(T n1, T n2){
		if (root == null){
            return null;
		}
		return lowestCommonAncestor(root,n1,n2);
        
	}
	public List<T> getNodesBetween(T n1, T n2){
		List<T> elements = new ArrayList<>();
		getNodesBetween(elements,root,n1,n2);
		return elements;
	}
	
	public void  getNodesBetween(List<T> elements ,BinaryTreeNode<T> node, T n1, T n2){
		
        if (node == null) {
            return;
        } 
  
        if (n1.compareTo(node.data) < 0) {
        	getNodesBetween(elements,node.left, n1, n2);
        } 
       
        if ((n1.compareTo(node.data) <= 0 )
        		&& (n2.compareTo(node.data) >= 0  )) {
        	elements.add(node.data);            
        } 
        if (n2.compareTo(node.data)>0) {
        	getNodesBetween(elements,node.right, n1, n2);
        }
	}
	private T lowestCommonAncestor(BinaryTreeNode<T> node,T n1, T n2){
		if(node == null){
			return null;
		}
		// 如果n1和n2都比 node的值小， LCA在左孩子
        if (node.data.compareTo(n1) > 0 && node.data.compareTo(n2) >0){
            return lowestCommonAncestor(node.left, n1, n2);
        }
  
        // 如果n1和n2都比 node的值小， LCA在右孩子
        if (node.data.compareTo(n1) < 0 && node.data.compareTo(n2) <0) 
            return lowestCommonAncestor(node.right, n1, n2);
  
        return node.data;
	}
	private boolean isValid(BinaryTreeNode<T> t){
		if(t == null){
			return true;
		}
		if(t.left != null && findMax(t.left).data.compareTo(t.data) >0){
			return false;
		}
		if(t.right !=null && findMin(t.right).data.compareTo(t.data) <0){
			return false;
		}
		if(!isValid(t.left) || !isValid(t.right)){
			return false;
		}
		return true;
	}
}

