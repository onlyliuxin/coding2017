package download;

import download.api.Connection;
import download.api.DownloadException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class DownloadThread extends Thread {
	private Connection conn;
	private int startPos;
	private int endPos;

	private File targetFile;
	private OnCompleteListener onComplete;
	private OnFailListener onFail;

	/**
	 *
	 * @param conn url连接
	 * @param startPos 此线程会从url所指向文件的startPos处开始下载
	 * @param endPos 此线程会在url所指向文件的endPos处停止下载
	 * @param targetFile 保存下载内容的文件
	 * @param onComplete 下载成功后自动调用
	 * @param onFail 下载失败后自动调用
	 *
	 * @see OnCompleteListener#onComplete()
	 * @see OnFailListener#onFail()
	 */
	DownloadThread(Connection conn, int startPos, int endPos, File targetFile,
						  OnCompleteListener onComplete, OnFailListener onFail) {
		this.conn = conn;		
		this.startPos = startPos;
		this.endPos = endPos;
		this.targetFile = targetFile;
		this.onComplete = onComplete;
		this.onFail = onFail;
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
		callback(success);
	}

	private void callback(boolean success) {
		if (success) {
			if (onComplete != null) {
				onComplete.onComplete();
			}
		} else {
			if (onFail != null) {
				onFail.onFail();
			}
		}
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
        } catch (IOException e1) {
            e1.printStackTrace();
        }
	}

	private void recreateFile(File file) throws IOException {
		file.delete();
		file.createNewFile();
	}

	private void download(FileOutputStream fos) throws IOException {
		int bufSize = 1024;
		int from = startPos;
		while (from < endPos) {
			int to = Math.min(from + bufSize, endPos);
			byte[] buf = conn.read(from, to);
			from = to;
			fos.write(buf);
			fos.flush();
		}
	}

	public interface OnCompleteListener {
		/**
		 * 下载成功后自动调用此方法
		 */
		void onComplete();
	}

	public interface OnFailListener {
		/**
		 * 下载失败后自动调用此方法
		 */
		void onFail();
	}
}
