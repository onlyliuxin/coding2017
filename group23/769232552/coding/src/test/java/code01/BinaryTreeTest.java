package code01;

import org.junit.Test;

/**
 * Created by yaoyuan on 2017/3/10.
 */
public class BinaryTreeTest {

    @Test
    public void testCreateBinaryTree(){

        BinaryTree<Integer> binaryTree1 = new BinaryTree<Integer>();
        BinaryTree<Integer> binaryTree2 = new BinaryTree<Integer>();
        Integer[] array1 = new Integer[]{3,4,1,2,5};
        Integer[] array2 = new Integer[]{3,1,4,5,2};
        binaryTree1.createBinaryTree(array1);
        binaryTree2.createBinaryTree(array2);
        binaryTree1.leftOrderScan(binaryTree1.getRoot());
        binaryTree2.leftOrderScan(binaryTree2.getRoot());
    }

    @Test
    public void testInsert(){
        BinaryTree<Integer> binaryTree3 = new BinaryTree<Integer>();
    }
}