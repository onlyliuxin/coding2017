package com.coding.datastructs;
	public class Queue{
		private LinkedList list = new LinkedList();
		
		public void enQueue(Object o){	
			list.add(o);
		}
		
		public Object deQueue(){
			if (list.size()<=0) {
				throw new NullPointerException("Queue���������ݣ����ܽ���ɾ������");
			}
			Object o = list.get(0);
			list.removeFirst();
			return o;
		}
		
		public boolean isEmpty(){
			int count = size();
			if(count<0){
				return true;
			}
			return false;
		}
		
		public int size(){
			return list.size();
		}
	}

