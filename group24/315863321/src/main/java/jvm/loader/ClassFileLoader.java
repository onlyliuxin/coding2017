package jvm.loader;


import com.johnChnia.coding2017.basic.ArrayList;
import com.johnChnia.coding2017.basic.List;

import java.io.*;

/**
 * @// TODO: 2017/4/20 改成  try... with...resource
 * @// TODO: 2017/4/20 close inputstream
 * @// TODO: 2017/4/20 修改TreeInfo直接返回File
 */
public class ClassFileLoader {

    private List<String> clzPaths = new ArrayList<String>();

    public byte[] readBinaryCode(String className) {
        for (int i = 0; i < clzPaths.size(); i++) {
            // 找到指定类文件
            Directory.TreeInfo treeInfo = Directory.walk(clzPaths.get(i), className);
            if (treeInfo.files.size() > 0) {
                try {
                    FileInputStream fis = new FileInputStream(treeInfo.files.get(0));
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();  // 自动增长
                    byte[] buff = new byte[1024];
                    int len;
                    while ((len = fis.read(buff)) != -1) {
                        bos.write(buff, 0, len);
                    }
                    return bos.toByteArray();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;

    }


    public void addClassPath(String path) {
        clzPaths.add(path);
    }


    public String getClassPath() {
        StringBuilder sb = new StringBuilder();
        for (int index = 0; index < clzPaths.size(); index++) {
            sb.append(clzPaths.get(index));
            sb.append(";");
        }
        return sb.toString().substring(0, sb.length() - 1);
    }


}
