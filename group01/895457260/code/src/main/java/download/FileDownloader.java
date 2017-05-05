package download;

import download.api.*;

import java.io.*;
import java.net.URL;
import java.util.Date;

/**
 * TODO:
 */
public class FileDownloader {
	private String url = null;
	private int contentLength;
	private ConnectionManager manager;
	private boolean failed = false;

	private DownloadCallback callback = new DownloadCallback();

	private final int[] completedThreadCount = new int[1];

	/**
	 * 下载一个url指向的文件，下载目录见 <code>Config</code>
	 *
	 * @see Config#targetDirectory
	 * @see #execute()
	 */
	public FileDownloader(String url) throws IOException {
		this.url = url;
		this.contentLength = new URL(url).openConnection().getContentLength();
	}

	/**
	 * 开始下载<br/>
	 * 调用这个方法前，先调用以下几个方法：<br/>{@link #setConnectionManager(ConnectionManager)}<br/>
	 * {@link #setOnCompleteListener(OnCompleteListener)}<br/>
	 * {@link #setOnFailListener(OnFailListener)}
	 */
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
		new Thread(() -> {
			initDirectories();

			int threadCount;
			try {
				threadCount = getThreadCount();
			} catch (IOException e) {
				e.printStackTrace();
				callback.callback(false);
				return;
			}
			File[] tempFiles = new File[threadCount];
			Connection[] connections = new Connection[threadCount];
			createMultiThread(threadCount, tempFiles, connections);

			waitForComplete(threadCount);
			mergeTempFiles(tempFiles);
			removeTempFiles(tempFiles);

			for (Connection c : connections) {
				if (c != null) {
					c.close();
				}
			}
			callback.callback(true);
		}).start();
	}

	private int getThreadCount() throws IOException {
		if (this.url.split(":", 2)[0].toLowerCase().startsWith("http")) {
			URL url = new URL(this.url);
			int length = url.openConnection().getContentLength();
			int count = length / Config.maxLengthPerThread;
			if (count < Config.minThreadCount) {
				return Config.minThreadCount;
			} else if (count > Config.maxThreadCount) {
				return Config.maxThreadCount;
			} else {
				return count;
			}
		} else {
			return 1;
		}
	}

	private void removeTempFiles(File[] tempFiles) {
		for (File tempFile : tempFiles) {
			tempFile.delete(); // 只删除临时文件，不删除临时目录
		}
	}

	private void mergeTempFiles(File[] tempFiles) {
		String[] split = url.replaceAll("/+", "/").split("/");
		File saveFile = new File(Config.targetDirectory, split[split.length - 1]);
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(saveFile);
			for (File tempFile : tempFiles) {
				write(tempFile, fos);
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
		}
	}

	private void waitForComplete(int threadCount) {
		while (completedThreadCount[0] < threadCount) {
			synchronized (completedThreadCount) {
				if (completedThreadCount[0] < threadCount) {
					try {
						completedThreadCount.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	private void createMultiThread(int threadCount, File[] tempFiles, Connection[] connections) {
		for (int i = 0; i < threadCount; ++i) {
			File targetFile = new File(Config.tempDirectory,
					new Date().getTime() + "_" + i);
			tempFiles[i] = targetFile;

			int startPos = (int) (1.0 * contentLength / threadCount * i);
			int endPos = i == threadCount - 1 ? contentLength : (int) (1.0 * contentLength / threadCount * (i + 1));
			endPos--;
			Connection connection = connect(startPos, endPos);
			if (connection != null) {
				connections[i] = connection;
				new DownloadThread(connection, targetFile, () -> {
					synchronized (completedThreadCount) {
						completedThreadCount[0]++;
						completedThreadCount.notifyAll();
					}
				}, () -> {
					try {
						downloadFailed(connections, tempFiles);
					} catch (DownloadException e) {
						e.printStackTrace();
					}
				}).start();
			}
		}
	}

	private Connection connect(int startPos, int endPos) {
		try {
            return manager.open(url, startPos, endPos);
        } catch (ConnectionException e) {
            e.printStackTrace();
            return null;
        }
	}

	private void initDirectories() {
		if (!Config.targetDirectory.exists()) {
			Config.targetDirectory.mkdir();
		}
		if (!Config.tempDirectory.exists()) {
			Config.tempDirectory.mkdir();
		}
	}

	private void downloadFailed(Connection[] connections, File[] tempFiles) throws DownloadException {
		for (Connection c : connections) {
			c.close();
		}
		removeTempFiles(tempFiles);
		failed = true;
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

	/**
	 *
	 * @param listener 下载成功后会调用<code>onComplete.onComplete()</code>，失败则不会调用
	 * @see OnCompleteListener#onComplete()
	 */
	public void setOnCompleteListener(OnCompleteListener listener) {
		callback.setOnComplete(listener);
	}

	/**
	 *
	 * @param listener 下载失败后会调用<code>onFail.onFail()</code>
	 * @see OnFailListener#onFail()
	 */
	public void setOnFailListener(OnFailListener listener) {
		callback.setOnFail(listener);
	}

	/**
	 *
	 * @param manager 通过url打开连接
	 * @see ConnectionManager#open(String, int, int)
	 */
	public void setConnectionManager(ConnectionManager manager) {
		this.manager = manager;
	}
}
