package test01.tree;

import java.util.ArrayList;
import java.util.List;

public class TreeNode<T> {
    public T t;
    private TreeNode<T> parent;
    
    public List<TreeNode<T>> nodelist;
    
    public TreeNode(T stype){
        t      = stype;
        parent = null;
        nodelist = new ArrayList<TreeNode<T>>();
    }

    public TreeNode<T> getParent() {
        return parent;
    }    
}