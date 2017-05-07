import java.net.URL;
import java.net.HttpURLConnection;
import java.io.IOException;
import java.io.InputStream;
import java.io.ByteArrayOutputStream;

public class Connection {   
    private URL url;
    private HttpURLConnection conn;    
    
    //创建一个Connection对象，相应建立了一个HttpURLConnection连接
    public Connection(String url) {        
        try {
            this.url = new URL(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void initConnection() {
        try {
            conn = (HttpURLConnection)url.openConnection();           
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    //从服务器下载startPos-endPos字节范围的资源内容到一个字节数组
    //Range: 用于客户端到服务器端的请求，可通过该字段指定下载文件的某一段大小，及其单位。
    public byte[] read(int startPos, int endPos) throws IOException {                                                  
        initConnection();
        conn.setRequestProperty("Range", "bytes=" + startPos + "-" + endPos);
        InputStream in = conn.getInputStream();                    
        ByteArrayOutputStream out = new ByteArrayOutputStream();
            
        byte[] buf = new byte[1024];
        int hasRead = 0;        
        while ((hasRead = in.read(buf)) != -1) {
            out.write(buf, 0, hasRead);
        }   
        out.close();
        in.close();
        return out.toByteArray();            
    }
    
    public int getContentLength() {
        initConnection();
        return conn.getContentLength();
    }
    
    public void close() {        
    }
}