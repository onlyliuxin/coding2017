package week4.loader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhouliang on 2017-04-04.
 */
public class ClassFileLoader {
    private List<String> clzPaths = new ArrayList<String>();

    public byte[] readBinaryCode(String className) {
        className = className.replace(".","\\");
        if(!className.endsWith(".class")){
            className=className+".class";
        }
        File file = null;
        for(String path : clzPaths){
            file = new File(path+"\\"+className);
            if(file.exists()){
                break;
            }
        }

        if(file==null){
            try {
                throw  new FileNotFoundException();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            return null;
        }else{
            byte[] buffer = new byte[1024];
            BufferedInputStream bufferedInputStream = null;
            ByteArrayOutputStream byteArrayOutputStream = null;
            try {
                bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
                byteArrayOutputStream = new ByteArrayOutputStream();
                int length = 0;
                while((length = bufferedInputStream.read(buffer))!= -1){
                    byteArrayOutputStream.write(buffer,0,length);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return byteArrayOutputStream.toByteArray();
        }
    }


    public void addClassPath(String path) {
        clzPaths.add(path);
    }

    public String getClassPath(){
        StringBuilder stringBuilder = new StringBuilder();
        for(int i=0; i<clzPaths.size(); i++){
            if(i!=clzPaths.size()-1){
                stringBuilder.append(clzPaths.get(i)).append(";");
            }else{
                stringBuilder.append(clzPaths.get(i));
            }
        }
        return stringBuilder.toString();
    }
}
