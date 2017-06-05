package dataStructure.lru;

public class LRUPageFrame
{
    
    private static class Node
    {
        Node prev;
        Node next;
        int data;
        Node()
        {
        }
    }
    
    private int capacity;
    
    private int currentSize;
    
    private Node head;// 链表头
    
    private Node last;// 链表尾
    
    public LRUPageFrame(int capacity)
    {
        this.currentSize = 0;
        this.capacity = capacity;
        
    }
    
    /**
     * 获取缓存中对象
     * 
     * @param key
     * @return
     */
    public void access(int data)
    {
        
        Node node = find(data);
        // 在该队列中存在， 则提到队列头
        if (node != null)
        {
            moveExistingNodeToHead(node);
        }
        else
        {
            node = new Node();
            node.data = data;
            // 缓存容器是否已经超过大小.
            if (currentSize >= capacity)
            {
                removeLast();
            }
            addNewNodetoHead(node);
            
        }
    }
    
    /**
     * 将新节 点添加到链表的头部
     * 
     * @param node
     */
    private void addNewNodetoHead(Node node)
    {
        
        if (isEmpty())
        {
            node.prev = null;
            node.next = null;
            head = node;
            last = node;
        }
        else
        {
            node.prev = null;
            node.next = head;
            head.prev = node;
            head = node;
        }
        this.currentSize++;
    }
    
    private Node find(int data)
    {
        Node node = head;
        while (node != null)
        {
            if (node.data == data)
            {
                return node;
            }
            node = node.next;
        }
        return null;
    }
    
    /**
     * 删除链表尾部节点 表示 删除最少使用的缓存对象
     */
    private void removeLast()
    {
        Node prev = last.prev;
        prev.next = null;
        last.prev = null;
        last = prev;
        this.currentSize--;
    }
    
    /**
     * 移动到链表头，表示这个节点是最新使用过的
     * 
     * @param node
     */
    private void moveExistingNodeToHead(Node node)
    {
        
        if (node == head)
        {
            
            return;
        }
        else if (node == last)
        {
            // 当前节点是链表尾， 需要放到链表头
            Node prevNode = node.prev;
            prevNode.next = null;
            last.prev = null;
            last = prevNode;
            
        }
        else
        {
            // node 在链表的中间， 把node 的前后节点连接起来
            Node prevNode = node.prev;
            prevNode.next = node.next;
            
            Node nextNode = node.next;
            nextNode.prev = prevNode;
            
        }
        
        node.prev = null;
        node.next = head;
        head.prev = node;
        head = node;
        
    }
    
    private boolean isEmpty()
    {
        return (head == null) && (last == null);
    }
    
    public String toString()
    {
        StringBuilder buffer = new StringBuilder();
        Node temp = head;
        while (temp != null)
        {
            buffer.append(temp.data);
            if (temp.next != null)
            {
                buffer.append(",");
            }
            temp = temp.next;
        }
        return buffer.toString();
    }
}