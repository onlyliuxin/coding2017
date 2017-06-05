package org.xukai.common;

import org.junit.Test;
import org.xukai.jvm.test.EmployeeV1;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * @author xukai
 * @desc
 * @date 2017-05-30-13:05
 */
public class OutOfMemoryTest {


    @Test
    public void testOutOfMemory() {

        LinkedList list = new LinkedList();
        while (true) {

            EmployeeV1 v1 = new EmployeeV1("okdd", 333);
            list.add(v1);
        }

    }

    @Test
    public void testPermGenError() throws MalformedURLException, ClassNotFoundException {
        URL url = new File("D:\\java\\IDEA-Workspace\\coding2017\\group19\\527220084\\xukai_coding\\coding-common\\target" +
                "\\classes\\org\\xukai\\jvm\\clz\\").toURL();

        ArrayList list = new ArrayList<ClassLoader>();
        while (true) {
            URLClassLoader loader = new URLClassLoader(new URL[]{url});
            Class<?> cls = loader.loadClass("org.xukai.jvm.clz.ClassFile");
            list.add(loader);
        }

    }
}
