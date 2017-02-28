public class ArrayUtil{
    public static void main(String[] args) {
        String[] arr = new String[]{"1", "2", "3"};
        String result = join(", ", arr);
        System.out.println(result);

    }

    /**
     * 字符串连接
     */
    private static String join(String con, String[] ele) {

        // StringBuffer is Synchronized, StringBuilder is not.
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < ele.length; i++) {
            if (i != ele.length-1) {
                result.append(ele[i] + con);
            } else {
                result.append(ele[i]);
            }
        }

        return result.toString();
    }

    /**
     * JDK的实现方式
     */
    //private static String jdkJoin(CharSequence delimiter, '') {
    //}
}
