package main.coding_170309;

import main.coding_170309.api.ConnectionException;

import java.io.IOException;

/**
 * Created by peter on 2017/3/9.
 */
public class Hello {
    public static void main(String[] args) {
        String url = "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=4162473607,1717166705&fm=23&gp=0.jpg";
        FileDownloader downloader = new FileDownloader(url);
        try {
            downloader.execute();
        } catch (ConnectionException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
