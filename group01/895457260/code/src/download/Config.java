package download;

import java.io.File;

/**
 * Created by Haochen on 2017/3/6.
 *
 */
public class Config {
    /**
     * 保存下载文件的目录
     */
    public static File targetDirectory = new File("download/");
    public static File tempDirectory = new File(targetDirectory, "temp/");
}
