package me.lzb.boom;

import me.lzb.common.utils.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * @author LZB
 */
public class BoomClassLoader extends ClassLoader {


    //类加载器的名称
    private String name;
    private String path;

    BoomClassLoader(String name, String path) {
        this.name = name;
        this.path = path;
    }

    BoomClassLoader(ClassLoader parent, String name) {
        super(parent);
        this.name = name;
    }

    /**
     * 重写findClass方法
     */
    @Override
    public Class<?> findClass(String name) {
        byte[] data = new byte[0];
        try {
            data = FileUtils.readByteCodes(path + name.replace('.', File.separatorChar) + ".class");
        } catch (IOException e) {
        }
        return this.defineClass(name, data, 0, data.length);
    }

}
