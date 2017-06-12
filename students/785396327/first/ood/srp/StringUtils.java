package first.ood.srp;

/**
 * Created by gongxun on 2017/6/12.
 */
public class StringUtils {

    /**
     * 判断文件路径是否为空
     *
     * @param filePath
     * @return
     */
    public static boolean isEmpty(String filePath) {
        return filePath == null || filePath.trim().isEmpty();
    }
}
