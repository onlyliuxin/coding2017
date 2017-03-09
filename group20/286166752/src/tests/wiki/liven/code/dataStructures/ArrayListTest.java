package wiki.liven.code.dataStructures;

import org.junit.Before;
import org.junit.Test;


/**
 * Created by leven on 2017/2/26.
 */
public class ArrayListTest {

    private ArrayList arrayList;


    @Before
    public void init(){
        arrayList = new ArrayList();
    }


    @Test
    public void add() throws Exception {
        arrayList.add("梅子黄时雨");
        arrayList.add("行下一首歌");
        arrayList.add("AmberTseng");
        arrayList.add("梅子黄时雨");
        arrayList.add("行下一首歌");
        arrayList.add("AmberTseng");
        arrayList.add("梅子黄时雨");
        arrayList.add("行下一首歌");
        arrayList.add("AmberTseng");
        arrayList.add("梅子黄时雨");
        arrayList.add("行下一首歌");
        arrayList.add("AmberTseng");
        System.out.println(arrayList.toString());
    }

    @Test
    public void add1() throws Exception {
        arrayList.add("梅子黄时雨");
        arrayList.add("行下一首歌");
        System.out.println(arrayList.size());
        arrayList.add(1,"星际游侠");
        System.out.println(arrayList.toString());
    }

    @Test
    public void get() throws Exception {
        arrayList.add("梅子黄时雨");
        arrayList.add("行下一首歌");
        arrayList.add("AmberTseng");
        arrayList.add("梅子黄时雨");
        arrayList.add("行下一首歌");
        arrayList.add("AmberTseng");
        arrayList.add("梅子黄时雨");
        arrayList.add("行下一首歌");
        arrayList.add("AmberTseng");
        arrayList.add("梅子黄时雨");
        arrayList.add("行下一首歌");
        arrayList.add("AmberTseng");
        System.out.println(arrayList.get(1));
    }

    @Test
    public void remove() throws Exception {
        arrayList.add("梅子黄时雨");
        arrayList.add("行下一首歌");
        System.out.println(arrayList.remove(1));
        System.out.println(arrayList.toString());
    }

    @Test
    public void size() throws Exception {
        arrayList.add("梅子黄时雨");
        arrayList.add("行下一首歌");
        arrayList.add("AmberTseng");
        arrayList.add("梅子黄时雨");
        arrayList.add("行下一首歌");
        arrayList.add("AmberTseng");
        arrayList.add("梅子黄时雨");
        arrayList.add("行下一首歌");
        arrayList.add("AmberTseng");
        arrayList.add("梅子黄时雨");
        arrayList.add("行下一首歌");
        arrayList.add("AmberTseng");
        System.out.println(arrayList.size());
    }

}