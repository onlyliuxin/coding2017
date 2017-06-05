package thread;

/**
 * Created by macvi on 2017/5/3.
 */
public class ThreadTest extends Thread {
    boolean stop = false;
    int value = 0;
    public void run() {
        while (!stop) {
            value++;
        }
    }
    public static void main(String[] args)
            throws Exception {

        ThreadTest t = new ThreadTest();
        t.start();
        Thread.sleep(2000);
        t.stop = true;
        System.out.println("value = " + t.value);
        Thread.sleep(2000);
        System.out.println("value = " + t.value);

    }
}

