package main.coding_170327.loader;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by peter on 2017/4/20.
 */
public class ClassFileLoader {
    private List<String> clzPaths = new ArrayList<>();

    public byte[] readBinaryCodes(String className){
        className = className.replace('.', File.separatorChar)+".class";
        for(String singlePath:clzPaths){
            String clzFileName = singlePath + File.separatorChar +className;
            byte[] codes = loadClassFile(clzFileName);
            if(codes!=null){
                return codes;
            }
        }
        return null;
    }
    public byte[] loadClassFile(String clzFileName){
        File file = new File(clzFileName);

        try {
            return IOUtils.toByteArray(new FileInputStream(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void addClassPath(String path){
        if(this.clzPaths.contains(path)){
            return;
        }
        clzPaths.add(path);
    }
    public String getClassPath_V1(){
        StringBuffer buffer = new StringBuffer();
        for(int i=0;i<this.clzPaths.size();i++){
            buffer.append(this.clzPaths.get(i));
            if(i<this.clzPaths.size()-1){
                buffer.append(";");
            }
        }
        return buffer.toString();
    }
    public String getClassPath(){
        return StringUtils.join(clzPaths,";");
    }
}
