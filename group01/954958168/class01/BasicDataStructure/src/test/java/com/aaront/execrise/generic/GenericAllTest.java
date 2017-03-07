package com.aaront.execrise.generic;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author tonyhui
 * @since 17/2/22
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        GenericArrayListTest.class,
        GenericLinkedListTest.class,
        GenericQueueTest.class,
        GenericStackTest.class,
        GenericBinaryTreeTest.class
})
public class GenericAllTest {
}
