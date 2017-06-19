package code10;

import java.io.File;

/**
 * Created by on 2017/5/9.
 */
public class FileList {

    public void list(File file){
        list(file,0);
    }

    public void list(File file,int layer){
        showFileName(file,layer);
        if(file.isDirectory()){
            File[] files = file.listFiles();
            for(File f : files){
                list(f,layer+1);
            }
        }
    }

    public void showFileName(File file,int i){
        for (int j = 0; j < i; j++) {
            System.out.print(" - ");
        }
        System.out.println(file.getName());
    }

    public static void main(String[] args) {
        FileList fileList = new FileList();
        String path = "D:\\dir1";
        File file = new File(path);
        fileList.list(file);
    }

}
