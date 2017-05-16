package me.lzb.jvm;

import me.lzb.jvm.engine.MiniJVM;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author LZB
 */
public class MiniJVMTest {

    static String PATH = EmployeeV1.class.getResource("/").getPath();

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testMain() throws Exception {
        String[] classPaths = {PATH};
        MiniJVM jvm = new MiniJVM();
        jvm.run(classPaths, "me.lzb.jvm.EmployeeV1");

    }

    @Test
    public void testMainV2() throws Exception {
        String[] classPaths = {PATH};
        MiniJVM jvm = new MiniJVM();
        jvm.run(classPaths, "me.lzb.jvm.EmployeeV2");

    }

    @Test
    public void testMainHE() throws Exception {
        String[] classPaths = {PATH};
        MiniJVM jvm = new MiniJVM();
        jvm.run(classPaths, "me.lzb.jvm.HourlyEmployee");

    }

}
