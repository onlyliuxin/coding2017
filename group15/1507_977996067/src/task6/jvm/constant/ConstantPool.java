package task6.jvm.constant;

import java.util.ArrayList;
import java.util.List;

public class ConstantPool {

    private List<ConstantInfo> constantInfos;

    public ConstantPool() {
        this.constantInfos = new ArrayList<>();
    }

    public ConstantPool(int size) {
        this.constantInfos = new ArrayList<>(size);

        addConstantInfo(new NullConstantInfo());
    }

    public void addConstantInfo(ConstantInfo info) {

        this.constantInfos.add(info);

    }

    public ConstantInfo getConstantInfo(int index) {
        return this.constantInfos.get(index);
    }

    public String getUTF8String(int index) {
        return ((UTF8Info) this.constantInfos.get(index)).getValue();
    }

    public Object getSize() {
        return this.constantInfos.size() - 1;
    }
}
