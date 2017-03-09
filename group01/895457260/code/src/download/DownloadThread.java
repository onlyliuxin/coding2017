package download;

import download.api.Connection;
import download.api.DownloadException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class DownloadThread extends Thread {
	Connection conn;
	int startPos;
	int endPos;

	File tempFile;
	OnCompleteListener onComplete;
	OnFailListener onFail;


	public DownloadThread(Connection conn, int startPos, int endPos, File tempFile,
						  OnCompleteListener onComplete, OnFailListener onFail) {
		this.conn = conn;		
		this.startPos = startPos;
		this.endPos = endPos;
		this.tempFile = tempFile;
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
		if (onComplete != null) {
			if (success) {
				onComplete.onComplete();
			} else {
				onFail.onFail();
			}
		}
	}

	private boolean tryDownload() throws DownloadException {
		FileOutputStream fos = null;
		try {
            fos = new FileOutputStream(tempFile);
            download(fos);
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
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
            recreateFile(tempFile);
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
		void onComplete();
	}

	public interface OnFailListener {
		void onFail();
	}
}
