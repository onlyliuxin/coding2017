package me.lzb.utils;

import java.io.*;

/**
 * Created by LZB on 2017/4/4.
 */
public class FileUtils {

    /**
     * 判断文件是否存在
     *
     * @param path 路径
     * @param name 文件名
     * @return true false
     */
    public static boolean isFileExist(String path, String name) {
        File file = new File(path + "\\" + name);
        return file.exists();
    }

    /**
     * 读取文件为二进制数组
     * @param clzFileName 文件路径
     * @return 数组
     * @throws IOException
     */
    public static byte[] readByteCodes(String clzFileName) throws IOException {

        File f = new File(clzFileName);

        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(f));

        ByteArrayOutputStream bos = new ByteArrayOutputStream();


        byte[] buffer = new byte[1024];
        int length = -1;
        try {
            while ((length = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, length);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (bis != null) {
                bis.close();
            }
            if (bos != null) {
                bos.close();
            }
        }

        byte[] codes = bos.toByteArray();

        bis.close();

        return codes;
    }


}
