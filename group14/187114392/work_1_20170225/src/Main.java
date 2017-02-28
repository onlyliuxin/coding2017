/**
 * Created by bshu on 2017/2/25.
 */
import com.coding.basic.ArrayList;
import com.coding.basic.Iterator;

import java.util.*;

/**
 * Created by bshu on 2017/2/21.
 */
public class Main {
    public static void main(String[] args) {
        int size = 0;
        System.out.println("1 size " + (size++));
        System.out.println("2 size " + (size));
        LinkedList lnk = new LinkedList();
        java.util.ArrayList<String> list = new java.util.ArrayList<String>(){{add("str0");}};
        lnk.add("kk1");
        lnk.add("kk2");
        lnk.add("kk3");
        lnk.remove(2);
        lnk.iterator();
        int count = 0;
        for (java.util.Iterator iter = lnk.iterator(); iter.hasNext();) {
            System.out.printf("%s is :%s \n",count, iter.next());
            ++count;
        }
    }
}

