package download;

import java.io.File;

/**
 * Created by Haochen on 2017/3/6.
 *
 */
public class Config {
    public static final String packageName = "download";

    /**
     * 保存下载文件的目录
     */
    public static File targetDirectory = new File("download/");
    public static File tempDirectory = new File(targetDirectory, "temp/");

    public static int maxLengthPerThread = 10 * 1024 * 1024;
    public static int minThreadCount = 3;
    public static int maxThreadCount = 8;
}
