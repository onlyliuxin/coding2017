package zhuakuang.homework.first.Collection;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by BlindingDark on 2017/3/11 0011.
 */
public class TestCollection {
    @Test
    public void arrayListTest() {//分开测试每一个方法 List test
        ArrayList myArrayList = new ArrayList();

        myArrayList.add("1");
        myArrayList.add("2");
        myArrayList.add("3");
        myArrayList.add("4");
        myArrayList.add("5");
        myArrayList.add("6");
        myArrayList.add("7");
        myArrayList.add("8");
        myArrayList.add("9");
        myArrayList.add("10");
        myArrayList.add("11");
        myArrayList.add("12");

        myArrayList.add(2, "2.5");
        myArrayList.remove(1);

        String first = String.valueOf(Integer.valueOf((String) myArrayList.get(2)) - 2);
        String[] strs = {first, "2.5", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};

        Iterator itr = myArrayList.iterator();
        while (itr.hasNext()) {
            for (int i = 0; i < myArrayList.size(); i++) {
                assertEquals(strs[i], itr.next());
            }
        }
    }
}
