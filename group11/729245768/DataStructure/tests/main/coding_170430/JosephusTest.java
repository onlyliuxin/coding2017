package main.coding_170430;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by peterchen on 2017/5/4.
 */
public class JosephusTest extends TestCase {
    @Test
    public void testExecute() throws Exception {
        List<Integer> list = Josephus.execute(8,3);
        int[] arrays = new int[list.size()];
        for(int i=0;i<list.size();i++){
            arrays[i] = list.get(i);
        }
        Assert.assertArrayEquals(arrays,new int[]{3,6,1,5,2,8,4,7});
    }

}