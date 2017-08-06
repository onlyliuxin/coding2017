package com.coderising.myknowledgepoint.threadpool;

import java.util.ArrayList;
import java.util.List;


public class ThreadPool {

    private BlockingQueue taskQueue = null;
    private List<WorkerThread> threads = new ArrayList<>();
    private boolean isStopped = false;

    /**
     * 线程池实例化的时候，线程就都启动了
     * @param numOfThreads
     * @param maxNumOfTasks
     */
    public ThreadPool(int numOfThreads, int maxNumOfTasks){
        taskQueue = new BlockingQueue(maxNumOfTasks);

        for(int i=0; i<numOfThreads; i++){
            threads.add(new WorkerThread(taskQueue));
        }
        for(WorkerThread thread : threads){
            thread.start();
        }
    }

    /**
     * 往阻塞队列里面加task
     * @param task
     * @throws Exception
     */
    public synchronized void execute(Task task) throws Exception{
        if(this.isStopped) throw
            new IllegalStateException("ThreadPool is stopped");

        this.taskQueue.enqueue(task);
    }

    public synchronized void stop(){
        this.isStopped = true;
        for(WorkerThread thread : threads){
           thread.doStop();
        }
    }

}