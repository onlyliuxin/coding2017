package com.coding.common.util;

import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by luoziyihao on 5/2/17.
 */
public abstract class IOUtils2 {

    public static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                throw new IllegalStateException(e);

            }
        }
    }


}
