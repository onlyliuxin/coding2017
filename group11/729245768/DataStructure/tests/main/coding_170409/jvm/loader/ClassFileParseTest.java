package main.coding_170409.jvm.loader;

import junit.framework.TestCase;
import main.coding_170409.jvm.constant.ConstantPool;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by peter on 2017/4/23.
 */
public class ClassFileParseTest extends TestCase {
    static String path1 = "E:\\IDEASpace\\coding2017\\group11\\729245768\\DataStructure\\tests";

    @Test
    public void testParse() throws Exception {
        ClassFileLoader loader = new ClassFileLoader();
        loader.addClassPath(path1);
        String clzName = "main.coding_170409.Employee";
        byte[] codes = loader.readBinaryCode(clzName);
        ClassFileParse parse = new ClassFileParse();
        ConstantPool pool = parse.parse(codes);
        Assert.assertEquals(54,pool.getSizes());
    }

}