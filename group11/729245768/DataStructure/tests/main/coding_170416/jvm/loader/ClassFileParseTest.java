package main.coding_170416.jvm.loader;

import junit.framework.TestCase;
import main.coding_170416.jvm.clz.ClassFile;
import main.coding_170416.jvm.field.Field;
import main.coding_170416.jvm.method.Method;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by peter on 2017/4/23.
 */
public class ClassFileParseTest extends TestCase {
    static String path1 = "E:\\IDEASpace\\coding2017\\group11\\729245768\\DataStructure\\tests";

    static ClassFile clzFile = null;
    static {
        ClassFileLoader loader = new ClassFileLoader();
        loader.addClassPath(path1);
        String className = "main.coding_170416.Employee";
        clzFile = loader.loadClass(className);
    }

    @Test
    public void testParseFields() throws Exception {
        List<Field> fields = clzFile.getFields();
        Assert.assertEquals(2,fields.size());
        Field f1 = fields.get(0);
        Assert.assertEquals("name:Ljava/lang/String;", f1.toString());
        Field f2 = fields.get(1);
        Assert.assertEquals("age:;I", f2.toString());
    }

    @Test
    public void testParseMethods() throws Exception {
        List<Method> methods = clzFile.getMethods();
        Assert.assertEquals(5,methods.size());
    }

}