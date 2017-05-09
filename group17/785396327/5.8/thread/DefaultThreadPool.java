package thread;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IBM on 2017/5/9.
 */
public class DefaultThreadPool implements ThreadPool {
    //    默认线程池中线程数量，同时也是核心线程数量
    private static final int corePoolSize = 5;
    //    线程池最大线程数量
    private static final int maximumPoolSize = 10;
    //    可供工作的线程集合（工作者）
    private static final List<Thread> workers = new ArrayList<Thread>(corePoolSize);
    //    正在工作的线程数量
    private int workNum;

    static {
        for (int i = 0; i < corePoolSize; i++) {
            workers.add(new Thread("worker - " + i));
        }
    }

    @Override
    public void execute(Runnable runnable) {
        if(workNum<0)
            throw new RuntimeException("error number of working thread");
        if (workNum < corePoolSize) {
            Thread thread = workers.get(0);
//            thread.s
        }
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
        return workNum;
    }
}
