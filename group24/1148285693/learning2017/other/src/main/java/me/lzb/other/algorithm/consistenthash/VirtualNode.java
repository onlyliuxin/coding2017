package me.lzb.other.algorithm.consistenthash;

/**
 * @author LZB
 * @date 2017/5/10
 */
public class VirtualNode {
    private PhysicalNode physicalNode;

    private String id;

    private int size;

    public VirtualNode(PhysicalNode node, String id) {
        this.physicalNode = node;
        this.id = id;
    }


    public void put(String key, String value) {
        physicalNode.put(key, value);
        size++;
    }

    public void remove(String key) {
        physicalNode.remove(key);
        size--;
    }

    public String get(String key) {
        return physicalNode.get(key);
    }

    public String getAddr() {
        return physicalNode.getAddr() + "-" + id;
    }

    public int size() {
        return size;
    }


}
