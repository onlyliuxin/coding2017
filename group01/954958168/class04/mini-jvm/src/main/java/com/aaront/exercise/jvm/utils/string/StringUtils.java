package com.aaront.exercise.jvm.utils.string;

/**
 * @author tonyhui
 * @since 17/3/31
 */
public class StringUtils {

    public static Boolean isEmpty(CharSequence charSequence) {
        if (charSequence == null) return true;
        if (charSequence.length() == 0) return true;
        int i = 0;
        int len = charSequence.length();
        for (; i < len; i++) {
            if (!Character.isWhitespace(charSequence.charAt(i))) break;
        }
        return i == len;
    }

    public static String join(Object[] parts, String separator) {
        StringBuilder sb = new StringBuilder();
        int len = parts.length;
        for (int i = 0; i < len; i++) {
            sb.append(parts[i]);
            if (i != len - 1) {
                sb.append(separator);
            }
        }
        return sb.toString();
    }

    public static String join(Iterable<?> iterable, final String separator) {
        StringBuilder sb = new StringBuilder();
        iterable.forEach(r -> {
            sb.append(r);
            sb.append(separator);
        });
        return sb.deleteCharAt(sb.length() - 1).toString();
    }
}
