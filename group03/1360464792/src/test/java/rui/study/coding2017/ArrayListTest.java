package rui.study.coding2017;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * 测试我的数组如何
 * Created by 赵睿 on 2017/2/24.
 */
public class ArrayListTest {
    @Test
    public void newArrayList(){
        System.out.println(new ArrayList().size());
        System.out.println(new ArrayList(0).size());
        System.out.println(new ArrayList(-1).size());
    }

    @Test
    public void add() throws Exception {
        ArrayList arrayList=new ArrayList(2);
        arrayList.add(1);
        arrayList.add(2);
        System.out.println(arrayList.size());
        System.out.println(arrayList.get(0));
        System.out.println(arrayList.get(1));
        System.out.println(arrayList.get(2));
        System.out.println(arrayList.get(3));
    }

    @Test
    public void add1() throws Exception {
        ArrayList arrayList=new ArrayList(2);
        arrayList.add(0);
        arrayList.add(0,1);
        arrayList.add(1,2);
        System.out.println(arrayList.size());
        System.out.println(arrayList.get(0));
        System.out.println(arrayList.get(1));
        System.out.println(arrayList.get(2));
        System.out.println(arrayList.get(3));
    }
    @Test
    public void add2() throws Exception {
        java.util.ArrayList arrayList=new java.util.ArrayList(2);
        arrayList.add(0);
        arrayList.add(0,1);
        arrayList.add(1,2);
        System.out.println(arrayList.size());
        System.out.println(arrayList.get(0));
        System.out.println(arrayList.get(1));
        System.out.println(arrayList.get(2));
        System.out.println(arrayList.get(3));
    }

    @Test
    public void remove() throws Exception {
        ArrayList arrayList=new ArrayList(2);
        arrayList.add(0);
        arrayList.add(0,1);
        arrayList.add(1,2);
        System.out.println(arrayList.size());
        System.out.println(arrayList.remove(1));
        System.out.println(arrayList.size());
        System.out.println(arrayList.get(0));
        System.out.println(arrayList.get(1));
        System.out.println(arrayList.get(2));
    }

    @Test
    public void iterator() throws Exception {
        ArrayList arrayList=new ArrayList(2);
        arrayList.add(0);
        arrayList.add(0,1);
        arrayList.add(1,2);
        Iterator iterator=arrayList.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

}