package me.lzb.jvm.attr;

/**
 * Created by LZB on 2017/4/15.
 */
public class LocalVariableItem {
    private int startPC;
    private int length;
    private int nameIndex;
    private int descIndex;
    private int index;


    public LocalVariableItem(int startPC, int length, int nameIndex, int descIndex, int index){
        this.startPC = startPC;
        this.length = length;
        this.nameIndex = nameIndex;
        this.descIndex = descIndex;
        this.index = index;
    }


    public int getStartPC() {
        return startPC;
    }

    public void setStartPC(int startPC) {
        this.startPC = startPC;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getNameIndex() {
        return nameIndex;
    }

    public void setNameIndex(int nameIndex) {
        this.nameIndex = nameIndex;
    }

    public int getDescIndex() {
        return descIndex;
    }

    public void setDescIndex(int descIndex) {
        this.descIndex = descIndex;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
