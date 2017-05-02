package data_structure.list;

import org.junit.Test;
import basic.dataStructure.array.ArrayList;

/**
 * Created by macvi on 2017/4/2.
 */
public class ArrayListTest {

    @Test
    public void TestAdd(){
        ArrayList al = new ArrayList();
        for(int i = 0; i < 32; i++){
            al.add(i + "");
        }

        System.out.println("ArrayList.content-->" + al.toString());
    }


    @Test
    public void testIndexAdd(){
        ArrayList al = new ArrayList();
        for(int i = 0; i < 17; i ++){
            al.add(i + "");
        }

        al.add(3, "xxoo");
        al.add(11, "abcd");
        al.add(0, "efgh");
        al.add(al.size(), "ijkl");

        System.out.println("al.toString-->" + al.toString());
        System.out.println("size-->" + al.size());
    }

    @Test
    public void testGet(){
        ArrayList al = new ArrayList();
        for(int i = 0; i < 18; i ++){
            al.add(i + "zxcd");
        }

        System.out.println("get-->" + al.get(13));
    }

    @Test
    public void testRemove(){
        ArrayList al = new ArrayList();
        for(int i = 0; i < 18; i ++){
            al.add(i + "");
        }
        System.out.println("size1-->" + al.size());
        System.out.println("al.toString1-->" + al.toString());
        String re = (String)al.remove(12);
        System.out.println("remove index=12 :");
        System.out.println("re-->" + re);
        System.out.println("size2-->" + al.size());
        System.out.println("al.toString2-->" + al.toString());

        String re1 = (String)al.remove(1);
        System.out.println("remove index=1 :");
        System.out.println("re-->" + re1);
        System.out.println("size2-->" + al.size());
        System.out.println("al.toString2-->" + al.toString());
    }

}
