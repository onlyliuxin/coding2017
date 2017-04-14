package coding.coderising.download.api;

import java.io.IOException;

/**
 * @author jiaxun
 */
public interface Connection {

    byte[] read(int startPos, int endPos) throws IOException;

    int getContentLength();

    void close();

}
