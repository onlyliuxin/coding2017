package jvm.util.code;

/**
 * Created by Haochen on 2017/1/5.
 */
public class ValueCode extends Code {
    public ValueCode(String code) {
        super(code.contains(".") ? PURE_DECIMAL : PURE_INTEGER, code);
    }

    @Override
    public String compString() {
        return "";
    }

    @Override
    public boolean isNegative() {
        return code.startsWith("-");
    }

    @Override
    public int[] intArray() {
        return new int[0];
    }

    @Override
    public Code valueCode() {
        return this;
    }

    @Override
    public void expand(int bit, int fill) {
        super.expand(bit);
    }

    @Override
    public Code trueCode() {
        String str = isNegative() ? "1" + code.substring(1) : "0" + code;
        return new TrueCode(type, str.replaceAll("0[.]", ""));
    }

    @Override
    public Code inverseSign() {
        String str = code;
        Code code = new ValueCode(isNegative() ? str.substring(1) : "-" + str);
        return code;
    }
}
