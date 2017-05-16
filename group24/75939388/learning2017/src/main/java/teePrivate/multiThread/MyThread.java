package teePrivate.multiThread;

/**
 * @author : 温友朝
 * @date : 2017/5/15
 */
public class MyThread implements Runnable {

    private volatile int no = 0;

    public MyThread(int no){
        this.no = no;
    }

    public void run() {
        synchronized (this){
            try{
                /**
                 * wait和notify方法均可释放对象的锁，但wait同时释放CPU控制权，
                 * 即它后面的代码停止执行，线程进入阻塞状态，而notify方法不立刻
                 * 释放CPU控制权，而是在相应的synchronized(){}语句块执行结束，
                 * 再自动释放锁。
                 */
                //唤醒正在等待的线程，将锁交给JVM
                notify();
                for(int i = 0; i < 10; i++){
                    System.out.println(no + " = " + i);
                }
                //执行完毕之后将线程置为等待状态，线程被阻塞
                wait();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
