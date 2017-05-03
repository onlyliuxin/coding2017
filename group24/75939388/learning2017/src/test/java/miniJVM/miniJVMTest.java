package miniJVM;

import miniJVM.engine.MiniJVM;
import org.junit.Assert;
import org.junit.Test;
import thread.download.ClassFileReader;

public class miniJVMTest {

    static final String PATH = "resources/classes";

    @Test
    public void readCafebabe(){
        Assert.assertEquals("cafebabe", ClassFileReader.readNextU4Bytes(0));
    }

    @Test
    public void testMain() throws Exception{
        String[] classPaths = {PATH};
        MiniJVM jvm = new MiniJVM();
        jvm.run(classPaths, "miniJVM.EmployeeV1");
    }
}
