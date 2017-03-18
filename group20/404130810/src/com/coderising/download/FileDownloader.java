package com.coderising.download;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

import com.coderising.download.api.Connection;
import com.coderising.download.api.ConnectionException;
import com.coderising.download.api.ConnectionManager;
import com.coderising.download.api.DownloadListener;
import com.coderising.download.impl.ConnectionManagerImpl;
import com.coderising.download.utils.FileDownloadUtil;

public class FileDownloader {

	String url = "http://localhost:8080/MyServer/test.exe";

	DownloadListener listener;

	ConnectionManager cm;

	public FileDownloader(String _url) {
		this.url = _url;
		cm = new ConnectionManagerImpl();
	}

	public void execute() throws IOException {
		// ������ʵ����Ĵ��룬 ע�⣺ ��Ҫ�ö��߳�ʵ������
		// ��������������������ӿ�, ����Ҫд�⼸���ӿڵ�ʵ�ִ���
		// (1) ConnectionManager , ���Դ�һ�����ӣ�ͨ��Connection���Զ�ȡ���е�һ�Σ���startPos,
		// endPos��ָ����
		// (2) DownloadListener, �����Ƕ��߳����أ� ���������Ŀͻ��˲�֪��ʲôʱ���������������Ҫʵ�ֵ�����
		// �̶߳�ִ�����Ժ� ����listener��notifiedFinished������ �����ͻ��˾����յ�֪ͨ��
		// �����ʵ��˼·��
		// 1. ��Ҫ����ConnectionManager��open���������ӣ�
		// Ȼ��ͨ��Connection.getContentLength��������ļ��ĳ���
		// 2. ��������3���߳����أ� ע��ÿ���߳���Ҫ�ȵ���ConnectionManager��open����
		// Ȼ�����read������ read�������ж�ȡ�ļ��Ŀ�ʼλ�úͽ���λ�õĲ����� ����ֵ��byte[]����
		// 3. ��byte����д�뵽�ļ���
		// 4. ���е��̶߳���������Ժ� ��Ҫ����listener��notifiedFinished����

		// ����Ĵ�����ʾ�����룬 Ҳ����˵ֻ��һ���̣߳� ����Ҫ����ɶ��̵߳ġ�
		Connection conn = null;
		try {
			conn = cm.open(url);
			int length = conn.getContentLength();
			int[] posArr = FileDownloadUtil.generateDownloadPosArr(length);
			CountDownLatch latch = new CountDownLatch(3);
			for (int i = 0; i < posArr.length; i++) {
				if (i == posArr.length - 1) {
					new DownloadThread(cm.open(url), posArr[i], length, latch).start();
				} else {
					new DownloadThread(cm.open(url), posArr[i], posArr[i + 1] - 1, latch).start();
				}
			}
			latch.await();
			System.out.println("Download Finished");
		} catch (ConnectionException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	public void setListener(DownloadListener listener) {
		this.listener = listener;
	}

	public void setConnectionManager(ConnectionManager ucm) {
		this.cm = ucm;
	}

	public DownloadListener getListener() {
		return this.listener;
	}

	public static void main(String[] args) throws IOException {
		new FileDownloader("http://localhost:8080/MyServer/Test.mp3").execute();
	}

}