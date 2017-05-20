package task10;

import java.io.File;
import java.util.Arrays;

public class FileList {

    public void list(File f) {
        if (!f.isFile()) {
            File[] files = f.listFiles();
            if (files != null && f.length() > 0)
                Arrays.stream(files).forEach(this::list);
            return;
        }
        System.out.println(f.getAbsolutePath() + f.getName());
    }
}
