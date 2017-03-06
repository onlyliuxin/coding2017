package download;

import download.api.Connection;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class DownloadThread extends Thread {
	Connection conn;
	int startPos;
	int endPos;

	File targetFile;
	OnCompleteListener onComplete;
	OnFailListener onFail;

	public DownloadThread(Connection conn, int startPos, int endPos, File targetFile,
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
		boolean success = false;
		int maxFailCount = 5;
		int failCount = 0;
		while (!success) {
			FileOutputStream fos = null;
			try {
				fos = new FileOutputStream(targetFile);
				tryDownload(fos);
				success = true;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				if (failCount < maxFailCount) {
					failCount++;
					try {
						recreateFile(fos, targetFile);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				} else {
					break;
				}
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

		if (onComplete != null) {
			if (success) {
				onComplete.onComplete();
			} else {
				onFail.onFail();
			}
		}
	}

	private void recreateFile(FileOutputStream old, File file) throws IOException {
		if (old != null) {
			old.close();
		}
		file.delete();
		file.createNewFile();
	}

	private void tryDownload(FileOutputStream fos) throws IOException {
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
		void onComplete();
	}
	public interface OnFailListener {
		void onFail();
	}
}
