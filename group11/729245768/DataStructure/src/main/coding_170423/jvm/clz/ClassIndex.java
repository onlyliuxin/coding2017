package main.coding_170423.jvm.clz;

/**
 * Created by peter on 2017/4/21.
 */
public class ClassIndex {
    private int thisClassIndex;
    private int superClassIndex;

    public void setThisClassIndex(int thisClassIndex) {
        this.thisClassIndex = thisClassIndex;
    }

    public void setSuperClassIndex(int superClassIndex) {
        this.superClassIndex = superClassIndex;
    }

    public int getThisClassIndex() {
        return thisClassIndex;
    }

    public int getSuperClassIndex() {
        return superClassIndex;
    }
}
