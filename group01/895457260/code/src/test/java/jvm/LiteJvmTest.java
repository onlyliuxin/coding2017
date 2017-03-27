package jvm;

import jvm.LiteJvm;
import jvm.exception.MagicNumberException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * LiteJvm Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>三月 26, 2017</pre>
 */
public class LiteJvmTest {

    private LiteJvm jvm = LiteJvm.INSTANCE;
    private File file;

    @Before
    public void before() throws Exception {
        file = new File("target/classes/algorithm/ArrayUtil.class");
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: launch(File file)
     */
    @Test
    public void testLaunch() {
//TODO: Test goes here...
        try {
            jvm.launch(file);
        } catch (MagicNumberException | IOException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }


    /**
     * Method: checkMagicNumber(byte[] bytes)
     */
    @Test
    public void testCheckMagicNumber() throws Exception {
//TODO: Test goes here... 
//        try {
//            Method method = LiteJvm.class.getDeclaredMethod("checkMagicNumber", byte[].class);
//            method.setAccessible(true);
//            method.invoke(jvm, ???);
//        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
//            e.printStackTrace();
//        }
    }

    /**
     * Method: getBytes(File file)
     */
    @Test
    public void testGetBytes() throws Exception {
//TODO: Test goes here...
        try {
            Method method = LiteJvm.class.getDeclaredMethod("getBytes", File.class);
            method.setAccessible(true);
            byte[] bytes = (byte[]) method.invoke(jvm, file);
            Assert.assertEquals(3851, bytes.length);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

} 
