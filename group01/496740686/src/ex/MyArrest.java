package ex;

/**
 * Created by Administrator on 2017/2/26.
 */
public class MyArrest {


    public static <T> void arrestEq(T expect, T value) {
        if (expect == null || value == null) {
            if (expect == value) {
                System.out.println("it's ok \n"  );
                return;
            } else {
                System.out.println("happend error \n"  );
                return;
            }
        }
        if (expect.equals(value)) {
            System.out.println("it's ok \n"  );
            return;
        } else {
            System.out.println("happend error \n"  );
            return;
        }
    }

    public static <T> void arrestEq(T expect, T value, String errorInfo) {
        if (expect == null || value == null) {
            if (expect == value) {
                System.out.println("it's ok \n"  );
                return;
            } else {
                System.out.println("happend error \n"  );
                return;
            }
        }
        if (expect.equals(value)) {
            System.out.println("it's ok \n"  );
            return;
        } else {
            System.out.println("happend error \n"  );
            return;
        }
    }

    public static <T> void arrestEqByBasicType(T expect, T value) {
        if (expect == null || value == null) {
            if (expect == value) {
                System.out.println("it's ok \n"  );
                return;
            } else {
                System.out.println("happend error \n"  );
                return;
            }
        }
        if (expect == value) {
            System.out.println("it's ok \n"  );
            return;
        } else {
            System.out.println("happend error \n"  );
            return;
        }
    }

    public static void arrestIsNull(Object obj) {
        if (obj == null) {
            System.out.println("it's null , you're right \n"  );
            return;
        } else {
            System.out.println("happend error \n"  );
            return;
        }
    }
}
