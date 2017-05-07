package jvm;

import jvm.exception.MagicNumberException;
import jvm.exception.ReadClassException;

/**
 * Created by Haochen on 2017/3/26.
 * TODO:
 */
public enum LiteJvm {
    INSTANCE;

    private ClassFileLoader classFileLoader = new ClassFileLoader();

    public void launch(String className) throws MagicNumberException, ReadClassException {
        byte[] bytes = getBytes(className);
        if (!classFileLoader.checkMagicNumber(bytes)) {
            throw new MagicNumberException();
        }
    }

    private byte[] getBytes(String className) throws ReadClassException {
        return classFileLoader.readBinaryCode(className);
    }
}
