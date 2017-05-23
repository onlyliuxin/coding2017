package tree;

import java.util.ArrayList;
import java.util.List;
import queue.Queue;

public class BinarySearchTree<T extends Comparable> {
	
	BinaryTreeNode<T> root;
	
	public BinarySearchTree(BinaryTreeNode<T> root){
		this.root = root;
	}
	
	public BinaryTreeNode<T> getRoot(){
		return root;
	}
	
	public T findMin(){
		
		BinaryTreeNode<T> node = getRoot();
		while(node.getLeft() != null) {
			node = node.getLeft();
		}
		
		return node.getData();
	}
	
	public T findMax(){
		
		BinaryTreeNode<T> node = getRoot();
		while(node.getRight() != null) {
			node = node.getRight();
		}
		
		return node.getData();
	}
	
	public int height() {
		
	    return getHeight(getRoot());
	}
	
	private int getHeight(BinaryTreeNode<T> node) {
		
		if (node == null) {
			return 0;
		}
		
		return Math.max(getHeight(node.getLeft()), getHeight(node.getRight())) + 1;
		
	}
	
	public int size() {
		
		BinaryTreeNode<T> node = getRoot();
		List<T> nodes = BinaryTreeUtil.inOrderVisit(node);
		
		return nodes.size();
	}
	
	
	
	private BinaryTreeNode<T> find(BinaryTreeNode<T> root, T e) {

		BinaryTreeNode<T> result = null;
		
		if (root == null) {
			return null;
		} else {
			int compare = root.data.compareTo(e);
			if (compare == 0) {
				
				return result;
				
			} else {
				if (compare > 0) {
					
					BinaryTreeNode<T> r1 = find (root.getLeft(),e);
					if (r1 != null) {
						result = r1;
					}
					
				} else {
					BinaryTreeNode<T> r2 = find(root.getRight(),e);
					if (r2 != null) {
						result = r2;
					}
				}
			}
		}
		
		return result;
	}
	
	
	public List<T> levelVisit(){
		BinaryTreeNode<T> root = this.root;
		
		List<T> result = new ArrayList<T>();
		
		Queue<BinaryTreeNode<T>> queue = new Queue<>();
		
		if (root == null) {
			return result;
		}
		
		queue.enQueue(root);
		
		while(!queue.isEmpty()) {
			BinaryTreeNode<T> curr = queue.deQueue();
			result.add(curr.data);
			
			
			if (curr.getLeft() != null) {
				queue.enQueue(curr.getLeft());
			}
			if (curr.getRight() != null) {
				queue.enQueue(curr.getRight());
			}
			
		}
		
		return result;
	}
	
	
	public boolean isValid(){
		
		return isValidBST(root);
	
	}
	
	
    boolean firstCompare = true;
    T prev = null;  
  
    public boolean isValidBST(BinaryTreeNode<T> root) {  
        if(root == null)  
            return true;  
        return isValidBST(root.left) && compare(root.data) && isValidBST(root.right);  
    }  
      
    public boolean compare(T e){
    	
        if(firstCompare){  
            firstCompare = false;  
            prev = e;  
            return true;  
        }
        
        if(e.compareTo(prev) <= 0) {
        	return false;  
        }
        
        prev = e;  
        return true;  
    }  

	public T getLowestCommonAncestor(T n1, T n2){

		int compare = n1.compareTo(n2);
		if (compare == 0) {
			throw new RuntimeException("Arguments could not be the same.");
		}
		
		lastAncestor = null;
		find = 0;
		
		T min = compare > 0  ? n2 : n1;
		T other = compare > 0 ? n1 : n2;
		System.out.println(min + " is and other is  " + other);
		findCommonAncestor(root,min,other);
		if (find == 2) {
			return lastAncestor;
		}

		return null;
		
	}
	
	private T lastAncestor = null;
	private int find = 0;
	private void findCommonAncestor(BinaryTreeNode<T> curr, T min, T other) {
		
		if (curr.data.compareTo(min) == 0 || curr.data.compareTo(other) == 0) {
			find++;
			return;
		}
		
		if (curr.data.compareTo(other) > 0) {
			if (curr.getLeft() != null) {
				findCommonAncestor(curr.getLeft(), min, other);
			}
			return;
		}
		
		if (curr.data.compareTo(min) > 0 && curr.data.compareTo(other) < 0) {
			lastAncestor = curr.data;
			if (curr.getLeft() != null) {
				findCommonAncestor(curr.getLeft(), min, other);
			}
			if (curr.getRight() != null) {
				findCommonAncestor(curr.getRight(), min, other);
			}
			return;
		}
		
		if (curr.data.compareTo(min) < 0) {
			if (curr.getRight() != null) {
				findCommonAncestor(curr.getRight(), min, other);
			}
			return;
		}
		
	}

	
	private void insertNodeBetween(BinaryTreeNode<T> curr, T min, T other, List<T> result) {
		
		if (curr.data.compareTo(min) == 0 || curr.data.compareTo(other) == 0) {
			find++;
			return;
		}
		
		if (curr.data.compareTo(other) > 0) {
			System.out.println("1 case " + curr.data);
			if (curr.getLeft() != null) {
				insertNodeBetween(curr.getLeft(), min, other,result);
			}
			return;
		}
		
		if (curr.data.compareTo(min) > 0 && curr.data.compareTo(other) < 0) {
			result.add(curr.data);
			if (curr.getLeft() != null) {
				insertNodeBetween(curr.getLeft(), min, other,result);
			}
			if (curr.getRight() != null) {
				insertNodeBetween(curr.getRight(), min, other,result);
			}
			return;
		}
		
		if (curr.data.compareTo(min) < 0) {
			System.out.println("2 case " + curr.data);
			if (curr.getRight() != null) {
				insertNodeBetween(curr.getRight(), min, other,result);
			}
			return;
		}
		
	}

	
	public List<T> getNodesBetween(T n1, T n2){
		
		List<T> result = new ArrayList<T>();
		
		int compare = n1.compareTo(n2);
		if (compare == 0) {
			throw new RuntimeException("Arguments could not be the same.");
		}
		
		find = 0;
		
		T min = compare > 0  ? n2 : n1;
		T other = compare > 0 ? n1 : n2;

		insertNodeBetween(root,min,other,result);
		if (find == 2) {
			return result;
		}

		return null;
	}
	
	
	
	public void remove(T e){
		
		BinaryTreeNode<T> root = getRoot();
		BinaryTreeNode<T> node = find(root,e);
		if (node == null) {
			return;
		}
		
		BinaryTreeNode<T> pNode = getRoot();
		while(node.getData() != e) {
			pNode = node;
			if ((int)node.getData() - (int)e > 0 ) {
				node = node.getLeft();
			} else {
				node = node.getRight();
			}
		}
		
		boolean left = false;

		if ((int)pNode.getLeft().getData() == (int)e) {
			left = true;
		}
			
		if (getHeight(node) == 1) {
			if (left) {
				pNode.setLeft(null);
			} else {
				pNode.setRight(null);
			}
			return;
		}

		
		int leftH = getHeight(node.getLeft());
		int rightH = getHeight(node.getRight());
		
		if (leftH >= rightH) {
			if (left) {
				pNode.setLeft(node.getLeft());		
			} else {
				pNode.setRight(node.getLeft());
			}
			
			if (node.getRight() != null) {
				System.out.println("1 ok");
				BinaryTreeNode<T> r = node.getLeft();
				while(r.getRight() != null) {
					r = node.getRight();
				}
				r.setRight(node.getRight());
				
			}

		} else {
			if (left) {
				pNode.setLeft(node.getRight());
			} else {
				pNode.setRight(node.getRight());
			}
			
			if (node.getLeft() != null) {
				System.out.println("2 ok");
				BinaryTreeNode<T> r = node.getRight();
				while(r.getLeft() != null) {
					r = node.getLeft();
				}
				r.setLeft(node.getLeft());
				
			}
			
		}
		
		
		
		
	}
	
}

