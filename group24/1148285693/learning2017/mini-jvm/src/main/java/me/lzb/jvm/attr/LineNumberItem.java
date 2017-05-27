package me.lzb.jvm.attr;

/**
 * @author LZB
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
