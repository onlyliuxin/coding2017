package org.test.all;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.learning.container.TestArrayUtil;
import org.lite.struts.TestStruts;

/**
 * ��������������ӵ�һ�����з��������С�
 * @author z
 *
 */
public class TestAll extends TestCase{

	public static Test suite(){
		TestSuite suite = new TestSuite();
		suite.addTestSuite(TestArrayUtil.class);
		suite.addTestSuite(TestStruts.class);
		return suite;
	}
	
}
