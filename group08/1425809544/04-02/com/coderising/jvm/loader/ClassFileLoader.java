package com.coderising.jvm.loader;

import com.google.common.base.Joiner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ClassFileLoader {

    //后缀名
    private static String ClASS_SUFFIX = ".class";
    //用";"连接字符串,过滤空
    private static final Joiner SEMICOLON_JOINER = Joiner.on(";").skipNulls();

    private List<String> clzPaths = new ArrayList<String>();


    public byte[] readBinaryCode(String className) throws FileNotFoundException {
        byte[] binBytes;
        for (String path : clzPaths) {
            String clzPath = path + className.replace(".", "/") + ClASS_SUFFIX;
            try {
                binBytes = getBytes(clzPath);
                return binBytes;
            } catch (Exception e) {

            }
        }
        return null;
    }


    /**
     * @Author xuyangyang
     * @Describe 获取字节数组
     * @Date 2017/4/5
     * @Params
     * @Return
     */
    private byte[] getBytes(String clzPath) throws IOException {
        byte[] binBytes;
        byte[] buffer = new byte[1024];//二进制数组
        int size = 0;
        int index = 0;
        InputStream in = new FileInputStream(clzPath);
        binBytes = new byte[in.available()];//获取文件的大小
        while ((size = in.read(buffer)) != -1) {
            for (int i = 0; i < size; i++) {
                binBytes[index++] = buffer[i];
            }
        }
        return binBytes;
    }

    public void addClassPath(String path) {

        if (!clzPaths.contains(path)) {
            clzPaths.add(path);
        }
    }

    public String getClassPath() {
        return SEMICOLON_JOINER.join(clzPaths);//list转为string,用";"连接起来
    }

}
