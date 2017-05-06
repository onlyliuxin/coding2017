package me.lzb.basic;

/**
 * 用双向链表实现LRU算法
 * @author lzb
 *
 */
public class LRUPageFrame {

	private static class Node {

		Node prev;
		Node next;
		int pageNum;

		Node(Node prev, Node next, int pageNum) {
		    this.prev = prev;
		    this.next = next;
            this.pageNum = pageNum;
		}
	}

	private int capacity;


	private Node first;// 链表头
	private Node last;// 链表尾


	public LRUPageFrame(int capacity) {
        if(capacity < 1){
//            throw new Exception("capacity boom");
        }
		this.capacity = capacity;

	}

	/**
	 * 获取缓存中对象
	 *
	 * @param pageNum
	 * @return
	 */
	public void access(int pageNum) {
        if(capacity == 1){
	        first = last = new Node(null, null, pageNum);
            return;
        }


        if(first == null){
            first = last = new Node(null, null, pageNum);
            return;
        }

        if(first.pageNum == pageNum){
            return;
        }

        Node tmp = first;
        int size = 0;
        for (int i = 0; i < capacity; i++) {
            size = size + 1;
            //如果发现一样的,把这个挪到最前面
            if(tmp.pageNum == pageNum){
                moveToFirst(tmp);
                return;
            }
            //链表已经循环结束，但是个数还没满，更新last
            if(tmp.next == null){
                last = tmp;
                break;
            }
            tmp = tmp.next;
        }


        //没有相同的，在最顶端插入
        Node f = new Node(null, first, pageNum);
        addAsFirst(f);

        //已经放满，更新last
        if(size >= capacity){
            removeLastOne();
        }

	}

    /**
     * 删除最后一个节点
     */
	private void removeLastOne(){
        last = last.prev;
        //使GC ROOT 不可达
        last.next.prev = null;
        last.next = null;
    }

    /**
     * 把某节点移动到最顶部
     * @param tmp 在链表中的任意节点
     */
	private void moveToFirst(Node tmp){
	    if(tmp == first){
	        return;
        }

        if (tmp.next != null){
            tmp.next.prev = tmp.prev;
            tmp.prev.next = tmp.next;
        }else {
            tmp.prev.next = null;
            //当这个节点是last的时候，更新last
            last = tmp.prev;
        }

        tmp.next = first;
        tmp.prev = null;

        first.prev = tmp;
        first = tmp;
    }

    /**
     * 在顶部增加一个节点
     * @param node node
     */
	private void addAsFirst(Node node){
        first.prev = node;
        first = node;
    }



    /**
     * ASC
     * @return
     */
	public String toString(){
		StringBuilder buffer = new StringBuilder();
		Node node = first;
		while(node != null){
			buffer.append(node.pageNum);

			node = node.next;
			if(node != null){
				buffer.append(",");
			}
		}
		return buffer.toString();
	}


    /**
     * DESC
     * @return
     */
    public String toStringDESC(){

        StringBuilder buffer = new StringBuilder();
        Node node = last;
        while(node != null){
            buffer.append(node.pageNum);

            node = node.prev;
            if(node != null){
                buffer.append(",");
            }
        }
        return buffer.toString();
    }

}
