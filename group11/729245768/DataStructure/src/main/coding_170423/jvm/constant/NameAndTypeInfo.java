package main.coding_170423.jvm.constant;

/**
 * Created by peter on 2017/4/21.
 */
public class NameAndTypeInfo extends ConstantInfo {
    public int type = ConstantInfo.NAME_AND_TYPE_INFO;

    private int indexA;
    private int indexB;

    public NameAndTypeInfo(ConstantPool pool){
        super(pool);
    }

    public int getIndexA() {
        return indexA;
    }

    public int getIndexB() {
        return indexB;
    }

    public void setIndexA(int indexA) {
        this.indexA = indexA;
    }

    public void setIndexB(int indexB) {
        this.indexB = indexB;
    }

    public String getName(){
        ConstantPool pool = this.getConstantPool();
        UTF8Info utf8Info = (UTF8Info) pool.getConstantInfo(indexA);
        return utf8Info.getValue();
    }

    public String getTypeInfo(){
        ConstantPool pool = this.getConstantPool();
        UTF8Info utf8_info =(UTF8Info) pool.getConstantInfo(indexB);
        return utf8_info.getValue();
    }

    @Override
    public String toString() {
        return "("+getName()+","+getTypeInfo()+")";
    }

    @Override
    public int getType() {
        return type;
    }
}
