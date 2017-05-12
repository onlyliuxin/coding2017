package cn.xl.c3;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import cn.xl.c3.DownloadTask.ChildThread;
import cn.xl.c3.api.Connection;
import cn.xl.c3.api.ConnectionException;
import cn.xl.c3.api.ConnectionManager;
import cn.xl.c3.api.DownloadListener;

public class FileDownloader {

	private String url;

	private String fileName;

	private int threadLength;

	private String fileDir;

	private static final int DOWNLOAD_TRHEAD_NUM = 4;

	private DownloadThread[] childThreads ;

	DownloadListener listener;

	ConnectionManager cm;


	public FileDownloader(String _url,String _fileDir) {
		this.url = _url;
		this.fileDir = _fileDir;
	}

	public void execute(){
		
		
		CyclicBarrier barrier = new CyclicBarrier(DOWNLOAD_TRHEAD_NUM , new Runnable(){
			public void run(){
				listener.notifyFinished();
			}
		}); 
		
		
		Connection conn = null;
		int[] startPos = new int[DOWNLOAD_TRHEAD_NUM]; 
		CountDownLatch latch = new CountDownLatch(DOWNLOAD_TRHEAD_NUM);
		childThreads = new DownloadThread[DOWNLOAD_TRHEAD_NUM];
		int endPos = 0; 
		this.fileName = url.substring(url.lastIndexOf("/") + 1); 
		try {

			conn = cm.open(this.url);

			int length = conn.getContentLength();	
			
			System.out.println("length===="+length);

			this.threadLength = length / DOWNLOAD_TRHEAD_NUM; 
			
			ExecutorService exec = Executors.newCachedThreadPool(); 
			
			// 第一步，分析已下载的临时文件，设置断点，如果是新的下载任务，则建立目标文件。在第4点中说明。 
			startPos = setThreadBreakpoint(fileDir, fileName, length, startPos); 
			
			for (int i = 0; i < DOWNLOAD_TRHEAD_NUM; i++) { 
				startPos[i] += threadLength * i; 
				if (i == DOWNLOAD_TRHEAD_NUM - 1) { 
					endPos = length; 
				} else { 
					endPos = threadLength * (i + 1) - 1; 
				} 
				DownloadThread downloadThread = new DownloadThread(
						fileDir+fileName,
						cm.open(this.url),
						i,
						startPos[i],
						endPos, 
						latch);
				
				childThreads[i] = downloadThread; 
				exec.execute(downloadThread); 
			} 

			try {
				latch.await();
				exec.shutdown(); 
				tempFileToTargetFile(childThreads); 
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 

		} catch (ConnectionException e) {			
			e.printStackTrace();
		}finally{
			if(conn != null){
				
			}
		}

	}

	private void tempFileToTargetFile(DownloadThread[] childThreads) { 
		try { 
			BufferedOutputStream outputStream = new BufferedOutputStream( 
					new FileOutputStream(fileDir + fileName)); 

			// 遍历所有子线程创建的临时文件，按顺序把下载内容写入目标文件中 
			for (int i = 0; i < DOWNLOAD_TRHEAD_NUM; i++) { 
				/*if (statusError) { 
					for (int k = 0; k < threadNum; k++) { 
						if (childThreads[k].tempFile.length() == 0) 
							childThreads[k].tempFile.delete(); 
					} 
					System.out.println("本次下载任务不成功，请重新设置线程数。"); 
					break; 
				} */

				BufferedInputStream inputStream = new BufferedInputStream( 
						new FileInputStream(childThreads[i].tempFile)); 
				System.out.println("Now is file " + childThreads[i].id); 
				int len = 0; 
				int count = 0; 
				byte[] b = new byte[1024]; 
				while ((len = inputStream.read(b)) != -1) { 
					count += len; 
					outputStream.write(b, 0, len); 
					if ((count % 5000) == 0) { 
						outputStream.flush(); 
					} 

					// b = new byte[1024]; 
				} 

				inputStream.close(); 
				childThreads[i].tempFile.delete(); 
				// 删除临时文件 
				/*if (childThreads[i].status == ChildThread.STATUS_HAS_FINISHED) { 
					childThreads[i].tempFile.delete(); 
				} */
			} 

			outputStream.flush(); 
			outputStream.close(); 
		} catch (FileNotFoundException e) { 
			e.printStackTrace(); 
		} catch (IOException e) { 
			e.printStackTrace(); 
		} 
	} 
	private int[] setThreadBreakpoint(String fileDir2, String fileName2, 
			int contentLength, int[] startPos) { 
		File file = new File(fileDir + fileName); 
		long localFileSize = file.length(); 

		if (file.exists()) { 
			System.out.println("file " + fileName + " has exists!"); 
			// 下载的目标文件已存在，判断目标文件是否完整 
			if (localFileSize < contentLength) { 
				System.out.println("Now download continue "); 

				// 遍历目标文件的所有临时文件，设置断点的位置，即每个临时文件的长度 
				File tempFileDir = new File(fileDir); 
				File[] files = tempFileDir.listFiles(); 
				for (int k = 0; k < files.length; k++) { 
					String tempFileName = files[k].getName(); 
					// 临时文件的命名方式为：目标文件名+"_"+编号 
					if (tempFileName != null && files[k].length() > 0 
							&& tempFileName.startsWith(fileName + "_")) { 
						int fileLongNum = Integer.parseInt(tempFileName 
								.substring(tempFileName.lastIndexOf("_") + 1, 
										tempFileName.lastIndexOf("_") + 2)); 
						// 为每个线程设置已下载的位置 
						startPos[fileLongNum] = (int) files[k].length(); 
					} 
				} 
			} 
		} else { 
			// 如果下载的目标文件不存在，则创建新文件 
			try { 
				file.createNewFile(); 
			} catch (IOException e) { 
				e.printStackTrace(); 
			} 
		} 

		return startPos; 
	} 


	public void setListener(DownloadListener listener) {
		this.listener = listener;
	}



	public void setConnectionManager(ConnectionManager ucm){
		this.cm = ucm;
	}

	public DownloadListener getListener(){
		return this.listener;
	}
	
}
