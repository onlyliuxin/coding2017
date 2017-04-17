package day_2017_3_8_ThreadHomework;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.concurrent.CyclicBarrier;

import com.coderising.download.api.Connection;
import com.coderising.download.api.ConnectionManager;
import com.coderising.download.api.DownloadListener;

public class FileDownloader {
	private String url;
	private String localFile;
	DownloadListener listener;
	ConnectionManager cm;
	
	private static final int DOWNLOAD_THREAD_NUM = 3;
	public FileDownloader(String _url,String localFile){
		this.url = _url;
		this.localFile = localFile;
	}
	
	public void execute(){
		// ������ʵ����Ĵ��룬 ע�⣺ ��Ҫ�ö��߳�ʵ������
		// ��������������������ӿڣ�����Ҫд�⼸���ӿڵ�ʵ�ִ���
		// ��1�� ConnectionManager  ���Դ�һ�����ӣ�ͨ��Connection���Զ�ȡ���е�һ�Σ���StartPos,endPos��ָ����
		// ��2��DownloadListener, �����Ƕ��߳����أ����������Ŀͻ��˲�֪��ʲôʱ���������������Ҫʵ�ֵ������̶߳�ִ�����Ժ󣬵���listener��notifiedFinished�����������ͻ��˾����յ�֪ͨ
		// �����ʵ��˼·��
		// 1. ��Ҫ����ConnectionManager�� open ���������ӣ�Ȼ��ͨ�� Connection.getContentLength��������ļ��ĳ���
		// 2. ��������3���߳����أ�ע��ÿ���߳���Ҫ�ȵ���ConnectionManager��open����
		// 	     Ȼ����� read ������ read �������ж�ȡ�ļ��Ŀ�ʼλ�úͽ���λ�õĲ���������ֵ��byte[] ����
		// 3.�� byte ����д�뵽�ļ���
		// 4.���е��̶߳���������Ժ���Ҫ���� listener �� notifiedFinished ����
		
		// ����Ĵ�����ʵ�����룬Ҳ����˵ֻ��һ���̣߳�����Ҫ����ɶ��̵߳�
		CyclicBarrier barrier = new CyclicBarrier(DOWNLOAD_THREAD_NUM,new Runnable() {// �����е�Thread������ await����ʱ����ִ�к���� barrierAction,���ú�������߳�
			@Override
			public void run() {
				listener.notifyFinished();
			}
		});
		
		Connection conn = null;
		try {
			conn = cm.open(this.url);
			int length = conn.getContentLength();// �õ���Ҫ�����ļ��ĳ���
			createPlaceHolderFile(this.localFile,length);//ռλ
			System.out.println("ռλ���");
			int [][] ranges = allocateDownloadRange(DOWNLOAD_THREAD_NUM,length);// ��ÿ���̷߳��俪ʼλ�úͽ���λ��
			// ��ʼ�����ļ�
			System.out.println("��ʼ�����ļ�");
			for(int i = 0; i < DOWNLOAD_THREAD_NUM; i++){
				DownloadThread thread = new DownloadThread(
						cm.open(url),
						ranges[i][0],
						ranges[i][1],
						localFile,
						barrier);
				thread.start();
				System.out.println("��" + (i+1) + "���߳��Ѿ�����");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			System.out.println("�����ر�����");
			if(conn != null){
				conn.close();
				System.out.println("�ر����ӳɹ�");
			}
		}
	}
	
	public void setListener(DownloadListener listener){
		this.listener = listener;
	}
	public void setConnectionManager(ConnectionManager ucm){
		this.cm = ucm;
	}
	public DownloadListener getListener(){
		return this.listener;
	}
	private void createPlaceHolderFile(String fileName,int contentLen) throws IOException{
		RandomAccessFile file = new RandomAccessFile(fileName,"rw");
		for(int i = 0; i < contentLen; i++){
			file.write(0);
		}
		file.close();
	}
	/**
	 *  �����߳������ļ����ȣ�����һ����ά���飬��������ÿ���߳����صĿ�ʼλ�úͽ���λ��
	 * @param threadNum
	 * @param contentLen
	 * @return
	 */
	private int [][] allocateDownloadRange(int threadNum, int contentLen){
		int [][] ranges = new int[threadNum][2];// �ö�ά�������ÿ���̵߳Ŀ�ʼλ�úͽ���λ��
		
		int eachThreadSize = contentLen / threadNum;// ÿ���߳���Ҫ���ص��ļ���С
		int left = contentLen % threadNum;// ʣ�µĹ����һ���߳�������
		
		for(int i = 0; i<threadNum; i++){
			int startPos = i * eachThreadSize;
			int endPos = (i+1) * eachThreadSize - 1;
			if((i == (threadNum -1))){
				endPos += left;
			}
			ranges[i][0] = startPos;
			ranges[i][1] = endPos;
		}
		
		return ranges;
	}
}
