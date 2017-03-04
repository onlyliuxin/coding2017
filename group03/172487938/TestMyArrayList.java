package 基本数据结构;

/**
 * Created by LIANG on 2017/2/24.
 */
public class TestMyArrayList
{
    public static void main(String[] args)
    {
        MyArrayList<String> list = new MyArrayList<>();
        list.add("America");
        System.out.println(list);

        list.add("Canada");
        System.out.println(list);

        list.add("Tokoy");
        System.out.println(list);

        list.add("Shanghai");


        int size = list.size();
        System.out.println(size);
        list.add(0,"China");
        System.out.println(list);
        String[] str = {"Japan","England","France","HonKong","BeiJing"};
        for(String s:str)
            list.add(s);
        System.out.println(list);

        list.remove(3);
        System.out.println(list);

    }
}
