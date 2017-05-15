package me.lzb.jvm.attr;

/**
 * Created by LZB on 2017/4/16.
 */
public class LineNumberItem {
    int startPC;
    int lineNum;


    public LineNumberItem(int startPC, int lineNum) {
        this.startPC = startPC;
        this.lineNum = lineNum;
    }

    public int getStartPC() {
        return startPC;
    }

    //    public void setStartPC(int startPC) {
//        this.startPC = startPC;
//    }

    public int getLineNum() {
        return lineNum;
    }

//    public void setLineNum(int lineNum) {
//        this.lineNum = lineNum;
//    }
}
