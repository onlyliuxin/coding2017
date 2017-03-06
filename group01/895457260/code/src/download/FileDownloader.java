package download;

import download.api.*;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;


public class FileDownloader {
	String url;
	DownloadListener listener;
	ConnectionManager manager;

	public FileDownloader(String url) {
		this.url = url;
	}
	
	public void execute() {
		// 在这里实现你的代码， 注意： 需要用多线程实现下载
		// 这个类依赖于其他几个接口, 你需要写这几个接口的实现代码
		// (1) ConnectionManager , 可以打开一个连接，通过Connection可以读取其中的一段（用startPos, endPos来指定）
		// (2) DownloadListener, 由于是多线程下载， 调用这个类的客户端不知道什么时候结束，所以你需要实现当所有
		//     线程都执行完以后， 调用listener的notifiedFinished方法， 这样客户端就能收到通知。
		// 具体的实现思路：
		// 1. 需要调用ConnectionManager的open方法打开连接， 然后通过Connection.getContentLength方法获得文件的长度
		// 2. 至少启动3个线程下载，  注意每个线程需要先调用ConnectionManager的open方法
		// 然后调用read方法， read方法中有读取文件的开始位置和结束位置的参数， 返回值是byte[]数组
		// 3. 把byte数组写入到文件中
		// 4. 所有的线程都下载完成以后， 需要调用listener的notifiedFinished方法
		
		// 下面的代码是示例代码， 也就是说只有一个线程， 你需要改造成多线程的。
		if (!Config.targetDirectory.exists()) {
			Config.targetDirectory.mkdir();
		}
		if (!Config.tempDirectory.exists()) {
			Config.tempDirectory.mkdir();
		}

		int threadCount = 4;
		File[] tempFiles = new File[threadCount];
		Connection[] connections = new Connection[threadCount];
		boolean[] threadComplete = new boolean[threadCount];
		for (int i = 0; i < threadCount; ++i) {
			File targetFile = new File(Config.tempDirectory,
					new Date().getTime() + "_" + i);
			tempFiles[i] = targetFile;

			Connection conn = null;
			try {
				conn = manager.open(this.url);
			} catch (ConnectionException e) {
				e.printStackTrace();
			}

			if (conn != null) {
				connections[i] = conn;
				int length = conn.getContentLength();
				int startPos = (int) (1.0 * length / threadCount * i);
				int endPos = i == threadCount - 1 ? length : (int) (1.0 * length / threadCount * (i + 1));
				System.out.println(i + " start: " + startPos + "  end: " + endPos);
				final int index = i;
				new DownloadThread(conn, startPos, endPos, targetFile,
						() -> threadComplete[index] = true, () -> {
					try {
						downloadError(connections);
					} catch (DownloadException e) {
						e.printStackTrace();
					}
				}).start();
			}
		}

		// 等待下载完成
		while (true) {
			boolean complete = true;
			for (boolean b : threadComplete) {
				complete = complete && b;
			}
			if (complete) {
				break;
			} else {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		// 合并临时文件
		String[] split = url.replaceAll("/+", "/").split("/");
		File saveFile = new File(Config.targetDirectory, split[split.length - 1]);
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(saveFile);
			for (File tempFile : tempFiles) {
				write(tempFile, fos);
				tempFile.delete();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			for (Connection c : connections) {
				if (c != null) {
					c.close();
				}
			}
		}

		if (listener != null) {
			listener.notifyFinished();
		}
	}


	private void downloadError(Connection[] connections) throws DownloadException {
		for (Connection c : connections) {
			c.close();
		}
		throw new DownloadException();
	}

	private void write(File inputFile, OutputStream os) {
		FileInputStream fis = null;
		int bufSize = 1024;
		byte[] buf = new byte[bufSize];
		int n;
		try {
			fis = new FileInputStream(inputFile);
			while ((n = fis.read(buf)) != -1) {
                os.write(buf, 0, n);
                os.flush();
            }
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void setListener(DownloadListener listener) {
		this.listener = listener;
	}

	public void setConnectionManager(ConnectionManager manager){
		this.manager = manager;
	}
	
	public DownloadListener getListener(){
		return this.listener;
	}

	public static void main(String[] args) {
		try {
			URL url = new URL("file:///E:/Video/download/88993.mp4");
			System.out.println(url.getProtocol() + " " + url.getHost() + " " + url.getPort()
					+ " " + url.getPath() + " " + url.getFile());
			URLConnection c = url.openConnection();
			System.out.println(c.getContentLength());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
