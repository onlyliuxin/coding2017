package main.coding_170309.impl;

import main.coding_170309.api.DownloadListener;

import java.util.concurrent.ExecutorService;

/**
 * Created by peter on 2017/3/9.
 */
public class DownloadListenerImpl implements DownloadListener {
    private ExecutorService executor;
    public DownloadListenerImpl(ExecutorService executor){
        this.executor = executor;
    }
    @Override
    public void notifyFinshed() {
       executor.shutdown();
       while (true){
           if(executor.isTerminated()){
               break;
           }
       }
        System.out.println("网络文件下载完成");
    }
}
