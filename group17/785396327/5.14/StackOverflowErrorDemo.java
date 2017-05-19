/**
 * Created by IBM on 2017/5/15.
 */
public class StackOverflowErrorDemo {

    StackOverflowErrorDemo stackOverflowErrorDemo;

    public StackOverflowErrorDemo() {
        stackOverflowErrorDemo = new StackOverflowErrorDemo();
    }

    public static void main(String[] args) {
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("栈溢出");
        StackOverflowErrorDemo stackOverflowErrorDemo = new StackOverflowErrorDemo();
    }
}
