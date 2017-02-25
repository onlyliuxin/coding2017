package com.sl.test20170221;

public class LinkedList implements List{
	private Node root;
	int index;
	
	public void addNode(String name){
		if(root == null){
			root = new Node(name);
		}else{
			root.add(name);
		}
	}
	
	
	
	
	class Node{
		Object data;
		Node next;
		
		
		Node(Object data){
			this.data = data;
		}
		//添加节点
		public void add(Object data){
			if(this.next == null){
				this.next = new Node(data);
			}else{
				this.next.add(data);
			}
		}
		//删除节点
		public Object del(int i){
			if(this.next != null){
				index++;
				if(i == index){
					this.next = this.next.next;
					return this.next.data;
				}else{
					this.next.del(i);
				}
			}
			return null;
		}
	
		
		//遍历
		public void traversal(){
			if(this.next != null){
				index++;
				this.next.traversal();
			}
		}
		//指定位置增加
		public void add(int i, Object o){
			if(this.next != null){
				if(i == index){
					Node node = new Node(data);
					node.next = this.next.next;
					this.next = node;
					return ;
				}else{
					this.next.add(i,o);
				}
				index++;
			}
		}
		
		//得到指定的节点
		public Object get(int i){
			if(this.next != null){
				
				if(i == index){
					return this.data;
				}else{
					this.next.get(i);
				}
				index++;
			}
			return null;
		}
		
	}
	
	@Override
	public void add(Object data) {
		if(root == null){
			root = new Node(data);
		}else{
			root.add(data);
		}
	}

	@Override
	public void add(int index, Object o) {
		if(root != null){
			root.add(index, o);
		}
	}

	@Override
	public Object get(int index) {
		if(root.next != null){
			return root.get(index);
		}
		return null;
	}

	@Override
	public Object remove(int index) {
		if(root != null){
			return root.del(index);
		}
		return null;
	}

	@Override
	public int size() {
		if(root != null){
			root.traversal();
		}
		return index;
	}
	
}
