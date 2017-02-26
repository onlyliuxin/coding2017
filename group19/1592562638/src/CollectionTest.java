/*范例名称：
 * 原文件名称：
 * 要点：
 * 1. 实现基本的数据结构类：ArrayList、LinkedList、Queue、Stack、Tree

 */
public class CollectionTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//测试ArrayList
		ArrayList_self<Name> arrayList1=new ArrayList_self<Name>();
		for(int i=0;i<15;i++){
			arrayList1.add(new Name("An"+i, "Array"));
			if(i>6){
				arrayList1.set(i, new Name("Bo"+i, "Array"));
			}
			System.out.println(arrayList1.get(i));
		}
		
		//测试LinkedList
		LinkedList_self<Name> linkedList1=new LinkedList_self<Name>();
		for(int i=0;i<8;i++){
			linkedList1.add(new Name("An"+i, "Linked"));
			if(i>3){
				linkedList1.set(i, new Name("Bo"+i, "Linked"));
			}
			System.out.println(linkedList1.get(i));
		}
		
		//测试Stack
		Stack_self<Name> stack1=new Stack_self<Name>();
		for(int i=0;i<6;i++){
			stack1.push(new Name("An"+i, "Stack"));
			if(i>3){
				System.out.println(stack1.peek());
			}
		}
	}

}
