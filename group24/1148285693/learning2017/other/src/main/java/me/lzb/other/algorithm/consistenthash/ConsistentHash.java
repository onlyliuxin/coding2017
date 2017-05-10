package me.lzb.other.algorithm.consistenthash;

import java.util.*;

/**
 * @author LZB
 * @date 2017/5/9
 */
public class ConsistentHash {

    private int vnNumber;

    private HashFunction hashFunction;

    private final SortedMap<Long, VirtualNode> nodes = new TreeMap<>();

    private int size;

    private final Map<String, PhysicalNode> pns = new HashMap<>();

    public ConsistentHash(int pnNum, int vnNum) {
        this.hashFunction = new HashFunction();
        this.vnNumber = vnNum;

        //创建好节点
        int a = 100;

        for (int i = 0; i < pnNum; i++) {
            PhysicalNode pn = new PhysicalNode("192.168.1." + a, "8888");
            pns.put(pn.getAddr(), pn);
            addNode(pn);
            a++;
        }
    }


    public void addNode(PhysicalNode pn) {
        //添加一个物理节点，增加vnNumber个虚拟节点
        for (int i = 0; i < this.vnNumber; i++) {
            VirtualNode vn = new VirtualNode(pn, String.valueOf(i));
            nodes.put(hashFunction.hash(vn.getAddr()), vn);
        }

    }

    public void removeNode(PhysicalNode node) {
        for (int i = 0; i < this.vnNumber; i++)
            nodes.remove(hashFunction.hash(node.getAddr() + "-" + i));
    }

    /**
     * 获取顺时针方向最近的节点
     *
     * @param key
     * @return
     */
    public VirtualNode getNode(String key) {
        if (nodes.isEmpty()) {
            return null;
        }

        long hash = hashFunction.hash(key);
        if (!nodes.containsKey(hash)) {
            SortedMap<Long, VirtualNode> tailMap = nodes.tailMap(hash);
            hash = tailMap.isEmpty() ? nodes.firstKey() : tailMap.firstKey();
        }
        return nodes.get(hash);
    }

    public long getSize() {
        return nodes.size();
    }


    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        for (Long key : nodes.keySet()) {
            VirtualNode n = nodes.get(key);
            sb.append(n.getAddr());
            sb.append("     ");
            sb.append(n.size());
            sb.append("\r\n");
        }
        return sb.toString();
    }


    public SortedMap<Long, VirtualNode> getNodes() {
        return this.nodes;
    }

    public Map<String, PhysicalNode> getPNodes() {
        return this.pns;
    }


    public void put(String key, String value) {
        VirtualNode node = getNode(key);
        node.put(key, value);
        size++;
    }

    public void remove(String key) {
        VirtualNode node = getNode(key);
        node.remove(key);
        size--;
    }

    public String get(String key) {
        VirtualNode node = getNode(key);
        return node.get(key);
    }

    public int size() {
        return size;
    }

}
