package testDownload;

import static org.junit.Assert.*;

import org.junit.Test;

import com.coderising.download.api.ConnectionManager;
import com.coderising.download.api.DownloadListener;
import com.coderising.download.impl.ConnectionManagerImpl;

import day_2017_3_8_ThreadHomework.FileDownloader;

public class FileDownloaderTest {
	boolean downloadFinished = false;
	@Test
	public void test() {
		String url = "http://pic33.nipic.com/2013 0916/3420027_192919547000_2.jpg";
		FileDownloader downloader = new FileDownloader(url,"d:/test.jpg");
		ConnectionManager cm = new ConnectionManagerImpl();
		downloader.setConnectionManager(cm);
		downloader.setListener(new DownloadListener(){
			@Override
			public void notifyFinished(){
				downloadFinished = true;
			}
		});
		
		downloader.execute();
		
		// �ȴ����߳����س���ִ�����
		while(!downloadFinished){
			try {
				System.out.println("��û������ɣ���������");
				// ���� 5 ��
				Thread.sleep(5000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("�������");
	}
	

}
