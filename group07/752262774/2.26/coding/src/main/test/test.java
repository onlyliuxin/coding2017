package main.test;


import javax.swing.tree.TreeNode;
import java.util.*;

/**
 * Created by yrs on 2017/2/21.
 */
public class test {
    public static void main(String [] args) {
        ArrayList list = new ArrayList(4);
        list.add(9);
        System.out.println(list);
        list.add(1,3);
//        list.add(2,3);   //error IndexOutOfBoundsException
        list.remove(1);
        System.out.println(list.size());

        Object[] target = new Object[0];
        System.out.println(target);
        Object[] EMPTY_ELEMENTDATA = {};
        System.out.println(EMPTY_ELEMENTDATA);


        //LinkedList
        LinkedList linkedList = new LinkedList();
        linkedList.add(1);
        System.out.println(linkedList.get(0));
        linkedList.add(1,3);
        System.out.println(linkedList.size());
        System.out.println(3 >> 1);

        for(int i=0; i<1; i++) {
            System.out.println("dd");
        }

        Stack stack = new Stack();

        Queue queue;
        TreeNode treeNode;

        List<Integer> lstint = new ArrayList<Integer>();
        lstint.add(1);
        lstint.add(2);
        lstint.add(3);

        // Iterator遍历一
        Iterator<Integer> iterator = lstint.iterator();
        iterator.hasNext();
        while (iterator.hasNext())
        {
            int i = (Integer) iterator.next();
            System.out.println(i);
        }


    }
}
                