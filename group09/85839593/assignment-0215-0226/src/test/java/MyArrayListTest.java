import org.junit.*;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: guohairui
 * Date: 17-2-22
 * Time: 上午12:24
 * To change this template use File | Settings | File Templates.
 */
public class MyArrayListTest {

    @Test
    public void testAdd() throws Exception {
        MyArrayList arrayList = new MyArrayList();
        arrayList.add("1");
        arrayList.add("2");
        arrayList.add("3");
        arrayList.add("4");
        arrayList.add("5");
        arrayList.add("6");
        arrayList.add(7);
        arrayList.add(8);
        arrayList.add(9);
        arrayList.add(10);
        arrayList.add(true);
        System.out.println(String.valueOf(arrayList.get(2)));
        System.out.println(arrayList.toString());
        arrayList.remove(2);
        System.out.println(arrayList.toString());

    }

    @Test
      public void testGet() throws Exception {
        MyArrayList arrayList = new MyArrayList();
        //arrayList.add(6,"1");
        arrayList.add(5,"2");
        arrayList.add(4,"3");
        arrayList.add(3,"4");
        arrayList.add(2,"5");
        arrayList.add(1,"6");
        //arrayList.add(0,"7");
        System.out.println(arrayList.toString());
        System.out.println(arrayList.get(4));
        arrayList.remove(2);
        System.out.println(arrayList.toString());

    }
    @Test
    public void testInsert() throws Exception {
        MyArrayList arrayList = new MyArrayList();
        arrayList.add("1");
        arrayList.add("2");
        arrayList.add("3");
        arrayList.add("4");
        arrayList.add("5");
        arrayList.add("6");
        System.out.println(arrayList.toString());
        System.out.println(arrayList.get(4));
        arrayList.insert(4,"ghr");
        System.out.println(arrayList.get(4));
        System.out.println(arrayList.toString());
    }

    @Test
    public void testRemove()  {
         throw new RuntimeException();
    }

    @Test
    public void testSize() throws Exception {
        System.out.println(16>>2);
    }
}
