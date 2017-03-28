import java.io.IOException;
import java.net.HttpURLConnection;
import java.io.RandomAccessFile;
import java.util.concurrent.locks.*;
public class DownloadThread extends Thread{

	Connection conn;
	int startPos;
	int endPos;
    String targetURL;
    final ReentrantLock lock = new ReentrantLock();
    volatile int i = 0;
             
	public DownloadThread(Connection conn, int startPos, int endPos, String targetURL) {		
		this.conn = conn;		
		this.startPos = startPos;
		this.endPos = endPos;  
        this.targetURL = targetURL;
	}
    
	public void run() {               
        System.out.println("线程" + getName() + "startPos:" + startPos + "; endPos:" + endPos);
        lock.lock(); 
        
        try {
           
            RandomAccessFile raf = new RandomAccessFile(targetURL, "rw");   
            byte[] buf = conn.read(startPos, endPos);
            raf.seek(startPos);
            raf.write(buf);
            raf.close();            
        } catch (IOException e) {
            e.printStackTrace();
        }
        lock.unlock();
        System.out.println("线程" + this.getName() + "下载完成.");
        
	}
}
