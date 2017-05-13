package thread;

/**
 * Created by william on 2017/5/9.
 */
public interface ThreadPool {
    //    执行线程任务
    void execute(Runnable runnable);

    //    关闭线程池
    void shutDown();

    //    增加工作线程
    void addWorkers(int num);

    //    减少工作线程
    void removeWorkers(int num);

    //    得到正在执行任务的数量
    int getWorkerSize();
}
