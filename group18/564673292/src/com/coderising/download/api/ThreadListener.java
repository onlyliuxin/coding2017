package com.coderising.download.api;

import java.io.IOException;

/**
 * Created by lqt0223 on 2017/3/11.
 */
public interface ThreadListener {
    void onComplete(byte[] buffer) throws IOException;
}
