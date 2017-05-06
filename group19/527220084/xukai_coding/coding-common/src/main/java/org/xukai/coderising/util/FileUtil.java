package org.xukai.coderising.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author xukai
 * @desc
 * @date 2017-04-02-13:22
 */
public class FileUtil {

    public static byte[] toByteArray(String fileName) throws IOException {
        File file = new File(fileName);
        return toByteArray(file);
    }

    public static byte[] toByteArray(File file) throws IOException {
        if (!file.exists()) {
            throw new FileNotFoundException();
        }
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buff = new byte[1024];
        int len = 0;
        try {
            while((len = in.read(buff)) != -1){
                out.write(buff,0,len);
            }
            return out.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
