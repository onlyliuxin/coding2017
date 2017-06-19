package com.coding.common.util;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

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


    public static List<String> readToStringList(InputStream inputStream) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(inputStream));
            return br.lines().collect(Collectors.toList());
        } finally {
            close(br);
        }

    }

}
