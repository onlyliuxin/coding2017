package multiThread;

/**
 * @author : 温友朝
 * @date : 2017/5/15
 */
public class Main{

    public static void main(String[] args){
        MyThread t1 = new MyThread(1);
        MyThread t2 = new MyThread(2);

        new Thread(t1).start();
        new Thread(t2).start();
    }

}
