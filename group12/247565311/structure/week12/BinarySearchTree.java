package structure.week12;
public class BinarySearchTree<T extends Comparable> {
	
	BinaryTreeNode<T> root;
	public BinarySearchTree(BinaryTreeNode<T> root){
		this.root = root;
	}
	public BinaryTreeNode<T> getRoot(){
		return root;
	}
	public T findMin(){
		return dfindMin(root);
	}
    private T dfindMin(BinaryTreeNode<T> node){
        if(node.left==null)return node.data;
        return dfindMin(node.left);
    }
    
	public T findMax(){
		return dfindMax(root);
	}
    private T dfindMax(BinaryTreeNode<T> node){
        if(node.right==null) return node.data;
        return dfindMax(node.right);
    }
    
	public int height() {
        return dheight(root);
	}
    private int dheight(BinaryTreeNode<T> node){
        if(node==null) return 0;
        return 1+Math.max(dheight(node.left),dheight(node.right));
    }
    
	public int size() {
		return dsize(root);
	}
    private int dsize(BinaryTreeNode<T> node){
        if(node==null) return 0;
        return 1+dsize(node.left)+dsize(node.right);
    }
    
	public void remove(T e){
		BinaryTreeNode<T> node = find(root,e);
        if(node==null) return;
        if(node.right!=null){
            T min = dfindMin(node.right);
            dremoveY(node.right,min);
            node.data=min;
        }
        if(node.left!=null){
            T max = dfindMax(node.left);
            dremoveY(node.left,max);
            node.data=max;
        }
        dremoveY(root,e);
	}
    private BinaryTreeNode<T> find(BinaryTreeNode<T> node,T e){
        if(node==null)return null;
        if(node.data==e) return node;
        BinaryTreeNode<T> left = find(node.left,e);
        if(left!=null) return left;
        BinaryTreeNode<T> right = find(node.right,e);
        return right;
    }
    private void dremoveY(BinaryTreeNode<T> node,T e){
        if(node==null)return;
        if(node.left != null){
            if(node.left.data==e) {
                node.left = null;
                return;
            }
        }
        if(node.right != null){
            if(node.right.data==e){
                node.right=null;
                return;
            }
        }
        dremoveY(node.left,e);
        dremoveY(node.right,e);
    }
	
}