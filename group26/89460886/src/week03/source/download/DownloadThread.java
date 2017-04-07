package coding.coderising.download;

import coding.coderising.download.api.Connection;

import java.io.IOException;

/**
 * @author jiaxun
 */
public class DownloadThread extends Thread{

    private Connection connection;
    private int startPos;
    private int endPos;
    private byte[] downloadByte;

    public DownloadThread(Connection connection, int startPos, int endPos) {
        this.connection = connection;
        this.startPos = startPos;
        this.endPos = endPos;
    }

    @Override
    public void run() {
        try {
            downloadByte = connection.read(startPos, endPos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public byte[] getDownloadByte() {
        return downloadByte;
    }

    public int getStartPos() {
        return startPos;
    }

}
