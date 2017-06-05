package tree;

import java.io.File;

/**
 * @author jiaxun
 */
public class FileList {

    public void list(File f) {
        listFile(f);
    }

    private void listFile(File file) {
        if (file.isFile()) {
            System.out.println("file is " + file.getPath());
            return;
        }
        if (file.isDirectory()) {
            listFile(file);
        }
    }

}
