/**
 * 
 */
package org.le.d;

import java.util.LinkedHashMap;
/**
 * @author yue
 * @time 2017年4月5日
 */
public class LRUMap<K,V> extends LinkedHashMap<K, V>{
	
	private static final long serialVersionUID = -4435712528205829896L;
	private int maxElements;
	
	public LRUMap(int maxElements){
		super(maxElements, 0.75F, true);
		this.maxElements = maxElements;
	}
	
	@Override
	protected boolean removeEldestEntry(java.util.Map.Entry<K, V> eldest) {
		return size() > this.maxElements;
	}
}
