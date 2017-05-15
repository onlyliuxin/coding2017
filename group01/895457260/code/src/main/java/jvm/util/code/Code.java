package jvm.util.code;

/**
 * Created by Haochen on 2017/1/5.
 */
public abstract class Code {
    public static final int PURE_DECIMAL = 1;
    public static final int PURE_INTEGER = 2;

    protected String code;
    protected int type;
    protected Getter getter;

    public Code(int type, String code) {
        this.type = type;
        this.code = code.replaceAll("[+]", "");
        init();
    }

    protected final void init() {
        if (type == PURE_DECIMAL) {
            getter = new PureDecimal();
        } else {
            getter = new PureInteger();
        }
    }

    public String getCode() {
        return code;
    }

    public int getType() {
        return type;
    }

    public String bitwiseNOTAddOne() {
        int[] array = intArray();
        int last1 = code.lastIndexOf('1');
        for (int i = 1; i < last1; ++i) {
            array[i] = 1 - array[i];
        }
        return intArrayToString(array);
    }

    public String compString() {
        if (isNegative()) {
            return bitwiseNOTAddOne();
        } else {
            return code;
        }
    }

    public static String intArrayToString(int[] array) {
        String str = "";
        for (int i : array) {
            str += i;
        }
        return str;
    }

    public int[] intArray() {
        int[] array = new int[code.length()];
        char[] chars = code.toCharArray();
        for (int i = 0; i < array.length; ++i) {
            array[i] = Integer.parseInt("" + chars[i]);
        }
        return array;
    }

    public int length() {
        return code.length();
    }

    public boolean isNegative() {
        return code.startsWith("1");
    }

    public final double value() {
        return getter.value(trueCode());
    }

    public Code valueCode() {
        return getter.valueCode(trueCode());
    }

    public Code compCode() {
        return trueCode().compCode();
    }

    public void expand(int bit) {
        getter.expand(this, bit, 0);
    }

    public void expand(int bit, int fill) {
        getter.expand(this, bit, fill);
    }

    public abstract Code trueCode();
    public abstract Code inverseSign();
}
