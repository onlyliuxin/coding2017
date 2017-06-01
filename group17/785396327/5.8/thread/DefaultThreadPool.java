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
            addWorker(runnable);
        }
        //   放入阻塞队列中
        else {
            if (!workersQueue.offer(runnable)) {
                //阻塞队列已满
                if (count < maximumPoolSize) {
                    //小于最大线程数，创建线程
                    addWorker(runnable);
                } else {
                    //达到最大线程数且队列已满，拒绝策略
                    reject(runnable);
                }
            }
        }
    }

    private void addWorker(Runnable runnable) {
        int count = workNum.get();
        Worker worker = new Worker(runnable);
        workers.add(worker);
        workNum.compareAndSet(count, count + 1);
        Thread thread = worker.getThread();
        thread.start();
    }

    private void reject(Runnable runnable) {
        System.out.println("拒绝策略");
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
            //这里传入当前worker对象，不能传入runnable
            this.thread = new Thread(this);
        }

        @Override
        public void run() {
            try {
                //while只要有任务，或者阻塞队列中能取出任务就执行
                while (runnable != null || (runnable = getTask()) != null) {
                    try {
                        runnable.run();
                    } finally {
                        runnable = null;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                processWorkerExit(this);
            }
        }

        /**
         * 从阻塞队列中得到任务，如果没有任务会阻塞等待
         *
         * @return
         */
        private Runnable getTask() {
            try {
                return workersQueue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        /**
         * 线程退出后从集合中删除，并且工作worker数量减一
         *
         * @param worker
         */
        private void processWorkerExit(Worker worker) {
            workers.remove(worker);
            while (!compareAndDecrementWorkerCount(workNum.get())) ;
        }

        public Thread getThread() {
            return thread;
        }
    }

    private boolean compareAndDecrementWorkerCount(int except) {
        return workNum.compareAndSet(except, except - 1);
    }
}
