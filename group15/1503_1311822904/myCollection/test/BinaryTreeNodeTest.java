import org.junit.Test;

/**
 * Created by Administrator on 2017/2/25.
 */
public class BinaryTreeNodeTest {


    @Test
    public void showAll() throws Exception {
        BinaryTreeNode b=new BinaryTreeNode();
        b.insert(4);
        b.insert(5);
       b.insert(-1);
       b.insert(44);
        b.insert(34);
        b.insert(49);
        b.showAll();
    }

}
