import java.util.ArrayList;
import java.util.List;

public class HelloWorld {
   public static void main(String[] args) {
       System.out.println("Hello World!");
       List<Object> list = new ArrayList<>();
       list.add(new Object());

       list.add(2, new Object());
   } 
}