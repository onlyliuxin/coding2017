package 基本数据结构;

/**
 * Created by LIANG on 2017/2/25.
 */
public class TestMyLinkedList
{
   public static void main(String[] args)
   {
       String[] name = {"Tom", "George", "Peter", "Jean", "George", "Jane"};
       MyList<String> list = new MyLinkedList<String>(name);

       System.out.println(list.contains("George"));
       System.out.println(list.get(3));
       System.out.println(list.indexOf("George"));
       list.set(4, "Michael");
       System.out.println(list);
   }
}
