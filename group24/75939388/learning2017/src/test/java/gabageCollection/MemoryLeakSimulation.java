package gabageCollection;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : 温友朝
 * @date : 2017/5/17
 */
public class MemoryLeakSimulation {

    public static void testOutOfMemory(){
//        List<UUID> list = new ArrayList<UUID>();
        List<byte[]> list = new ArrayList<byte[]>();
        for(;;){
            list.add(new byte[10*1024*1024]);
        }
    }

    //死循环的递归会引起stackOverFlow
    public static void testStackOverFlowError(){
        testStackOverFlowError();
    }

    public static void testOutOfMemoryPermGenSpace(){
        //出现在热部署时最多
    }

    public static void main(String[] args){
//        testOutOfMemory();
//        testStackOverFlowError();
        testOutOfMemoryPermGenSpace();
    }
}
