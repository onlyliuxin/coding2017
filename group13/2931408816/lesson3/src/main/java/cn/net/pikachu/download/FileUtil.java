package cn.net.pikachu.download;

import cn.net.pikachu.download.api.ConnectionException;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Created by pikachu on 2017/3/13.
 */
public class FileUtil {
    private static String filename = "temp";
    private static String url = null;
    public static File getFile() {
        return new File(filename);
    }
    public static void setFile(String url,int totalLen){

        File dir = new File("D:/Download/test");
        if(!dir.exists()){
            dir.mkdirs();
        }
        File file = new File(dir, getFileName(url));
        if (file.exists()){
            file.delete();
        }
        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile(file, "rws");
            System.out.println("totalLen = "+ totalLen);
            if (totalLen < 0){
                throw new ConnectionException();
            }
            raf.setLength(totalLen);
            raf.close();
            filename=file.getCanonicalPath();
        } catch (ConnectionException | IOException e) {
            e.printStackTrace();
        }
    }
    private static String getFileName(String url){
        int index = url.lastIndexOf("/");
        if (index<0){
            return "temp";
        }
        String filename = url.substring(index+1);
        if (filename.equals("")){
            return "temp";
        }else {
            return filename;
        }
    }
}
