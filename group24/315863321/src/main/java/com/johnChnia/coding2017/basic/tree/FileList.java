package com.johnChnia.coding2017.basic.tree;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * Created by john on 2017/5/18.
 */
public class FileList {

    public TreeInfo list(File f) {
        if (Objects.isNull(f)) {
            return null;
        }
        TreeInfo treeInfo = new TreeInfo();
        if (f.isDirectory()) {
            treeInfo.dirs.add(f);
            for (File lf :
                    f.listFiles()) {
                treeInfo.addAll(list(lf));
            }
        } else {
            if (!f.getName().matches(".*\\.DS_Store"))
            treeInfo.files.add(f);
        }
        return treeInfo;
    }

    public static class TreeInfo implements Iterable<File> {
        public List<File> files = new ArrayList<>();
        public List<File> dirs = new ArrayList<>();

        @Override
        public Iterator<File> iterator() {
            return files.iterator();
        }

        public void addAll(TreeInfo other) {
            files.addAll(other.files);
            dirs.addAll(other.dirs);
        }

        @Override
        public String toString() {
            return "dirs: " + dirs +
                    "\n\nfiles: " + files;
        }
    }

}
