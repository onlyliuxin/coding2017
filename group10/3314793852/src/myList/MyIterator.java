	
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
			if(aData instanceof MyArrayList){//MyArrayList��Iterator
				
				Object[] arr=((MyArrayList) aData).getArr();
				int a=((MyArrayList)aData).size();
				return a>i;
			}
			else{//MyLinkedList��Iterator
				node=((MyLinkedList)aData).getHeadNode();//���ͷ�ڵ�
				int a=((MyLinkedList)aData).size();
				return a>l;
			}
			
			
		}
		public Object next(){
			if(aData instanceof MyArrayList){//MyArrayList��Iterator
				
				Object[] arr=((MyArrayList) aData).getArr();
				return arr[++i];
			}
			else{//MyLinkedList��Iterator
				l++;
				return node.getDate();
			}
		}
	}
