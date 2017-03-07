package mytest;

public class myMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		myMain myTest = new myMain();
//		myTest.testArrayList();
		myTest.testLinkedList();
	}

	public void testLinkedList(){
		LinkedList myList = new LinkedList();
		myList.add("aa");
		myList.add("bb");
		myList.add("cc");
		myList.add("dd");
		myList.add("ee");
		myList.add("ff");
		myList.add("gg");
		myList.removeLast();
		for(int i = 0;i < myList.size();i++){
			System.out.println(myList.get(i));
		}
		
	}
	
	public void testArrayList(){
		ArrayList myArray = new ArrayList(5);
		myArray.add("xx1");
		myArray.add("xx2");
		myArray.add("xx3");
		myArray.add("xx4");
		myArray.add("xx5");
		myArray.add("xx6");
		myArray.add("xx7");
		myArray.add("xx8");
		myArray.add("xx9");
		myArray.add("xx10");
		myArray.add("xx11");
		myArray.add("xx12");
		myArray.add("xx13");
		myArray.remove(8);
		int i;
		for(i=0;i<myArray.size();i++){
			System.out.println(myArray.get(i));
		}		
	}

}
