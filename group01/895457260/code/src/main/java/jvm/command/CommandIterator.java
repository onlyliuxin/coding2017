package jvm.command;

/**
 * Created by Haochen on 2017/4/20.
 * TODO:
 */
public class CommandIterator {
    private String codes = null;
    private int pos = 0;

    public CommandIterator(String codes) {
        this.codes = codes;
    }

    public boolean hasNext() {
        return pos < this.codes.length();
    }

    public String next2CharAsString() {
        String result = codes.substring(pos, pos + 2);
        pos += 2;
        return result;
    }

    public int next2CharAsInt() {
        String s = this.next2CharAsString();
        return Integer.valueOf(s, 16);
    }
}
