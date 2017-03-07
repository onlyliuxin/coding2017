package test01.arrayList;

import org.junit.Test;

public class ArrayListTest {

	@Test
	public void cetrinWArrayListTest(){
        CetrinwList<String> list = new CetrinwArrayList<String>();

        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");

        System.out.println("下标为3的元素为"+list.get(3));
        System.out.println("数组size:"+list.size());
        list.remove(2);
        System.out.print("remove后的数组size:"+list.size());

        System.out.print("remove后的数组:");
        for (int i = 0; i < list.size() ; i++) {
            System.out.print(list.get(i)+",");
        }

        list.insert(3,"gg");

        System.out.println("");
        System.out.print("insert后的数组:");
        for (int i = 0; i < list.size() ; i++) {
            System.out.print(list.get(i)+",");
        }
    }
}
