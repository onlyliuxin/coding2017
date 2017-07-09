/**
 * 版权 (c) 2017 palmshe.com
 * 保留所有权利。
 */
package com.coderising.ood.ocp.handler;

import java.io.Serializable;

/**
  * @Description:
  * @author palmshe
  * @date 2017年6月19日 下午9:08:04
  */
public interface LogHandler extends Serializable{

	 void handleLog(String msg);
}
