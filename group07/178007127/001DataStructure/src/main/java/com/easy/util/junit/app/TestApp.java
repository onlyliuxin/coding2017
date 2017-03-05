package com.easy.util.junit.app;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import com.easy.util.myarraylist.TestArrayList;
import com.easy.util.mylinkedlist.TestLinkedList;
import com.easy.util.myqueue.TestQueue;
import com.easy.util.mystack.TestStack;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	TestArrayList.class,
	TestLinkedList.class,
	TestQueue.class,
	TestStack.class
})
public class TestApp {

}
