/**
 * 版权 (c) 2017 palmshe.com
 * 保留所有权利。
 */
package com.coderising.ood.ocp;

import com.coderising.ood.ocp.formatter.LogFormatter;
import com.coderising.ood.ocp.handler.LogHandler;

/**
  * @Description:
  * @author palmshe
  * @date 2017年6月19日 下午9:10:02
  */
public class Logger {
	
	private LogHandler logHandler;
	private LogFormatter logFormatter;
	
	public Logger(LogHandler handler, LogFormatter formatter){
		this.logHandler= handler;
		this.logFormatter= formatter;
	}
	
	public void log(String msg){
		this.logHandler.handleLog(this.logFormatter.formatMsg(msg));
	}
}
