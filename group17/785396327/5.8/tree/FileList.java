package tree;

import queue.Queue;

import java.io.File;

/**
 * Created by william on 2017/5/8.
 */
public class FileList {
    private static final String path = "G:\\Git\\homework";

    public static void main(String[] args) {
        Queue<File> queue = new Queue<File>();
        File rootFile = new File(path);
        queue.offer(rootFile);
        while (!queue.isEmpty()) {
            File file = queue.poll();
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                if (files != null) {
                    for (int i = 0; i < files.length; i++) {
                        File child = files[i];
                        queue.offer(child);
                    }
                }
            } else {
                System.out.println(file.getName());
            }
        }
    }

    public void list(File f) {
    }

}
