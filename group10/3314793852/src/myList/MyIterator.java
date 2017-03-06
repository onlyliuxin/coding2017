	
	package myList;
	
	public class MyIterator {
		
		private Object aData;
		private int i=0;
		private int l=0;
		MyLinkedList.Node node;
		public MyIterator(Object aDate){
			this.aData=aDate;
		}
		
		public boolean hasNext(){
			if(aData instanceof MyArrayList){//MyArrayList的Iterator
				
				Object[] arr=((MyArrayList) aData).getArr();
				int a=((MyArrayList)aData).size();
				return a>i;
			}
			else{//MyLinkedList的Iterator
				node=((MyLinkedList)aData).getHeadNode();//获得头节点
				int a=((MyLinkedList)aData).size();
				return a>l;
			}
			
			
		}
		public Object next(){
			if(aData instanceof MyArrayList){//MyArrayList的Iterator
				
				Object[] arr=((MyArrayList) aData).getArr();
				return arr[++i];
			}
			else{//MyLinkedList的Iterator
				l++;
				return node.getDate();
			}
		}
	}
