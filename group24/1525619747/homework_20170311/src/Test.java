import com.github.dengliotng.coding2017.basic.ArrayList;
import com.github.dengliotng.coding2017.basic.Iterator;

/**
 * Created by LeonDeng on 2017/3/11.
 */
public class Test {

    public static void main(String[] args) {
        ArrayList a = new ArrayList();
        for (int i =0; i<10; i++) {
            a.add("hello" + i);
        }
        a.add(20.56);
        System.out.println(a.size());
        a.add(5, 20.56);
        System.out.println(a.size());
        System.out.println(a.get(5));
//        a.remove(5);
//        a.remove(200);

        Iterator i = a.iterator();
        for ( ; i.hasNext(); ) {
            String str = i.next().toString();
            System.out.println(str);
        }
    }

}
