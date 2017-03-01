package test01.linkedList;

import org.junit.Test;

public class MyLinkedListTest {

	@Test
	public void MyLinkedListTest(){
        MyLinkedList<String> list = new MyLinkedList<String>();

        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");

        for (int i = 0; i < list.size() ; i++) {
            System.out.print(list.get(i)+",");
        }
        System.out.println("下标为3的元素为"+list.get(3));
        System.out.println("数组size:"+list.size());
        list.remove(2);
        System.out.print("remove后的数组size:"+list.size());

        System.out.print("remove后的数组:");
        for (int i = 0; i < list.size() ; i++) {
            System.out.print(list.get(i)+",");
        }

        list.add(3,"gg");

        System.out.println("");
        System.out.print("insert后的数组:");
        for (int i = 0; i < list.size() ; i++) {
            System.out.print(list.get(i)+",");
        }
    }
}
