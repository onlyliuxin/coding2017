package assignment0326.jvm.loader;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/30.
 */
public class ClassFileLoader {


        private List<String> clzPaths = new ArrayList<String>();

        public byte[] readBinaryCode(String className) {
            String path = className.replace(".", File.separator);
            File classFile=null;
            for (String p: clzPaths) {
                classFile=new File(p+File.separator+path+".class");
                if(classFile.exists())
                    break;
            }
            if(classFile==null)
                throw new RuntimeException("no such class file");

            byte[] bytes=new byte[(int)classFile.length()];
            try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(classFile))){
                bufferedInputStream.read(bytes, 0, bytes.length);

            } catch (IOException e) {
                e.printStackTrace();
            }
            return bytes;
        }


        public void addClassPath(String path) {
            clzPaths.add(path);
        }



        public String getClassPath(){
            StringBuilder stringBuilder=new StringBuilder();
            for (int i = 0; i <clzPaths.size()-1; i++) {
                stringBuilder.append(clzPaths.get(i) + ";");
            }
            stringBuilder.append(clzPaths.get(clzPaths.size()-1));
            return stringBuilder.toString();
        }





}
