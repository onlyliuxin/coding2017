/*�������ƣ�
 * ԭ�ļ����ƣ�
 * Ҫ�㣺
 * 1. ʵ�ֻ��������ݽṹ�ࣺArrayList��LinkedList��Queue��Stack��Tree

 */
public class CollectionTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//����ArrayList
		ArrayList_self<Name> arrayList1=new ArrayList_self<Name>();
		for(int i=0;i<15;i++){
			arrayList1.add(new Name("An"+i, "Array"));
			if(i>6){
				arrayList1.set(i, new Name("Bo"+i, "Array"));
			}
			System.out.println(arrayList1.get(i));
		}
		
		//����LinkedList
		LinkedList_self<Name> linkedList1=new LinkedList_self<Name>();
		for(int i=0;i<8;i++){
			linkedList1.add(new Name("An"+i, "Linked"));
			if(i>3){
				linkedList1.set(i, new Name("Bo"+i, "Linked"));
			}
			System.out.println(linkedList1.get(i));
		}
		
		//����Stack
		Stack_self<Name> stack1=new Stack_self<Name>();
		for(int i=0;i<6;i++){
			stack1.push(new Name("An"+i, "Stack"));
			if(i>3){
				System.out.println(stack1.peek());
			}
		}
		
		//����Queue
		Queue_self<Name> queue1=new Queue_self<Name>();
		for(int i=0;i<6;i++){
			queue1.enQueue(new Name("An"+i, "Queue"));
			if(i>3){
				System.out.println(queue1.deQueue());
			}
		}
	}

}
