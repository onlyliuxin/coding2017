package me.lzb.other.algorithm.consistenthash;

import java.util.HashMap;

/**
 * @author LZB
 * @date 2017/5/10
 */
public class PhysicalNode {
    private String ip;

    private String port;

    private HashMap<String, String> data = new HashMap<>();

    public PhysicalNode(String ip, String port) {
        this.ip = ip;
        this.port = port;
    }


    public void put(String key, String value) {
        data.put(key, value);
    }

    public void remove(String key) {
        data.remove(key);
    }

    public String get(String key) {
        return data.get(key);
    }

    public String getAddr() {
        return this.ip + ":" + this.port;
    }

    public int size() {
        return data.size();
    }

    public HashMap<String, String> getDate(){
        return data;
    }

}
