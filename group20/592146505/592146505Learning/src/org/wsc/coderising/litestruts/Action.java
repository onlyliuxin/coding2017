package org.wsc.coderising.litestruts;

import java.util.Set;

public class Action {

	/** 标签名 */
	private String tag;
	/** 属性 */
	private String name;
	
	/** 类名 */
	private String className;
	
	/** 子标签 */
	private Set<Action> statusElement;

	/** 文本内容 */
	private String textContent;

}
