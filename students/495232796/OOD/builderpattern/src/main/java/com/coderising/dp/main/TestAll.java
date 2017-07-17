package com.coderising.dp.main;

import com.coderising.dp.builder.TagBuilderTest;

public class TestAll {
	
	public void testTag() {
		TagBuilderTest tt = new TagBuilderTest();
		tt.testToXML();
	}

	public static void main(String[] args) {
		TestAll ta = new TestAll();
		ta.testTag();
	}

}
