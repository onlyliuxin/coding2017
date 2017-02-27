package basic;

import java.util.Comparator;

/**
 * 实现二叉排序树，使左节点的值，始终父节点小，右节点的值，始终比父节点大
 * @author Wayss
 * 2017-02-25
 */

public class MySortBinaryTree {
    
    public BinaryTreeNode root = new BinaryTreeNode();
    
    /**
     * 1. 添加时，先判断与root节点值的大小
     * 2. insert的值小于root的值，则插入到root的左边。
     * 3. insert的值大于root的值，则插入到root的右边。
     * PS:目前只支持Integer类型的对象插入
     * @param val
     */
    public void add(Integer val){
        BinaryTreeNode treeNode = new BinaryTreeNode(val);
        insert(root, treeNode);
    }
    
    private void insert(BinaryTreeNode node, BinaryTreeNode insertNode){
        
        int result = compare((Integer)insertNode.getData(),(Integer)node.getData());
        
        if(0 == result){
            //相等的话，当重复数据，不插了.呵呵
        }
        //insert的值小于root的值，则插入到root的左边。
        //如果左节点有值，则递归
        if(-1 == result){
            if(node.getLeft() != null){
                insert(node.getLeft(), insertNode);
            }else{
                node.setLeft(insertNode);
            }
        }
        //insert的值大于root的值，则插入到root的右边。
        if(1 == result){
            if(node.getRight() != null){
                insert(node.getRight(), insertNode);
            }else{
                node.setRight(insertNode);
            }
        }
    }
    
    public MyArrayList getValue(){
        MyArrayList malist = new MyArrayList();
        getTreeValue(root,malist);
        return malist;
    }
    
    private void getTreeValue(BinaryTreeNode node,MyArrayList list){
        //遍历左子数
        if(node.getLeft() != null){
            getTreeValue(node.getLeft(),list); 
        }
        list.add(node.getData());
        //遍历右子数
        if(node.getRight() != null){
            getTreeValue(node.getRight(),list);
        }
    }
    
    public static int compare(Integer i1, Integer i2){
        if(i1 < i2){
            return -1;
        }else if(i1 == i2){
            return 0;
        }else {
            return 1;
        }
    }
    
}
