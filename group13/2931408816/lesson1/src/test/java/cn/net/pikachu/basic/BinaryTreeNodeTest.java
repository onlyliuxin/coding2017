package cn.net.pikachu.basic; 

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After; 

/** 
* BinaryTreeNode Tester. 
* 
* @author pikachu 
* @since <pre>二月 25, 2017</pre> 
* @version 1.0 
*/ 
public class BinaryTreeNodeTest { 

    BinaryTreeNode tree;
    @Before
    public void before() throws Exception { 
        tree = new BinaryTreeNode();
    } 

    @After
    public void after() throws Exception { 
        
    } 

    /** 
    * 
    * Method: insert(Object o) 
    * 
    */ 
    @Test
    public void testInsert() {
        for (int i = 0; i < 4; i++) {
            tree.insert(i);
        }
        final StringBuilder builder = new StringBuilder();
        BinaryTreeNode.Visit visit = new BinaryTreeNode.Visit() {
            @Override
            public void visit(Object o) {
                builder.append(o).append(",");
            }
        };
        builder.append("[");
        tree.inOrderTraversal(visit,tree);
        if (builder.length()>2){
            builder.deleteCharAt(builder.length()-1);
        }
        builder.append("]");
        Assert.assertEquals("[0,1,2,3]",builder.toString());
        
    } 


} 
