package data_structure;



public class MyBinaryTree {

	private BinaryTreeNode root;
	private  int size;
	
	
	
	public void add(int key,Object o) {
		size++;
		BinaryTreeNode target=null;
		final BinaryTreeNode parent=root;
		final BinaryTreeNode newnode=new BinaryTreeNode(key,o,null,null,null);
		if(parent==null)
			root=newnode;
		else{
			target=compareKey(key,parent);
		
		   if (key <  target.key) {  
			   target.left = newnode;  
	            newnode.top =  target;  
	        } else if(key >  target.key){  
	        	 target.right = newnode;  
	            newnode.top = target;  
	        }  
	        else{
	        	target.data=o;
	        	size--;
	        }
		
		}
	
	}
	public Object get(int key){
		BinaryTreeNode target=null;
		target=search( key);
		if(target==null)
			return null;
		else
			return target.data;
	}
	private BinaryTreeNode search(int key){
		BinaryTreeNode target=null;
		final BinaryTreeNode parent=root;
		if(parent==null)
			return null;
		
		else
			target=compareKey(key,parent);
		if (key ==  target.key) { 
			  
	       return target;
		}
		return null;
	}
	public Object remove(int key){
		BinaryTreeNode replace=null;
		BinaryTreeNode target=null;
		BinaryTreeNode oldnode=null;
		
		target=search( key);
		if(target==null)
			return null;
		else
		{
			oldnode=target;
			if(target.left==null&&target.right==null){
			
				changeParent( target,null);
					target=null;
			}
			else if(target.left!=null&&target.right==null){
			//	replace=next(target.left);
			//	target=replace;
			//	changeParent(target,replace);
			//	changeChild(target, replace);
			//	changeParent(replace,null);
			//	target=null;
				
				replace=target.left;
				changeParent(target,replace);
				replace.top=target.top;
				target=null;
			}
			else if(target.left==null&&target.right!=null){
//				replace=prev(target.right);
//				target=replace;
//				changeParent(target,replace);
//				changeChild(target, replace);
//				changeParent(replace,null);
//				replace=null;
				
				replace=target.right;
				changeParent(target,replace);
				replace.top=target.top;
				target=null;
			}
			else if(target.left!=null&&target.right!=null){
				int prev=prev(target.right).key;
				int next=next(target.left).key;
				if((next-key)>(key-prev))
					replace=prev(target.right);
				else
					replace=next(target.left);
				target=replace;
				changeParent(target,replace);
				changeChild(target, replace);
				changeParent(replace,null);
				replace=null;
			}
		}
		size--;
		return oldnode.data;
	}
	private void changeParent(BinaryTreeNode target,BinaryTreeNode child){
		BinaryTreeNode targetparent=null;
		targetparent=target.top;
		if(targetparent.key>target.key)
			targetparent.left=child;
		else
			targetparent.right=child;
		
	}
	private void changeChild(BinaryTreeNode target,BinaryTreeNode parent){
		BinaryTreeNode targetleftchild=null;
		BinaryTreeNode targetrightchild=null;
		targetleftchild=target.left;
		targetrightchild=target.right;
		if(targetleftchild!=null)
			targetleftchild.top=parent;
		if(targetrightchild!=null)
			targetrightchild.top=parent;
		
	}
	//找到前驱节点
	private BinaryTreeNode prev(BinaryTreeNode target){
	//	BinaryTreeNode prev=null;
		while(target.left!=null){
			target=target.left;
		}
		return target;
		
	}
	//找到后驱节点
	private BinaryTreeNode next(BinaryTreeNode target){
//		BinaryTreeNode next=null;
		while(target.right!=null){
			target=target.right;
		}
		return target;
		
	}
	public int size(){
		
		return size;
	}
	private BinaryTreeNode compareKey(int key ,BinaryTreeNode node) {
		BinaryTreeNode parent=node;
		   while (parent != null) {  
			
			     if (key < parent.key&&parent.left!=null) {  
			    	 parent = parent.left;  
			     } else if (key > parent.key&&parent.right!=null) {  
			    	 parent = parent.right;  
			     } else {  
			                return parent;
			    	
			     }  
		    }  
		   return parent;
		   
		
	}
	

	
	
	private static class BinaryTreeNode{
		 Object data;
		 int key;
		 BinaryTreeNode left;
		 BinaryTreeNode right;
		 BinaryTreeNode top;
		 public BinaryTreeNode(int key,Object o, BinaryTreeNode top, BinaryTreeNode left,BinaryTreeNode right){
			 this.key=key;
			 this.data=o;
			 this.left=left;
			 this.right=right;
			 this.top=top;
					 
		 }
		
		
	}
}
