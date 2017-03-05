package rui.study.coding2017;

/**
 * 二叉树
 * Created by 赵睿 on 2017/2/25.
 */
public class BinaryTree {
    private BinaryTreeNode root;

    private int size;

    public void insert(Comparable comparable){
        BinaryTreeNode binaryTreeNode=new BinaryTreeNode(comparable);

        if(this.root==null){
            this.root=binaryTreeNode;
        }else {
            boolean flag=false;
            BinaryTreeNode cursorNode=root;
            while(!flag){
                if(comparable.compareTo(cursorNode.getData())<0){
                    if(cursorNode.getLeft()==null){
                        cursorNode.setLeft(binaryTreeNode);
                        flag=true;
                    }else{
                        cursorNode=cursorNode.getLeft();
                    }
                }else {
                    if(cursorNode.getRight()==null){
                        cursorNode.setRight(binaryTreeNode);
                        flag=true;
                    }else{
                        cursorNode=cursorNode.getRight();
                    }
                }

            }
        }
        size++;
    }

    public LinkedList inorder(){
        LinkedList linkedList=new LinkedList();
        sortLeft(linkedList,root);
        sortRight(linkedList,root);
        return linkedList;
    }

    private void sortRight(LinkedList linkedList,BinaryTreeNode binaryTreeNode){
        Queue queue=getRightList(binaryTreeNode);
        while(!queue.isEmpty()){
            BinaryTreeNode queueNode = (BinaryTreeNode) queue.deQueue();
            sortLeft(linkedList,queueNode);
        }

    }

    private void sortLeft(LinkedList linkedList,BinaryTreeNode binaryTreeNode){
        Stack stack=getLeftList(binaryTreeNode);
        while(!stack.isEmpty()) {
            BinaryTreeNode stackNode = (BinaryTreeNode) stack.pop();
            linkedList.add(stackNode.getData());
            Queue queue = getRightList(stackNode);
            while (!queue.isEmpty()) {
                BinaryTreeNode queueNode = (BinaryTreeNode) queue.deQueue();
                sortLeft(linkedList,queueNode);
            }
        }
        linkedList.add(binaryTreeNode.getData());
    }


    private Stack getLeftList(BinaryTreeNode binaryTreeNode){
        Stack stack=new Stack();
        while(binaryTreeNode.getLeft()!=null){
            binaryTreeNode=binaryTreeNode.getLeft();
            stack.push(binaryTreeNode);
        }
        return stack;
    }

    private Queue getRightList(BinaryTreeNode binaryTreeNode){
        Queue queue=new Queue();
        while(binaryTreeNode.getRight()!=null){
            binaryTreeNode=binaryTreeNode.getRight();
            queue.enQueue(binaryTreeNode);
        }
        return queue;
    }



}
