package main.coding_170416.jvm.loader;

import main.coding_170416.jvm.clz.ClassFile;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by peter on 2017/4/21.
 */
public class ClassFileLoader {
    private List<String> clzPaths = new ArrayList<>();
    public byte[] readBinaryCode(String className){
        className = className.replace('.', File.pathSeparatorChar) +".class";
        for (String path:this.clzPaths){
            String clzFileName = path + File.separatorChar + className;
            byte[] codes = loadClassFile(clzFileName);
            if(codes!=null){
                return codes;
            }
        }
        return null;
    }

    public byte[] loadClassFile(String clzFileName){
        File f = new File(clzFileName);
        try {
            return IOUtils.toByteArray(new FileInputStream(f));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void addClassPath(String path){
        if(clzPaths.contains(path)){
            return;
        }
        clzPaths.add(path);
    }

    public String getClassPath(){
        return StringUtils.join(this.clzPaths,";");
    }
    public ClassFile loadClass(String className){
        byte[] codes = this.readBinaryCode(className);
        ClassFileParse parser = new ClassFileParse();
        return parser.parse(codes);
    }
    public String getClassPath_V1(){
        StringBuffer sb = new StringBuffer();
        for(int i=0;i<this.clzPaths.size();i++){
            sb.append(this.clzPaths.get(i));
            if(i<this.clzPaths.size()-1){
                sb.append(";");
            }
        }
        return sb.toString();
    }

    public byte[] loadClassFile_V1(String clzFileName){
        BufferedInputStream bis = null;
        File f = new File(clzFileName);
        try {
            bis = new BufferedInputStream(new FileInputStream(f));
            ByteArrayOutputStream bos = new ByteArrayOutputStream();

            byte[] buffer = new byte[1024];
            int length = -1;
            while ((length=bis.read(buffer))!=-1){
                bos.write(buffer,0,length);
            }
            byte[] codes = bos.toByteArray();
            return codes;
        }  catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(bis!=null){
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
