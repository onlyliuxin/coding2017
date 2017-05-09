package thread;

/**
 * Created by IBM on 2017/5/9.
 */
public class DefaultThreadPool implements ThreadPool {
    //    默认线程池中线程数量，同时也是核心线程数量
    private static final int corePoolSize = 5;
    //    线程池最大线程数量
    private static final int maximumPoolSize = 10;


    @Override
    public void execute(Runnable runnable) {

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
        return 0;
    }
}
