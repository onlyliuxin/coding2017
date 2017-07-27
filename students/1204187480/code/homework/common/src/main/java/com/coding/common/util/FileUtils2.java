package com.coding.common.util;

import com.google.common.base.Preconditions;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by luoziyihao on 4/28/17.
 */
public abstract class FileUtils2 {

    public static List<File> listAllFiles(File directory) {
        Preconditions.checkNotNull(directory);
        Preconditions.checkArgument(directory.isDirectory()
                , "file=%s is not directory", directory.getPath());
        return listAllFiles(new ArrayList<>(), directory);
    }

    private static List<File> listAllFiles(List<File> files, File directory) {
        File[] fileArr = directory.listFiles();
        if (fileArr == null) {
            return files;
        }
        for (File file : fileArr) {
            if (file.isDirectory()) {
                files = listAllFiles(files, file);
            } else {
                files.add(file);
            }
        }
        return files;
    }


    public static String getCanonicalPath(File file) {
        Preconditions.checkNotNull(file);
        try {
            return file.getCanonicalPath();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }


    public static byte[] getBytes(File classFile) {
        // byteArrayOutputStream, 可写的动长数组
        ByteArrayOutputStream baos = null;
        BufferedInputStream bis = null;
        try {
            baos = new ByteArrayOutputStream();
            bis = new BufferedInputStream(new FileInputStream(classFile));
            int intTmp = bis.read();
            while (intTmp != -1) {
                baos.write(intTmp);
                intTmp = bis.read();
            }
            return baos.toByteArray();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        } finally {
            IOUtils2.close(baos);
            IOUtils2.close(bis);
        }
    }

    public static InputStream openStream(String classPath) {
        return ClassLoader.getSystemResourceAsStream(classPath);
    }

}
