package memory_exception;

/**
 * Created by zhouliang on 2017-05-27.
 */
public class StackOverflowError {
    public static int count = 0;
    public static void StackOverflowErrorTest(){
        System.out.println(++count);
        StackOverflowErrorTest();
    }
    public static void main(String[] args) {
        StackOverflowErrorTest();
    }
}
