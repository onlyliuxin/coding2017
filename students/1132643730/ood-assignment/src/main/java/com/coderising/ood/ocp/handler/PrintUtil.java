/**
 * 版权 (c) 2017 palmshe.com
 * 保留所有权利。
 */
package com.coderising.ood.ocp.handler;

/**
  * @Description:
  * @author palmshe
  * @date 2017年6月19日 下午9:22:49
  */
public class PrintUtil implements LogHandler{

	/* (non-Javadoc)
	 * @see com.coderising.ood.ocp.LogHandler#send(java.lang.String)
	 */
	@Override
	public void handleLog(String msg) {
		System.out.println("PrintUtil handle, msg= "+ msg);
	}
}
