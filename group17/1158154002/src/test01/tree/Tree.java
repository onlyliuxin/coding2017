package test01.tree;

public class Tree<T> {
    
    public TreeNode<T> root;
    
    public Tree(){}
        
    public void addNode(TreeNode<T> node, T newNode){
        //增加根节点
        if(null == node){
            if(null == root){
                root = new TreeNode(newNode);
            }
        }else{
                TreeNode<T> temp = new TreeNode(newNode);
                node.nodelist.add(temp);
        }
    }
    
    /*    查找newNode这个节点 */
    public TreeNode<T> search(TreeNode<T> input, T newNode){
    
        TreeNode<T> temp = null;
        
        if(input.t.equals(newNode)){
            return input;
        }
        
        for(int i = 0; i < input.nodelist.size(); i++){
            
            temp = search(input.nodelist.get(i), newNode);
            
            if(null != temp){
                break;
            }    
        }
        
        return temp;
    }
    
    public TreeNode<T> getNode(T newNode){
        return search(root, newNode);
    }
    
    public void showNode(TreeNode<T> node){
        if(null != node){
            //循环遍历node的节点
            System.out.println(node.t.toString());
            
            for(int i = 0; i < node.nodelist.size(); i++){
                showNode(node.nodelist.get(i));
            }            
        }
    }
}