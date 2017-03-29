package com.coding.basic;

/**
 * Created by Funy on 2017/3/12.
 */
public class test {
    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.add(2);

        list.add(3);
        list.add(8);
        list.add(10);
        list.add(11);

        list.remove(3,6);
        // list.addFirst(2);
        System.out.println(list.size());
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            // String s= new String(iterator.next());
            System.out.print(iterator.next()+" ");
        }
        //System.out.println(list.get(1));
    }
}
