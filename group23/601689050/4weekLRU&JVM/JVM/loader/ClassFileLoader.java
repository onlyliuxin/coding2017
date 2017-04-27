package loader;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by Lxx on 2017/4/23.
 */
public class ClassFileLoader {
    private List<String> clzPaths = new ArrayList<String>();

    public byte[] readBinaryCode(String className) {

        className = className.replace('.', File.separatorChar) +".class";

        for(String path : this.clzPaths){

            String clzFileName = path + File.separatorChar + className;
            byte[] codes = loadClassFile(clzFileName);
            if(codes != null){
                return codes;
            }
        }

        return null;



    }

    private byte[] loadClassFile(String clzFileName) {

        File f = new File(clzFileName);

        try {

            return IOUtils.toByteArray(new FileInputStream(f));

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }



    public void addClassPath(String path) {
        if(this.clzPaths.contains(path)){
            return;
        }

        this.clzPaths.add(path);

    }



    public String getClassPath(){
        return StringUtils.join(this.clzPaths,";");
    }


}
