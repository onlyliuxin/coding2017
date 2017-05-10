package thread;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by wiliam on 2017/5/9.
 */
public class DefaultThreadPool implements ThreadPool {
    //    默认线程池中线程数量，同时也是核心线程数量
    private static final int corePoolSize = 5;
    //    线程池最大线程数量
    private static final int maximumPoolSize = 10;
    //    可供工作的线程集合（工作者）
    private static final Set<Worker> workers = new HashSet<Worker>(corePoolSize);
    //    正在工作的线程数量
    private AtomicInteger workNum = new AtomicInteger(0);
    //    存放暂时处理不了的所有任务
    private final LinkedBlockingQueue<Runnable> workersQueue = new LinkedBlockingQueue<Runnable>();

    /**
     * 不能初始化corePoolSize数量的线程，因为线程的执行主体还未知
     */

    @Override
    public void execute(Runnable runnable) {
        //worker数量小于核心线程数量，创建一个worker，加到worker集合中
        int count = workNum.get();
        if (count < corePoolSize) {
            Worker worker = new Worker(runnable);
            workers.add(worker);
            workNum.compareAndSet(count, count + 1);
            Thread thread = worker.getThread();
            thread.start();
        }
        //   worker数量介于核心线程和最大线程数之间，放入阻塞队列中
        else if (count >= corePoolSize && count < maximumPoolSize) {
            if (!workersQueue.offer(runnable))
                reject(runnable);
        }
    }

    private void reject(Runnable runnable) {

    }

    @Override
    public void shutDown() {

    }

    @Override
    public void addWorkers(int num) {

    }

    @Override
    public void removeWorkers(int num) {

    }

    @Override
    public int getWorkerSize() {
        return workNum.get();
    }

    /**
     * 封装要执行的任务类
     * 写成内部类是因为需要使用外部类的变量
     */
    private final class Worker implements Runnable {
        //    执行任务的线程
        private Thread thread;
        //    任务的主体
        private Runnable runnable;

        public Worker(Runnable runnable) {
            if (runnable == null)
                throw new RuntimeException("wrong argument of worker");
            this.runnable = runnable;
            this.thread = new Thread(runnable);
        }

        @Override
        public void run() {
            try {
                thread.start();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                processWorkerExit(this);
            }
        }

        /**
         * 线程退出后从集合中删除，并且工作worker数量减一
         *
         * @param worker
         */
        private void processWorkerExit(Worker worker) {
            workers.remove(worker);
            workNum.decrementAndGet();
        }

        public Thread getThread() {
            return thread;
        }
    }
}
