package jvm;

import jvm.exception.MagicNumberException;
import jvm.util.ArrayUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Haochen on 2017/3/26.
 * TODO:
 */
public enum LiteJvm {
    INSTANCE;

    public static String classPath;

    public void launch(File file) throws MagicNumberException, IOException {
        byte[] bytes = getBytes(file);
        if (!checkMagicNumber(bytes)) {
            throw new MagicNumberException();
        }
    }

    private boolean checkMagicNumber(byte[] bytes) {
        String magicNumber = "CAFEBABE";
        String str = "";
        int byteNum = 4;
        for (int i = 0; i < byteNum; ++i) {
            str += Integer.toHexString(Byte.toUnsignedInt(bytes[i]));
        }
        return magicNumber.equals(str.toUpperCase());
    }

    private byte[] getBytes(File file) throws IOException {
        InputStream is = new FileInputStream(file);
        List<Byte> bytes = new ArrayList<>();
        byte[] buf = new byte[1024];
        int len;
        while ((len = is.read(buf)) != -1) {
            bytes.addAll(ArrayUtils.toList(buf, 0, len));
        }
        return ArrayUtils.toArray(bytes);
    }
}
