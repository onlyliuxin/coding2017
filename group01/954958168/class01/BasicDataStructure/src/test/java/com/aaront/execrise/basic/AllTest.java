package com.aaront.execrise.basic;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author tonyhui
 * @since 17/2/21
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        ArrayListTest.class,
        BinaryTreeTest.class,
        LinkListTest.class,
        QueueTest.class,
        StackTest.class
})
public class AllTest {
}
