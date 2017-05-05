package com.aaront.exercise.jvm.utils.string;

import java.io.File;

/**
 * @author tonyhui
 * @since 17/3/31
 */
public class FileUtils {

    public static Boolean isDictionary(String path) {
        File f = new File(path);
        return f.isDirectory();
    }

}
