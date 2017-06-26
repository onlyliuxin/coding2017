package com.coderising.myknowledgepoint.threadpool;

public class WorkerThread extends Thread {

    private BlockingQueue taskQueue = null;
    private boolean       isStopped = false;

    public WorkerThread(BlockingQueue queue){
        taskQueue = queue;
    }

    public void run(){
        while(!isStopped()){
            try{
                Task task = (Task) taskQueue.dequeue();
                task.execute();
            } catch(Exception e){
                //log or otherwise report exception,
                //but keep pool thread alive.
                System.out.println("doStop");
            }
        }
    }

    public synchronized void doStop(){
        isStopped = true;
        this.interrupt();  //break pool thread out of dequeue() call，可以令waiting状态的线程抛出异常从而跳出
    }

    public synchronized boolean isStopped(){
        return isStopped;
    }
}