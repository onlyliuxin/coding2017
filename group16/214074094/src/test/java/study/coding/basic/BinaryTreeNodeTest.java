package study.coding.basic;

import org.junit.Test;
import study.AbstractTest;

/**
 * @Author shane
 * @Time 2017/2/26 19:57
 * @Email shanbaohua@lxfintech.com
 * @Desc ...
 */
public class BinaryTreeNodeTest extends AbstractTest {

    @Test
    public void test(){
        BinaryTreeNode node = new BinaryTreeNode();
        node.insert(8);
        node.insert(5);
        node.insert(9);
        node.insert(1);
        node.insert(6);
        node.insert(11);
        node.insert(10);
        node.insert(15);
        node.insert(13);
        node.insert(19);

        printStar();
        System.out.println(node.getData());
        System.out.println(node);
        printStar();
    }
}
