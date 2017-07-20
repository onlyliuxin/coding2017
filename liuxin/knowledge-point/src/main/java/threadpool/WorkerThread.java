package threadpool;


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
            }
        }
    }

    public synchronized void doStop(){
        isStopped = true;
        this.interrupt(); //break pool thread out of dequeue() call.
    }

    public synchronized boolean isStopped(){
        return isStopped;
    }
}