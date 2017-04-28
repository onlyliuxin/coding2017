package com.coding.common.util;

import com.google.common.base.Preconditions;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by luoziyihao on 4/28/17.
 */
public abstract class FileUtils {

    public static List<File> listAllFiles(File directory) {
        Preconditions.checkNotNull(directory);
        Preconditions.checkArgument(directory.isDirectory()
                , "file=%s is not directory", directory.getAbsolutePath());
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
}
