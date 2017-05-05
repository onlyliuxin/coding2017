package coderising;

import coderising.array.ArrayUtil;
import coderising.litestruts.Struts;
import coderising.litestruts.View;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by laibin on 2017/3/4.
 */
public class Main {
    private static ArrayUtil arrayUtil = new ArrayUtil();

    public static void main(String[] args) {
        int[] ints1 = {1, 2, 3, 9, 22};
//        arrayUtil.grow(ints1, 10);
//        arrayUtil.getPerfectNumbers(100000000);
        String actionName = "login";

        Map<String, String> params = new HashMap<String, String>();
        params.put("name", "test");
        params.put("password", "1234");


        View view = Struts.runAction(actionName, params);

        arrayUtil.join(ints1, "-");
    }

    private static void merge() {
        int[] ints1 = {1, 2, 3, 9, 22};
        int[] ints2 = {2, 3, 4, 7, 8, 10};
        int[] newArr = arrayUtil.merge(ints1, ints2);
        for (int i = 0; i < newArr.length; i++) {
            System.out.println("newArr[i] = " + newArr[i]);
        }
    }

    public static void reverseArray() {
        int[] ints = {1, 2, 3, 4, 5, 6};
        arrayUtil.reverseArray(ints);
        System.out.println("ints = " + ints);
    }

    public static void removeZero() {
        int oldArr[] = {1, 3, 4, 5, 0, 0, 6, 6, 0, 5, 4, 7, 6, 7, 0, 5};
        int[] ints1 = arrayUtil.removeZero(oldArr);
        System.out.println("ints = " + ints1);
    }
}
