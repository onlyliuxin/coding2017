package download;

import download.api.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class DownloadThread extends Thread {
	private Connection conn;
	private File targetFile;
	private DownloadCallback callback = new DownloadCallback();

	/**
	 * @param conn url连接
	 * @param targetFile 保存下载内容的文件
	 * @param onComplete 下载成功后自动调用
	 * @param onFail 下载失败后自动调用
	 *
	 * @see OnCompleteListener#onComplete()
	 * @see OnFailListener#onFail()
	 */
	DownloadThread(Connection conn, File targetFile,
						  OnCompleteListener onComplete, OnFailListener onFail) {
		this.conn = conn;
		this.targetFile = targetFile;
		callback.setOnComplete(onComplete);
		callback.setOnFail(onFail);
	}

	@Override
	public void run() {
		int maxFailCount = 5;
		int failCount = 0;
		boolean success = false;
		while (!success) {
			try {
				success = tryDownload();
			} catch (DownloadException e) {
				if (failCount < maxFailCount) {
					failCount++;
					retry();
				} else {
					break;
				}
			}
		}
		callback.callback(success);
	}

	private boolean tryDownload() throws DownloadException {
		FileOutputStream fos = null;
		try {
            fos = new FileOutputStream(targetFile);
            download(fos);
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
			e.printStackTrace();
			throw new DownloadException();
		} finally {
            if (fos != null) {
                try {
					fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
	}

	private void retry() {
		try {
            recreateFile(targetFile);
            conn.reset();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	private void recreateFile(File file) throws IOException {
		file.delete();
		file.createNewFile();
	}

	private void download(FileOutputStream fos) throws IOException {
		int bufSize = 1024;
		byte[] buf = new byte[bufSize];
		int len;
		while ((len = conn.read(buf)) != -1) {
			fos.write(buf, 0, len);
			fos.flush();
		}
	}
}
