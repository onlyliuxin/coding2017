package org.litejunit.v2;


/**
 * Created by john on 2017/9/2.
 */
public class Assert {
    protected Assert() {
    }


    public static void assertEquals(byte expected, byte actual) {
        assertEquals((String) null, (byte) expected, (byte) actual);
    }

    public static void assertEquals(char expected, char actual) {
        assertEquals((String) null, (char) expected, (char) actual);
    }

    public static void assertEquals(double expected, double actual, double delta) {
        assertEquals((String) null, expected, actual, delta);
    }

    public static void assertEquals(float expected, float actual, float delta) {
        assertEquals((String) null, expected, actual, delta);
    }

    public static void assertEquals(int expected, int actual) {
        assertEquals((String) null, (int) expected, (int) actual);
    }

    public static void assertEquals(long expected, long actual) {
        assertEquals((String) null, expected, actual);
    }

    public static void assertEquals(Object expected, Object actual) {
        assertEquals((String) null, expected, actual);
    }

    public static void assertEquals(String message, byte expected, byte actual) {
        assertEquals(message, new Byte(expected), new Byte(actual));
    }

    public static void assertEquals(String message, char expected, char actual) {
        assertEquals(message, new Character(expected), new Character(actual));
    }

    public static void assertEquals(String message, double expected, double actual, double delta) {
        if (Double.isInfinite(expected)) {
            if (expected != actual) {
                failNotEquals(message, new Double(expected), new Double(actual));
            }
        } else if (Math.abs(expected - actual) > delta) {
            failNotEquals(message, new Double(expected), new Double(actual));
        }

    }

    public static void assertEquals(String message, float expected, float actual, float delta) {
        if (Float.isInfinite(expected)) {
            if (expected != actual) {
                failNotEquals(message, new Float(expected), new Float(actual));
            }
        } else if (Math.abs(expected - actual) > delta) {
            failNotEquals(message, new Float(expected), new Float(actual));
        }

    }

    public static void assertEquals(String message, int expected, int actual) {
        assertEquals(message, new Integer(expected), new Integer(actual));
    }

    public static void assertEquals(String message, long expected, long actual) {
        assertEquals(message, new Long(expected), new Long(actual));
    }

    public static void assertEquals(String message, Object expected, Object actual) {
        if (expected != null || actual != null) {
            if (expected == null || !expected.equals(actual)) {
                failNotEquals(message, expected, actual);
            }
        }
    }

    public static void assertEquals(String message, short expected, short actual) {
        assertEquals(message, new Short(expected), new Short(actual));
    }

    public static void assertEquals(String message, boolean expected, boolean actual) {
        assertEquals(message, new Boolean(expected), new Boolean(actual));
    }

    public static void assertEquals(short expected, short actual) {
        assertEquals((String) null, (short) expected, (short) actual);
    }

    public static void assertEquals(boolean expected, boolean actual) {
        assertEquals((String) null, expected, actual);
    }

    public static void assertNotNull(Object object) {
        assertNotNull((String) null, object);
    }

    public static void assertNotNull(String message, Object object) {
        assertTrue(message, object != null);
    }

    public static void assertNull(Object object) {
        assertNull((String) null, object);
    }

    public static void assertNull(String message, Object object) {
        assertTrue(message, object == null);
    }

    public static void assertSame(Object expected, Object actual) {
        assertSame((String) null, expected, actual);
    }

    public static void assertSame(String message, Object expected, Object actual) {
        if (expected != actual) {
            failNotSame(message, expected, actual);
        }
    }

    public static void assertTrue(String message, boolean condition) {
        if (!condition) {
            fail(message);
        }

    }

    public static void assertTrue(boolean condition) {
        assertTrue((String) null, condition);
    }

    public static void fail() {
        fail((String) null);
    }

    public static void fail(String message) {
        throw new AssertionFailedError(message);
    }

    private static void failNotEquals(String message, Object expected, Object actual) {
        String formatted = "";
        if (message != null) {
            formatted = message + " ";
        }

        fail(formatted + "expected:<" + expected + "> but was:<" + actual + ">");
    }

    private static void failNotSame(String message, Object expected, Object actual) {
        String formatted = "";
        if (message != null) {
            formatted = message + " ";
        }

        fail(formatted + "expected same");
    }
}
