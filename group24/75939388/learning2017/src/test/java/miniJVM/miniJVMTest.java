package miniJVM;

import thread.download.ClassFileReader;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author : 温友朝
 * @date : 2017/4/18
 */
public class miniJVMTest {

    @Test
    public void readCafebabe(){
        Assert.assertEquals("cafebabe", ClassFileReader.readNextU4Bytes(0));
    }


}
