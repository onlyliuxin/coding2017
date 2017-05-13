package jvm.util.code;

/**
 * Created by Haochen on 2017/1/5.
 */
public abstract class Getter {
    public final double value(Code trueCode) {
        return value(trueCode.getCode());
    }

    public final Code valueCode(Code trueCode) {
        return valueCode(trueCode.getCode());
    }

    public abstract double value(String trueCode);
    public abstract Code valueCode(String trueCode);
    public abstract void expand(Code code, int bit, int fill);
}
