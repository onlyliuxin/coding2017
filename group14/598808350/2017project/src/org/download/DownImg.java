package org.download;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownImg {

	public void saveImg(String destUrl){
		HttpURLConnection conn = null;
		URL url = null;
		int BUFFER_SIZE = 1024;
		byte[] buf = new byte[BUFFER_SIZE];
		int size = 0;
		BufferedInputStream bis = null;
		FileOutputStream fos = null;
		try{
			url = new URL(destUrl);
			conn = (HttpURLConnection)url.openConnection();
			conn.connect();
			bis = new BufferedInputStream(conn.getInputStream());
			fos = new FileOutputStream("c:\\aa.jpg");
			while((size = bis.read(buf)) != -1){
				fos.write(buf,0,size);
			}
			fos.flush();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				bis.close();
				fos.close();
				conn.disconnect();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DownImg down = new DownImg();
		down.saveImg("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489836924867&di=68eb122b4c92d6b5e2f3af6d7331290e&imgtype=0&src=http%3A%2F%2Fcdn.duitang.com%2Fuploads%2Fitem%2F201210%2F21%2F20121021202014_kAAr3.thumb.600_0.jpeg");
	}

}
